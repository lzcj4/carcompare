package com.carcompare.utils;

import com.carcompare.core.logs.Log;
import com.carcompare.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 操作日志工具类
 */
@Component
public class LogUtil {
    public static String USER_LOGIN = "用户登录";

    public static String USER_LOGOUT = "用户登出";

    public static String VEHICLE_ADD = "车型录入";

    public static String VEHICLE_Audited = "车型审核";

    public static String VEHICLE_Audited_Save = "车型审核提交";

    public static String VEHICLE_UPDATE = "车型修改";

    public static String VEHICLE_DELETE = "车型删除";

    @Autowired
    private LogMapper logMapper;

    public static List<String> getLogTypes(){
        List<String> types = new LinkedList<>();
        types.add(LogUtil.USER_LOGIN);
        types.add(LogUtil.USER_LOGOUT);
        types.add(LogUtil.VEHICLE_ADD);
        types.add(LogUtil.VEHICLE_DELETE);
        types.add(LogUtil.VEHICLE_UPDATE);
        //types.add(LogUtil.VEHICLE_Audited); //预留功能，暂时屏蔽
        types.add(LogUtil.VEHICLE_Audited_Save);
        return types;
    }

    public void AddLog(Log log){
        this.logMapper.addLog(log);
    }

    public void AddLog(String logType,String description,String userName){
        Log log = new Log();
        log.setUsername(userName);
        log.setTime(new Date());
        log.setType(logType);
        log.setDescription(description);
        AddLog(log);
    }
}
