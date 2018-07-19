package com.carcompare.service;

import com.carcompare.core.menus.Menu;
import com.carcompare.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务
 */
@Service
public class MenuService extends BaseService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenusByCurrentUser() {
        Long userId = this.getCurrentUserId();
        return menuMapper.getMenusByUserId(userId);
    }

    public List<Menu> menuTree() {
        return menuMapper.getMenuTree();
    }

    public List<Long> getMenusByRoleId(Long roleId) {
        return menuMapper.getMenusByRoleId(roleId);
    }

    public List<Menu> getLeafMenus(){
        return menuMapper.getLeafMenus();
    }
}
