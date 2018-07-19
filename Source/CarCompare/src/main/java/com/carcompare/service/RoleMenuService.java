package com.carcompare.service;

import com.carcompare.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleMenuService {
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean updateRoleMenu(Long roleId, Long[] menuIds) {
        roleMenuMapper.deleteByRoleId(roleId);
        if(menuIds.length == 0){
            return true;
        }
        return roleMenuMapper.addRoleMenu(roleId, menuIds) > 0;
    }
}
