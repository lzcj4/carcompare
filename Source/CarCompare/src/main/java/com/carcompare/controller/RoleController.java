package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.dto.role.GetAllRolesInput;
import com.carcompare.dto.role.GetAllRolesOutput;
import com.carcompare.core.menus.Menu;
import com.carcompare.core.roles.Role;
import com.carcompare.service.MenuService;
import com.carcompare.service.RoleMenuService;
import com.carcompare.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController {
    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @Autowired
    RoleMenuService roleMenuService;

    @GetMapping("")
    public ApiResult getAllRoles(GetAllRolesInput input){
        GetAllRolesOutput output = this.roleService.getAllRoles(input);
        return  new ApiResult(true, output);
    }

    @PostMapping("")
    public ApiResult addRole(@Valid Role role, BindingResult bindingResult){
        boolean success = this.roleService.addRole(role);
        if(success){
            return  new ApiResult(true);
        }else {
            return  new ApiResult(false, "操作失败");
        }
    }

    @PutMapping("/{roleId}")
    public ApiResult updateRole(@PathVariable Long roleId, @Valid Role role, BindingResult bindingResult){
        boolean success = this.roleService.upateRole(role);
        if(success){
            return  new ApiResult(true);
        }else {
            return  new ApiResult(false, "操作失败");
        }
    }

    @DeleteMapping("/{ids}")
    public ApiResult deleteRoles(@PathVariable("ids") String ids){
        String[] idArr = ids.split(",");

        boolean success = this.roleService.deleteRoles(idArr);
        if(success){
            return  new ApiResult(true);
        }else {
            return  new ApiResult(false, "操作失败");
        }
    }

    @GetMapping(value = "/{roleId}/menu_tree")
    public ApiResult getMenuTree(@PathVariable Long roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Menu> menus = menuService.menuTree();
        map.put("menus", menus);
        List<Long> hasMenuIds = menuService.getMenusByRoleId(roleId);
        map.put("hasMenuIds", hasMenuIds);
        return new ApiResult(true, map);
    }

    @PutMapping(value = "/{roleId}/set_permission")
    public ApiResult updatePermission(@PathVariable Long roleId, Long[] menuIds) {
        if (roleMenuService.updateRoleMenu(roleId, menuIds)) {
            return new ApiResult(true);
        }
        return new ApiResult(false, "更新失败!");
    }
}
