package com.carcompare.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    int deleteByRoleId(@Param("roleId") Long roleId);

    int deleteByRoleIds(@Param("roleIds") String[] roleIds);

    int addRoleMenu(@Param("roleId") Long roleId, @Param("menuIds") Long[] menuIds);
}
