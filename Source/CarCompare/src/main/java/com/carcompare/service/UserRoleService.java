package com.carcompare.service;

import com.carcompare.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean updateUserRole(Long userId, Long[] roleIds) {
        userRoleMapper.deleteByUserId(userId);
        if(roleIds.length == 0){
            return true;
        }
        return userRoleMapper.addUserRole(userId, roleIds[0]) > 0;
    }
}
