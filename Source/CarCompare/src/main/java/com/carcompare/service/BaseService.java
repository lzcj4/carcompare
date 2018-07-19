package com.carcompare.service;

import com.carcompare.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务基类
 */
public abstract class BaseService {
    public static Logger logger = LoggerFactory.getLogger(BaseService.class);

    /**
     * 获取当前用户ID
     * @return
     */
    protected  Long getCurrentUserId(){
        Object obj = getSessionAttribute(TokenUtil.CLAIM_KEY_USERID);
        if(obj == null){
            logger.warn("getCurrentUserId is null");
            return null;
        }

        Long userId = Long.valueOf(obj.toString());
        return userId;
    }

    /**
     * 获取当前用户名
     * @return
     */
    protected  String getCurrentUsername(){
        Object obj = getSessionAttribute(TokenUtil.CLAIM_KEY_USERNAME);
        if(obj == null){
            logger.warn("getCurrentUsername is null");
            return null;
        }

        String username = (String) obj;
        return username;
    }

    /**
     * 当前是否后端用户
     * @return
     */
    protected  Boolean currentUserIsBackend(){
        Object obj = getSessionAttribute(TokenUtil.CLAIM_KEY_USER_IS_BACKEND);
        if(obj == null){
            return false;
        }

        return (Boolean) obj;
    }

    private Object getSessionAttribute(Object key){
        return SecurityUtils.getSubject().getSession().getAttribute(key);
    }
}
