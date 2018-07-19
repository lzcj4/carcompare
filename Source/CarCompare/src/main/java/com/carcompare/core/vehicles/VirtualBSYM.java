package com.carcompare.core.vehicles;

import lombok.Data;

@Data
public class VirtualBSYM {
    private String id,oid ,Name,parentid,code,tableName;
    private char initials;
    private int order,count;
}
