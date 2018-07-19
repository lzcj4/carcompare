package com.carcompare.entity.Vehicle;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class FeaturePicture implements Serializable {
    private BigInteger id;
    private int type;
    private String name,path,originname,bigname,mediumname,smallname,parentid;
}
