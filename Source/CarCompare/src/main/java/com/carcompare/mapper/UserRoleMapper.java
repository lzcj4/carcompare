package com.carcompare.mapper;

import com.carcompare.core.roles.Role;
import com.carcompare.core.users.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByUserId(@Param("userId") Long userId);

    int deleteByUserIds(@Param("userIds") String[] userIds);

    int addUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    List<UserRole> getAllUserRoles();

    List<Role> getRolesByUserId(@Param("userId")Long userId);
}
