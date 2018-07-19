package com.carcompare.service;

import com.carcompare.base.UserFriendlyException;
import com.carcompare.dto.role.GetAllRolesInput;
import com.carcompare.dto.role.GetAllRolesOutput;
import com.carcompare.core.menus.Menu;
import com.carcompare.core.roles.Role;
import com.carcompare.core.users.UserRole;
import com.carcompare.mapper.MenuMapper;
import com.carcompare.mapper.RoleMapper;
import com.carcompare.mapper.RoleMenuMapper;
import com.carcompare.mapper.UserRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务类
 */
@Service
public class RoleService extends BaseService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public GetAllRolesOutput getAllRoles(GetAllRolesInput input){
        PageHelper.startPage(input.getPageIndex(), input.getPageSize());

        List<Role> roles = this.roleMapper.getAllRoles(input.getKeywords());
        PageInfo<Role> page = new PageInfo<Role>(roles);

        GetAllRolesOutput output = new GetAllRolesOutput();
        output.setTotalCount(page.getTotal());
        output.setItems(roles);

        return output;
    }

    /**
     * 获取启用的角色列表
     * @return
     */
    public List<Role> getEnabledRoles(){
        return roleMapper.getEnabledRoles();
    }

    /**
     * 获取可分配的角色列表
     * @return
     */
    public List<Role> getAssignableRoles(){
        return roleMapper.getEnabledRoles();
    }

    public List<Role> getRolesByUserId(Long userId){
        return roleMapper.getRolesByUserId(userId);
    }

    public List<Menu> getRoleMenus(Long roleId){
        return roleMapper.getRoleMenus(roleId);
    }

    public boolean addRole(Role role) {
        Role findRole = roleMapper.getRoleByCode(role.getCode());
        if(findRole != null){
            throw new UserFriendlyException("角色编码已存在");
        }

        String defaultUrlName = this.getDefaultUrlName(role.getDefaultUrl());
        role.setDefaultUrlName(defaultUrlName);

        return this.roleMapper.addRole(role) > 0;
    }

    public boolean upateRole(Role role){
        String defaultUrlName = this.getDefaultUrlName(role.getDefaultUrl());
        role.setDefaultUrlName(defaultUrlName);

        return this.roleMapper.updateRole(role) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean deleteRoles(String[] ids){
        List<UserRole> userRoleList = this.userRoleMapper.getAllUserRoles();

        for (String id : ids){
            int roleId = Integer.parseInt(id);
            Boolean isTrue = userRoleList.stream().anyMatch(p->p.getRoleid() == roleId);
            if(isTrue){
                throw new UserFriendlyException("删除失败，删除的角色已被引用");
            }
        }

        this.roleMenuMapper.deleteByRoleIds(ids);
        this.roleMapper.deleteRoles(ids);

        return true;
    }

    private String getDefaultUrlName(String defaultUrl){
        if(defaultUrl == null || defaultUrl.equals("")){
            return "";
        }

        Menu menu = menuMapper.getMenuByPath(defaultUrl);
        if(menu == null){
            throw new UserFriendlyException("默认页面不存在");
        }

        return menu.getName();
    }
}
