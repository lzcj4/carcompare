package com.carcompare.base;

import org.apache.poi.ss.usermodel.Row;

public abstract class BasePOIMapper<T> {
    public abstract T initDto(Row row);
    public abstract void insertRow(Row row);
}
