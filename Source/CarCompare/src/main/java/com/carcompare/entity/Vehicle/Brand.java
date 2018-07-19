package com.carcompare.entity.Vehicle;

import lombok.Data;

@Data
public class Brand extends VehicleBase {
    /**
     * 品牌首字母
     */
    private String initials;

    /**
     * 品牌显示顺序
     */
    private int order;
}
