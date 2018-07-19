package com.carcompare.core.users;

import com.carcompare.core.roles.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Long id;

    @NotBlank(message = "姓名不能为空")
    @Length(min = 3, max = 18, message = "姓名长度必须为{min}-{max}个字符之间")
    private String name;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 18, message = "用户名长度必须为{min}-{max}个字符之间")
    private String username;

    private String password;

    private String salt;

    private Byte status;

    private Long creationUserId;

    private String creationUserName;

    private Date creationTime;

    private Role role;

    public User() {
    }

    /**
     * 用户可用状态枚举
     */
    public enum Statuses {
        Enable(0, "可用"), Disable(1, "禁用");

        private int value;  //枚举value字段
        private String description; //枚举描述字段

        public int getValue() {
            return value;
        }

        public Byte getValueAsByte() {
            return Byte.parseByte(String.valueOf(value));
        }

        public String getDescription() {
            return description;
        }

        //构造初始化赋值
        Statuses(int value, String description){
            this.description = description;
            this.value = value;
        }

    }
}