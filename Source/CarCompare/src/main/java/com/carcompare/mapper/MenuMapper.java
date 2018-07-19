package com.carcompare.mapper;

import com.carcompare.core.menus.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    /**
     * 获取用户拥有的菜单列表
     * @param userId
     * @return
     */
    List<Menu> getMenusByUserId(Long userId);

    /**
     * 获取菜单的树形结构
     * @return
     */
    List<Menu> getMenuTree();

    /**
     * 获取角色拥有的菜单列表
     * @param roleId
     * @return
     */
    List<Long> getMenusByRoleId(Long roleId);

    List<Menu> getMenuItemsByRoleId(@Param("roleId")Long roleId);

    /**
     * 获取叶子菜单
     * @return
     */
    List<Menu> getLeafMenus();

    /**
     * 根据路径获取菜单信息
     * @param path
     * @return
     */
    Menu getMenuByPath(String path);
}