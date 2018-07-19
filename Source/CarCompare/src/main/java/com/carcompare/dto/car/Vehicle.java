package com.carcompare.dto.car;

import lombok.Data;

@Data
public class Vehicle {
    private String id;

    private String brandId;

    private String brand;

    private String initials;

    private String seriesId;

    private String series;

    private String yearId;

    private String yearCode;

    private String year;

    private String modelId;

    private String model;

    private String picUrl;

    /**
     * 获取复合名称（品牌+车系+年款+款型）
     * @return
     */
    public String getComplexName(){
        return String.format("%s %s %s %s", brand, series, year, model);
    }
}
