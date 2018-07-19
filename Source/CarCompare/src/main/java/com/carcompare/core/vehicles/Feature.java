package com.carcompare.core.vehicles;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Feature  implements Serializable {
    private String id,brand,series,modelyear,type,model,xiangxing,chebiaokuangxing,chebiaoweizhi,
    geshanjiegou,shanggeshanxing,geshanqianwu,qianbaohuban,frontxinnengyuanbiao,bzhuyanse,geshanqianzhao,
    houchuangxiaochuang,houchuangxiayan,menbashou,menbashouyanse,menxianwei,lungulunfu,weidengxingzhuang,
    youxianggaiwei,sidexinnengyuanbiao,houchuangkuangxing,weidengweizhi,haopaikuangmei,haopaiweizhi,
    haopaikuangxing,gaoweizhidongdeng,houfangwudeng,paiqikouwei,backxinnengyuanbiao,waiguabeitai,
    dingmifengtiao,weiyiraoliu,xinglizhijia,beijitianxian,brandName,saishi,chechuangneikuang;
    private int hotindex,creator,hotindexSort,yearCode;
    private char brandInitials;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
}
