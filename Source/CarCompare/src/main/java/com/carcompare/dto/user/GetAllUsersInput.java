package com.carcompare.dto.user;

import com.carcompare.dto.PagedResultInput;

public class GetAllUsersInput extends PagedResultInput {
    public GetAllUsersInput(){
        super();
    }

    public String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
