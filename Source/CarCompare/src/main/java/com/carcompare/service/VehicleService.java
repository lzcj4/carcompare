package com.carcompare.service;

import com.carcompare.dto.car.GetAllVehiclesInput;
import com.carcompare.dto.car.GetAllVehiclesOutput;
import com.carcompare.dto.car.Vehicle;
import com.carcompare.core.vehicles.Brand;
import com.carcompare.core.vehicles.Model;
import com.carcompare.core.vehicles.ModelYear;
import com.carcompare.core.vehicles.Series;
import com.carcompare.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VehicleService extends BaseService {

    @Autowired
    private VehicleMapper vehicleMapper;

    public List<Brand> getBrandsWithVehicleCount(String vehicleStatus){
        return this.vehicleMapper.getBrandsWithVehicleCount(vehicleStatus);
    }

    public List<Series> getSeriesWithVehicleCount(String brandId, String vehicleStatus){
        return  this.vehicleMapper.getSeriesWithVehicleCount(brandId, vehicleStatus);
    }

    public List<ModelYear> getModelYearsWithVehicleCount(String seriesId, String vehicleStatus){
        return  this.vehicleMapper.getModelYearsWithVehicleCount(seriesId, vehicleStatus);
    }

    public List<Model> getModelsWithVehicleCount(String modelyearId, String vehicleStatus){
        return  this.vehicleMapper.getModelsWithVehicleCount(modelyearId, vehicleStatus);
    }

    public GetAllVehiclesOutput getAllVehicles(GetAllVehiclesInput input){
        String paramMap=input.getParamMap();
        Map<String,Object> queryMap=com.alibaba.fastjson.JSON.parseObject(paramMap);

        if( hasQueryParams(queryMap)){
            queryMap.put("hasParams", "");
        }

        int pageStart = (input.getPageIndex()-1) * input.getPageSize();
        int pageSize = input.getPageSize();
        queryMap.put("pageStart", pageStart);
        queryMap.put("pageSize", pageSize);

        List<Vehicle> vehicles = this.vehicleMapper.getAllVehicles(queryMap);

        GetAllVehiclesOutput output = new GetAllVehiclesOutput();
        int totalCount = this.vehicleMapper.getVehicleCount(queryMap);
        output.setTotalCount(totalCount);
        output.setItems(vehicles);

        return output;
    }

    public Vehicle getVehicleDescriptionById(String id){
        Vehicle result =this.vehicleMapper.getVehicleById(id);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * 是否有查询参数
     * @param queryMap
     * @return
     */
    private boolean hasQueryParams(Map<String, Object> queryMap){
        if(queryMap.size() == 0){
            return false;
        }

        for (Object value : queryMap.values()) {
            if(value != ""){
                return true;
            }
        }

        return false;
    }
}
