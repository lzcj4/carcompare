package com.carcompare.mapper;

import com.carcompare.core.users.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /**
     * 获取所有用户列表
     * @param keywords
     * @return
     */
    List<User> getAllUsers(@Param("keywords") String keywords);

    /**
     * 获取指定创建人创建的用户列表
     * @param creator
     * @param keywords
     * @return
     */
    List<User> getUsersByCreator(@Param("creator") Long creator, @Param("keywords") String keywords);

    /**
     * 根据用户ID获取用户
     * @param id
     * @return
     */
    @ResultMap("baseResultMap")
    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(Long id);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getByUsername(@Param("username") String username);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    int deleteUsers(@Param("ids") String[] ids);

    /**
     * 更新用户密码
     * @param userId
     * @param encryptedPassword
     * @return
     */
    int updatePassword(@Param("userId") Long userId, @Param("encryptedPassword") String encryptedPassword);

    /**
     * 重置用户密码
     * @param ids
     * @param defaultPassword
     * @return
     */
    int resetPassWord(@Param("ids") String[] ids,@Param("defaultPassword") String defaultPassword);
}