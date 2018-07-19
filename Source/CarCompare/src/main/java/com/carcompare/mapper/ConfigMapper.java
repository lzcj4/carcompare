package com.carcompare.mapper;

import com.carcompare.dto.common.KeyValueDto;
import com.carcompare.core.vehicles.Brand;

import java.util.List;

public interface ConfigMapper {
    List<Brand> getAllBrands();

    List<KeyValueDto> getSeriesByBrandId(String brandId);

    List<KeyValueDto> getModelYearsBySeriesId(String seriesId);

    List<KeyValueDto> getModelsByYearId(String modelyearId);
}
