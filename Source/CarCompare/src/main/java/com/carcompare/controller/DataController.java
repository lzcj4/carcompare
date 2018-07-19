package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.service.DataService;
import com.carcompare.service.InOutDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    InOutDataService inOutDataService;

    @GetMapping("/brands")
    public ApiResult<Map<String, Object>> getBrandGroupByInitials(){
        Map<String, Object> map = this.dataService.getBrandGroupByInitials();
        return new ApiResult<>(true, map);
    }

    @GetMapping("/features")
    public ApiResult<Map<String, Object>> getAllFeatures(){
        Map<String, Object> map = this.dataService.getAllFeatures();

        return new ApiResult<>(true, map);
    }

    /**
     * 获取车的年份
     * @return
     */
    @GetMapping("/years")
    public ApiResult<List<Integer>> getVehicleYears(){
        List<Integer> years = new ArrayList<>();

        int startYear = 1990;
        int endYear =  Calendar.getInstance().get(Calendar.YEAR);

        for (Integer year = endYear; year>startYear; year--){
            years.add(year);
        }

        return new ApiResult<>(true, years);
    }

    @PostMapping(value = "/importbsymdata")
    public ApiResult importBSYMData(MultipartFile file,int startRowIndex,int sheetIndex){
        ApiResult result =new ApiResult();
        try{
          result.setSuccess(true);
          result.setData(inOutDataService.importExcel(file,startRowIndex,sheetIndex));
        }catch (Exception e){
            result.setSuccess(false);
            result.setData("导入失败！");
        }
        return  result;
    }
}
