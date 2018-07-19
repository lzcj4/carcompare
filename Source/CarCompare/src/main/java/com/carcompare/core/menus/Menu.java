package com.carcompare.core.menus;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Long id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String icon;

    private Long parentId;

    private Boolean requireAuth;

    private Integer order;

    private Boolean enabled;

    private  String roleName;

    private List<Menu> children;
}