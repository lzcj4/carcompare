package com.carcompare.core.users;

public class UserRole {
    private Long userid;

    private Long roleid;

    public UserRole(Long userid, Long roleid) {
        this.userid = userid;
        this.roleid = roleid;
    }

    public UserRole() {
        super();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}