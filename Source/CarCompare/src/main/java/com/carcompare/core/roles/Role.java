package com.carcompare.core.roles;

import com.carcompare.core.menus.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

@Data
public class Role implements Serializable {
    private Long id;

    @NotBlank(message = "角色编码不能为空")
    @Length(min = 3, max = 18, message = "角色编码长度必须为{min}-{max}个字符之间")
    private String code;

    @NotBlank(message = "角色名称不能为空")
    @Length(min = 3, max = 18, message = "角色名称长度必须为{min}-{max}个字符之间")
    private String name;

    private String description;

    private Boolean isBackend;

    private Boolean enabled;

    private String defaultUrl;

    private String defaultUrlName;

    @JsonIgnore
    private List<Menu> menus;
}
