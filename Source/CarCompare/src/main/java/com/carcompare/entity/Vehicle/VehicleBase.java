package com.carcompare.entity.Vehicle;

import lombok.Data;

import java.io.Serializable;

@Data
public class VehicleBase implements Serializable {
    private  String id;

    private  String name;

    private  int total;
}
