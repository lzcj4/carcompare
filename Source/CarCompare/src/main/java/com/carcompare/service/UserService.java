package com.carcompare.service;

import com.carcompare.base.UserFriendlyException;
import com.carcompare.core.roles.Role;
import com.carcompare.core.users.User;
import com.carcompare.dto.user.GetAllUsersInput;
import com.carcompare.dto.user.GetAllUsersOutput;
import com.carcompare.mapper.RoleMapper;
import com.carcompare.mapper.UserMapper;
import com.carcompare.mapper.UserRoleMapper;
import com.carcompare.utils.AppUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户服务类
 */
@Service
public class UserService extends BaseService {

    public static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Value("${custom.user.default.password}")
    private String userDefaultPassword;

    @Value("${custom.frontend.user.rolecode}")
    private String frontendUsertRoleCode;

    @Value("${custom.frontend.administrator.rolecode}")
    private String frontendAdministratorRoleCode;

    public User getUserById(Long id){
        return this.userMapper.getUserById(id);
    }

    public User getByUsername(String username){
        return this.userMapper.getByUsername(username);
    }

    public GetAllUsersOutput getAllUsers(GetAllUsersInput input){
        PageHelper.startPage(input.getPageIndex(), input.getPageSize());

        List<User> users = this.userMapper.getUsersByCreator(this.getCurrentUserId(), input.getKeywords());

        PageInfo<User> pageInfo = new PageInfo<User>(users);

        GetAllUsersOutput output = new GetAllUsersOutput();
        output.setTotalCount(pageInfo.getTotal());
        output.setItems(users);

        return output;
    }

    @Transactional
    public boolean addUser(User user){
        User currentUser = this.getByUsername(this.getCurrentUsername());

        //如果当前用户是前端管理员，那么默认添加前端用户
        if(currentUser.getRole().getCode().equals(frontendAdministratorRoleCode)){
            return saveFrontendUser(user);
        }

        return saveUser(user);
    }

    public boolean upateUser(User user){
        int res = this.userMapper.updateUser(user);
        return res > 0;
    }

    public void updatePassword(String originalPassword, String newPassword, String verifyPassword){
        User user = userMapper.getUserById(this.getCurrentUserId());
        if (!user.getPassword().equals(AppUtil.MD5(originalPassword))){
            throw new UserFriendlyException("原始密码不正确");
        }

        int passwordLen = newPassword.length();
        if(passwordLen <6 || passwordLen>12){
            throw new UserFriendlyException("密码长度必须在6-12个字符之间");
        }

        if(!newPassword.equals(verifyPassword)){
            throw new UserFriendlyException("新密码和确认密码不一致");
        }

        String encryptedPassword = AppUtil.MD5(newPassword);
        int res = userMapper.updatePassword(user.getId(), encryptedPassword);
        if(res == 0){
            throw new UserFriendlyException("密码修改失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean deleteUsers(String[] ids){
        this.userRoleMapper.deleteByUserIds(ids);

        return this.userMapper.deleteUsers(ids) == ids.length;
    }

    public  boolean resetPassWord(String[] ids){
        String defaultPassword = AppUtil.MD5(userDefaultPassword);
        return  this.userMapper.resetPassWord(ids,defaultPassword) == ids.length;
    }

    /**
     * 添加一般用户
     * @param user
     * @return
     */
    private boolean saveUser(User user){
        User findUser = userMapper.getByUsername(user.getUsername());
        if(findUser != null){
            throw new UserFriendlyException("用户名已存在");
        }

        user.setCreationUserId(this.getCurrentUserId());
        user.setCreationTime(new Date());
        user.setPassword(AppUtil.MD5(userDefaultPassword));
        user.setSalt(user.getUsername());
        user.setStatus(Byte.parseByte("0"));
        int res = this.userMapper.addUser(user);
        if(res == 0){
            throw new UserFriendlyException("添加用户失败");
        }

        return true;
    }

    /**
     * 添加前端用户
     * @param user
     * @return
     */
    private boolean saveFrontendUser(User user){
        User findUser = userMapper.getByUsername(user.getUsername());
        if(findUser != null){
            throw new UserFriendlyException("用户名已存在");
        }

        Role role = roleMapper.getRoleByCode(frontendUsertRoleCode);
        if(role == null){
            throw new UserFriendlyException("请设置一个前端默认角色");
        }

        user.setCreationUserId(this.getCurrentUserId());
        user.setCreationTime(new Date());
        user.setPassword(AppUtil.MD5(userDefaultPassword));
        user.setSalt(user.getUsername());
        user.setStatus(User.Statuses.Enable.getValueAsByte());

        int res = this.userMapper.addUser(user);
        if(res == 0){
            throw new UserFriendlyException("添加用户失败");
        }

        res = this.userRoleMapper.addUserRole(user.getId(), role.getId());
        if(res == 0){
            throw new UserFriendlyException("添加用户失败");
        }

        return true;
    }
}
