package com.carcompare.core.roles;

public class RoleMenu {
    private Long roleid;

    private Long menuid;

    public RoleMenu(Long roleid, Long menuid) {
        this.roleid = roleid;
        this.menuid = menuid;
    }

    public RoleMenu() {
        super();
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}