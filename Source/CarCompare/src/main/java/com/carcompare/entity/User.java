package com.carcompare.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Long id;

    @NotBlank(message = "名称不能为空")
    @Length(min = 1, max = 18, message = "名称长度必须为{min}-{max}个字符之间")
    private String name;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 2, max = 18, message = "用户名长度必须为{min}-{max}个字符之间")
    private String username;

    private String password;

    private String salt;

    private Byte status;

    private Long creationUserId;

    private String creationUserName;

    private Date creationTime;

    /***
     * 是否后端用户
     */
    private Boolean isBackend;

    private List<Role> roles;

    public User() {
    }

}