package com.carcompare.dto.role;

import com.carcompare.dto.PagedResultInput;

public class GetAllRolesInput extends PagedResultInput {
    public GetAllRolesInput(){
        super();
    }

    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
