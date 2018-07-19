package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.dto.log.GetAllLogsInput;
import com.carcompare.dto.log.GetAllLogsOutput;
import com.carcompare.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogController extends BaseController{
    @Autowired
    LogService logService;

    @GetMapping("/types")
    public ApiResult getLogTypes(){
        return new ApiResult(true, logService.getLogTypes());
    }

    @GetMapping("")
    public ApiResult getAllLogs(GetAllLogsInput input){
        GetAllLogsOutput output = this.logService.getAllLogs(input);
        return  new ApiResult(true, output);
    }
}
