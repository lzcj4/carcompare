package com.carcompare.service;

import com.carcompare.core.authentication.AccountManager;
import com.carcompare.core.authentication.LoginMode;
import com.carcompare.base.UserFriendlyException;
import com.carcompare.dto.auth.LoginInput;
import com.carcompare.dto.auth.LoginOutput;
import com.carcompare.core.logs.Log;
import com.carcompare.core.users.User;
import com.carcompare.license.LicenseValidator;
import com.carcompare.mapper.RoleMapper;
import com.carcompare.mapper.UserMapper;
import com.carcompare.shiro.jwt.JwtToken;
import com.carcompare.utils.LogUtil;
import com.carcompare.utils.TokenUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class AuthService extends BaseService {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private LogUtil logUtil;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AccountManager accountManager;

    @Autowired
    LicenseValidator licenseValidator;

    @Value("${custom.captcha.enable}")
    Boolean captchaEnable;

    /**
     * 登录
     * @param input 登录参数
     * @param loginMode 登录模式
     * @return
     */
    @SuppressWarnings("unchecked")
    public LoginOutput login(LoginInput input, LoginMode loginMode) {
        checkLicense();

        if(StringUtils.isEmpty(input.getUsername())){
            throw new UserFriendlyException("用户名不能为空");
        }

        if(StringUtils.isEmpty(input.getPassword())){
            throw new UserFriendlyException("密码不能为空");
        }

        User user = this.userMapper.getByUsername(input.getUsername());
        if (user == null) {
            throw new UserFriendlyException("用户名或密码有误");
        }

        if(user.getRole() == null){
            throw new UserFriendlyException("没有登录权限:[权限未分配]");
        }

        if(!user.getRole().getIsBackend() && loginMode == LoginMode.BACKEND){
            throw new UserFriendlyException("没有登录权限");
        }

        String hashPassword = DigestUtils.md5Hex(input.getPassword());
        if (!user.getPassword().equals(hashPassword)) {
            throw new UserFriendlyException("用户名或密码有误");
        }

        if(loginMode == LoginMode.FRONTEND && this.captchaEnable){
            if(StringUtils.isEmpty(input.getCaptcha())){
                throw new UserFriendlyException("请输入验证码");
            }

            if(!captchaService.validate(input.getCaptcha())){
                throw new UserFriendlyException("验证码有误");
            }
        }

        // 验证用户名密码成功后生成token
        String token = tokenUtil.createToken(user, input.getDevice());
        JwtToken jwtToken = JwtToken.builder().token(token).principal(input.getUsername()).build();

        Boolean isAuthenticated = accountManager.login(user, jwtToken);
        if (isAuthenticated) {
            LoginOutput loginOutput = new LoginOutput();
            loginOutput.setToken(jwtToken.getToken());
            loginOutput.setName(user.getName());
            loginOutput.setUsername(user.getUsername());
            loginOutput.setIsBackend(user.getRole().getIsBackend());
            loginOutput.setRoleCode(user.getRole().getCode());
            loginOutput.setDefaultUrl(user.getRole().getDefaultUrl());
            loginOutput.setLoginMode(loginMode);

            Log log = new Log();
            log.setUsername(user.getUsername());
            log.setTime(new Date());
            log.setType(LogUtil.USER_LOGIN);
            log.setDescription(LogUtil.USER_LOGIN);
            logUtil.AddLog(log);

            return loginOutput;
        } else {
            throw new UserFriendlyException("认证失败");
        }
    }

    /**
     * 登出
     */
    public void logout() {
        String username = this.getCurrentUsername();

        accountManager.logout();

        Log log = new Log();
        log.setUsername(username);
        log.setTime(new Date());
        log.setType(LogUtil.USER_LOGOUT);
        log.setDescription(LogUtil.USER_LOGOUT);
        logUtil.AddLog(log);
    }

    /**
     * 验证License
     */
    private void checkLicense(){
        if(licenseValidator.getLicenseValidationResult().getValid()){
            return;
        }

        throw new UserFriendlyException(licenseValidator.getLicenseValidationResult().getMessage());
    }
}
