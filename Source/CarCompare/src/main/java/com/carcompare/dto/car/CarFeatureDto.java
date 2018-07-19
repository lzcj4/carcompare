package com.carcompare.dto.car;


import com.carcompare.core.vehicles.Feature;
import lombok.Data;

@Data
public class CarFeatureDto {
    private String brand, id, year;

    public void readFeature(Feature feature) {
        if (feature != null) {
            this.id = feature.getId();
            this.brand = feature.getBrand();
            this.year = feature.getModelyear();
        }
    }

    public Feature toEntity() {
        Feature feature = new Feature();
        feature.setId(this.id);
        feature.setBrand(this.brand);
        feature.setModelyear(this.year);
        return feature;
    }
}