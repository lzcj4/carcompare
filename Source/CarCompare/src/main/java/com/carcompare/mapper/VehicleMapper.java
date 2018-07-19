package com.carcompare.mapper;

import com.carcompare.dto.car.Vehicle;
import com.carcompare.core.vehicles.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VehicleMapper {
    /**
     * 获取品牌及品牌下的车型统计
     * @param vehicleStatus
     * @return
     */
    List<Brand> getBrandsWithVehicleCount(String vehicleStatus);

    /**
     * 获取车系及车系下的车型统计
     * @param brandId
     * @param vehicleStatus
     * @return
     */
    List<Series> getSeriesWithVehicleCount(@Param("brandId") String brandId, @Param("vehicleStatus") String vehicleStatus);

    /**
     * 获取年款及年款下的车型统计
     * @param seriesId
     * @param vehicleStatus
     * @return
     */
    List<ModelYear> getModelYearsWithVehicleCount(@Param("seriesId") String seriesId, @Param("vehicleStatus") String vehicleStatus);

    /**
     * 获取款型及款型下的车型统计
     * @param modelyearId
     * @param vehicleStatus
     * @return
     */
    List<Model> getModelsWithVehicleCount(@Param("modelyearId") String modelyearId, @Param("vehicleStatus") String vehicleStatus);

    /**
     * 查询所有符合条件的车型
     * @param paramMap
     * @return
     */
    List<Vehicle> getAllVehicles(Map<String,Object> paramMap);

    /**
     * 获取所有符合条件的车型数量
     * @param paramMap
     * @return
     */
    int getVehicleCount(Map<String,Object> paramMap);

    /**
     *
     * @param id
     * @return
     */
    Vehicle getVehicleById(@Param("id") String id);

    /**
     * 保存或更新车型
     * @param brand
     */
    void saveOrUpdate(VirtualBSYM brand);
}
