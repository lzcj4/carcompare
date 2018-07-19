package com.carcompare.entity;

import lombok.Data;

/**
 * 特征项实体类
 */
@Data
public class FeatureItem {
    private String id;
    private String code;
    private String name;
    private String parentId;
    private Integer order;
    private String remark;
}
