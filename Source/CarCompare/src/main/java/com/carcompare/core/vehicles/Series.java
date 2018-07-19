package com.carcompare.core.vehicles;

import java.util.UUID;

public class Series extends VehicleBase {

    public UUID getParentid() {
        return parentid;
    }

    public void setParentid(UUID parentid) {
        this.parentid = parentid;
    }

    private UUID parentid;





}
