package com.carcompare.core.features;

import lombok.Data;

import java.util.List;

/**
 * 特征组实体类
 */
@Data
public class FeatureGroup {
    private String id;
    private String code;
    private String name;
    private List<FeatureItem> items;
}
