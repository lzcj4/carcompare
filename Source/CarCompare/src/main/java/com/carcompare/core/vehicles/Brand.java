package com.carcompare.core.vehicles;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
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
