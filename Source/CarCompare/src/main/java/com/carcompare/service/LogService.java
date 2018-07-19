package com.carcompare.service;

import com.carcompare.dto.log.GetAllLogsInput;
import com.carcompare.dto.log.GetAllLogsOutput;
import com.carcompare.core.logs.Log;
import com.carcompare.mapper.LogMapper;
import com.carcompare.utils.LogUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LogService extends BaseService {
    @Resource
    private LogMapper logMapper;

    /**
     * 获取日志类型列表
     * @return
     */
    public List<String> getLogTypes(){
        return LogUtil.getLogTypes();
    }

    /**
     * 获取日志
     * @param input
     * @return
     */
    public GetAllLogsOutput getAllLogs(GetAllLogsInput input){
        PageHelper.startPage(input.getPageIndex(), input.getPageSize());

        List<Log> logs = this.logMapper.getAllLogs(input.getUsername(), input.getType(),input.getBeginTime(),input.getEndTime());

        PageInfo<Log> pageInfo = new PageInfo<Log>(logs);

        GetAllLogsOutput output = new GetAllLogsOutput();
        output.setTotalCount(pageInfo.getTotal());
        output.setItems(logs);

        return output;
    }

}
