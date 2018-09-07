package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.base.AppConsts;
import com.carcompare.core.roles.Role;
import com.carcompare.core.users.User;
import com.carcompare.dto.user.GetAllUsersInput;
import com.carcompare.dto.user.GetAllUsersOutput;
import com.carcompare.service.RoleService;
import com.carcompare.service.UserRoleService;
import com.carcompare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping("")
    public ApiResult getAllUsers(GetAllUsersInput input) {
        GetAllUsersOutput output = this.userService.getAllUsers(input);

        //移除超级管理员
        output.getItems().removeIf(u->u.getRole() != null && u.getRole().getCode().equals(AppConsts.ADMINISTRATOR_ROLE_CODE));

        return new ApiResult(true, output);
    }

    @PostMapping("")
    public ApiResult addUser(@Valid User user, BindingResult bindingResult) {
        this.userService.addUser(user);
        return new ApiResult(true);
    }

    @PutMapping("/{userId}")
    public ApiResult updateUser(@PathVariable Long userId, @Valid User user, BindingResult bindingResult) {
        boolean success = this.userService.upateUser(user);
        if (success) {
            return new ApiResult(true);
        } else {
            return new ApiResult(false, "操作失败");
        }
    }

    @PutMapping("/reset_password")
    public ApiResult updatePassword(String originalPassword, String newPassword, String verifyPassword) {
        this.userService.updatePassword(originalPassword, newPassword, verifyPassword);
        return new ApiResult(true);
    }

    @PutMapping("/resetPassword")
    public ApiResult resetPassword(String ids) {
        String[] idArr = ids.split(",");
        boolean success =this.userService.resetPassWord(idArr);
        if (success) {
            return new ApiResult(true);
        } else {
            return new ApiResult(false, "操作失败");
        }
    }

    @DeleteMapping("/{ids}")
    public ApiResult deleteUsers(@PathVariable("ids") String ids) {
        String[] idArr = ids.split(",");
        boolean success = this.userService.deleteUsers(idArr);
        if (success) {
            return new ApiResult(true);
        } else {
            return new ApiResult(false, "操作失败");
        }
    }

    @GetMapping(value = "/{userId}/roles")
    public ApiResult getRolesByUserId(@PathVariable Long userId) {
        Map<String, Object> map = new HashMap<>();

        List<Role> roles = roleService.getAssignableRoles();
        map.put("roles", roles);

        List<Role> hasRoles = roleService.getRolesByUserId(userId);
        List<Long> hasRoleIds = hasRoles.stream().map(Role::getId).collect(Collectors.toList());
        map.put("hasRoleIds", hasRoleIds);

        return new ApiResult(true, map);
    }

    @PutMapping(value = "/{userId}/roles")
    public ApiResult updateRolesByUserId(@PathVariable Long userId, Long[] roleIds) {
        if (userRoleService.updateUserRole(userId, roleIds)) {
            return new ApiResult(true);
        }
        return new ApiResult(false, "更新失败!");
    }
}
