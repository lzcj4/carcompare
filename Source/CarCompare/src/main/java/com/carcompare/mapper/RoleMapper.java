package com.carcompare.mapper;

import com.carcompare.core.menus.Menu;
import com.carcompare.core.roles.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
    /**
     * 获取所有角色列表
     * @param keywords
     * @return
     */
    List<Role> getAllRoles(@Param("keywords") String keywords);

    /**
     * 获取启用的角色列表
     * @return
     */
    List<Role> getEnabledRoles();

    /**
     * 用户拥有的角色列表
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(@Param("userId")Long userId);

    /**
     * 获取菜单角色
     * @param roleId
     * @return
     */
    List<Menu> getRoleMenus(Long roleId);

    /**
     * 根据角色编码获取角色
     * @param code
     * @return
     */
    @ResultMap("baseResultMap")
    @Select("SELECT * FROM role WHERE code=#{code}")
    Role getRoleByCode(String code);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    int deleteRoles(@Param("ids") String[] ids);
}
