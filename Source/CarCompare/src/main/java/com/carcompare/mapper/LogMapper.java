package com.carcompare.mapper;

import com.carcompare.core.logs.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
    List<Log> getAllLogs(@Param("username") String username,
                         @Param("type") String type,
                         @Param("beginTime")String beginTime,
                         @Param("endTime")String endTime);

    int addLog(Log log);
}
