package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.dto.car.CarOutput;
import com.carcompare.dto.car.ImageParameter;
import com.carcompare.core.vehicles.Feature;
import com.carcompare.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarController extends BaseController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars/{id}")
    public ApiResult Get(@PathVariable("id") String id) {
        CarOutput carOutput = carService.get(id);
        if (!id.isEmpty()) {
            return new ApiResult(true, carOutput);
        } else {
            return new ApiResult(false, "操作失败");
        }
    }

    @GetMapping("/cars/{bid}/{sid}/{yid}/{mid}")
    public ApiResult Get(@PathVariable("bid") String bid,@PathVariable("sid") String sid,
                         @PathVariable("yid") String yid,@PathVariable("mid") String mid) {
        return new ApiResult(true, carService.exists(bid,sid,yid,mid));
    }
    @DeleteMapping("/cars/delete/{id}")
    public ApiResult delete(@PathVariable("id") String id) {
        String deketeId = carService.delete(id);
        if (!id.isEmpty()) {
            return new ApiResult(true, deketeId);
        } else {
            return new ApiResult(false, "操作失败");
        }
    }

    @PostMapping("/cars/add")
    public ApiResult AddCar(Feature input) {
        String id = carService.saveFeature(input);
        return new ApiResult(true, id);
    }

    @PostMapping("/cars/updateAndAudit/{status}")
    public ApiResult updateAndAuditCar(Feature input,@PathVariable("status") int status) {
        String id = carService.updateAndAuditFeature(input,status);
        return new ApiResult(true, id);
    }

    @PostMapping("/cars/update")
    public ApiResult Update(Feature input) {
        String id = carService.updateFeature(input);
        return new ApiResult(true, id);
    }

    @PostMapping("/cars/audit/{id}")
    public ApiResult Audit(@PathVariable("id") String id) {
        boolean audited = carService.auditById(id,1);
        return new ApiResult(audited, id);
    }

    @DeleteMapping("/image/{id}")
    public ApiResult deleteImage(@PathVariable BigInteger id) {
        return new ApiResult(true, carService.deleteImageById(id));
    }

    @PostMapping(value = "/images/{id}", consumes = {"multipart/form-data"})
    public ApiResult Post(@RequestPart("files") MultipartFile[] files, @RequestPart("imgeIds") BigInteger[] imageIds,
                          @RequestPart("parameters") Map<String, ImageParameter[]> parameters, @PathVariable String id) {

        Collection<String> result = carService.addImages(files, imageIds, parameters, id);
        boolean success = true;
        if (success) {
            return new ApiResult(true, result);
        } else {
            return new ApiResult(false, "操作失败");
        }
    }
}

