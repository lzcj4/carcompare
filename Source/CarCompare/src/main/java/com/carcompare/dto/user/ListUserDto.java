package com.carcompare.dto.user;

import com.carcompare.core.users.User;
import java.io.Serializable;

public class ListUserDto extends User implements Serializable {
    private String creationUserName;

    public String getCreationUserName() {
        return creationUserName;
    }

    public void setCreationUserName(String creationUserName) {
        this.creationUserName = creationUserName;
    }
}
