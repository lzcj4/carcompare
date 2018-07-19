package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.dto.common.KeyValueDto;
import com.carcompare.service.ConfigService;
import com.carcompare.service.InOutDataService;
import com.carcompare.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/** 数据配置服务
 *
 */
@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private InOutDataService inOutDataService;

    /**
     * 获取系统菜单
     * @return
     */
    @GetMapping("/menus")
    public ApiResult getSystemMenus() {
        return new ApiResult(true, menuService.getMenusByCurrentUser());
    }

    @GetMapping("/brands")
    public ApiResult<Map<String, Object>> getBrandGroupByInitials(){
        return new ApiResult<>(true, this.configService.getBrandGroupByInitials());
    }

    @GetMapping("/brand/{brandId}/series")
    public ApiResult<List<KeyValueDto>> getSeriesByBrandId(@PathVariable("brandId") String brandId){
        List<KeyValueDto> outPut = this.configService.getSeriesByBrandId(brandId);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("/series/{seriesId}/modelyears")
    public ApiResult<List<KeyValueDto>> getModelYearsBySeriesId(@PathVariable String seriesId){
        List<KeyValueDto> outPut=this.configService.getModelYearsBySeriesId(seriesId);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("/modelyears/{modelyearId}/models")
    public ApiResult<List<KeyValueDto>> getModelsByYearId(@PathVariable String modelyearId){
        List<KeyValueDto> outPut=this.configService.getModelsByYearId(modelyearId);
        return new ApiResult<>(true, outPut);
    }

    @GetMapping("/features")
    public ApiResult<Map<String, Object>> getAllFeatures(){
        return new ApiResult<>(true, this.configService.getAllFeatures());
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
    public ApiResult importBSYMData(MultipartFile file, int startRowIndex, int sheetIndex){
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
