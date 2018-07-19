package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.dto.car.GetAllVehiclesInput;
import com.carcompare.dto.car.GetAllVehiclesOutput;
import com.carcompare.dto.car.Vehicle;
import com.carcompare.core.vehicles.Brand;
import com.carcompare.core.vehicles.Model;
import com.carcompare.core.vehicles.ModelYear;
import com.carcompare.core.vehicles.Series;
import com.carcompare.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vService) {
        this.vehicleService = vService;
    }

    @GetMapping("/brands_with_vehicle_count")
    public ApiResult<List<Brand>> getBrandsWithVehicleCount(@RequestParam("status") String status){
        List<Brand> outPut = this.vehicleService.getBrandsWithVehicleCount(status);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("/series_with_vehicle_count")
    public ApiResult<List<Series>> getSeriesWithVehicleCount(@RequestParam("brandId") String brandId, @RequestParam("status") String status){
        List<Series> outPut = this.vehicleService.getSeriesWithVehicleCount(brandId, status);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("/modelyears_with_vehicle_count")
    public ApiResult<List<ModelYear>> getModelYearsWithVehicleCount(@RequestParam("seriesId") String seriesId, @RequestParam("status") String status){
        List<ModelYear> outPut=this.vehicleService.getModelYearsWithVehicleCount(seriesId, status);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("/models_with_vehicle_count")
    public ApiResult<List<Model>> getModelsWithVehicleCount(@RequestParam("modelyearId") String modelyearId, @RequestParam("status") String status){
        List<Model> outPut=this.vehicleService.getModelsWithVehicleCount(modelyearId, status);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("")
    public ApiResult getVehicles(GetAllVehiclesInput input){
        GetAllVehiclesOutput outPut=this.vehicleService.getAllVehicles(input);
        return new ApiResult(true, outPut);
    }

    @GetMapping("/{parentid}/description")
    public ApiResult getVehicleDescriptionById(@PathVariable String parentid){
        Map<String, Object> map = new HashMap<>();

        Vehicle outPut=this.vehicleService.getVehicleDescriptionById(parentid);
        String description=outPut.getSeries().concat(" ").concat(outPut.getYear()).concat(" ").concat(outPut.getModel());
        map.put("description",description);
        return new ApiResult(true, map);
    }
}
