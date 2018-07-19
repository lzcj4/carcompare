package com.carcompare.mapper;

import com.carcompare.core.vehicles.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VehicleFeatureMapper {

    String updateSql="brand = #{brand},series = #{series},modelyear =#{modelyear},type=#{type},model = #{model}," +
            "xiangxing = #{xiangxing},chebiaokuangxing = #{chebiaokuangxing},chebiaoweizhi =#{chebiaoweizhi},geshanqianzhao=#{geshanqianzhao}," +
            "geshanjiegou = #{geshanjiegou},shanggeshanxing =#{shanggeshanxing},geshanqianwu =#{geshanqianwu}," +
            "qianbaohuban =#{qianbaohuban},frontxinnengyuanbiao =#{frontxinnengyuanbiao},bzhuyanse =#{bzhuyanse}," +
            "houchuangxiaochuang =#{houchuangxiaochuang},houchuangxiayan =#{houchuangxiayan},menbashou =#{menbashou}," +
            "menbashouyanse =#{menbashouyanse},menxianwei =#{menxianwei},lungulunfu =#{lungulunfu},weidengxingzhuang=#{weidengxingzhuang}," +
            "youxianggaiwei =#{youxianggaiwei},sidexinnengyuanbiao =#{sidexinnengyuanbiao},houchuangkuangxing =#{houchuangkuangxing}," +
            "weidengweizhi =#{weidengweizhi},haopaikuangmei =#{haopaikuangmei},haopaiweizhi =#{haopaiweizhi}," +
            "haopaikuangxing =#{haopaikuangxing},gaoweizhidongdeng =#{gaoweizhidongdeng},houfangwudeng =#{houfangwudeng}," +
            "paiqikouwei =#{paiqikouwei},backxinnengyuanbiao =#{backxinnengyuanbiao},waiguabeitai =#{waiguabeitai}," +
            "dingmifengtiao =#{dingmifengtiao},weiyiraoliu =#{weiyiraoliu},xinglizhijia =#{xinglizhijia}," +
            "beijitianxian =#{beijitianxian},hotindex =#{hotindex},hotindexSort=#{hotindexSort},brandInitials=#{brandInitials}," +
            "yearCode=#{yearCode},brandName=#{brandName},`status`=0,saishi=#{saishi},chechuangneikuang=#{chechuangneikuang}";
    String fieldsSql="id,brand,series,modelyear,type,model,xiangxing,chebiaokuangxing,chebiaoweizhi," +
            "geshanjiegou,shanggeshanxing,geshanqianwu,qianbaohuban,frontxinnengyuanbiao,bzhuyanse,geshanqianzhao," +
            "houchuangxiaochuang,houchuangxiayan,menbashou,menbashouyanse,menxianwei,lungulunfu,weidengxingzhuang," +
            "youxianggaiwei,sidexinnengyuanbiao,houchuangkuangxing,weidengweizhi,haopaikuangmei,haopaiweizhi," +
            "haopaikuangxing,gaoweizhidongdeng,houfangwudeng,paiqikouwei,backxinnengyuanbiao,waiguabeitai," +
            "dingmifengtiao,weiyiraoliu,xinglizhijia,beijitianxian,hotindex,creator,createtime,hotindexSort," +
            "brandInitials,yearCode,brandName,`status`,saishi,chechuangneikuang";
    String valuesSql="#{id},#{brand},#{series},#{modelyear},#{type},#{model},#{xiangxing},#{chebiaokuangxing},#{chebiaoweizhi}," +
            "#{geshanjiegou},#{shanggeshanxing},#{geshanqianwu},#{qianbaohuban},#{frontxinnengyuanbiao},#{bzhuyanse},#{geshanqianzhao}," +
            "#{houchuangxiaochuang},#{houchuangxiayan},#{menbashou},#{menbashouyanse},#{menxianwei},#{lungulunfu},#{weidengxingzhuang}," +
            "#{youxianggaiwei},#{sidexinnengyuanbiao},#{houchuangkuangxing},#{weidengweizhi},#{haopaikuangmei},#{haopaiweizhi}," +
            "#{haopaikuangxing},#{gaoweizhidongdeng},#{houfangwudeng},#{paiqikouwei},#{backxinnengyuanbiao},#{waiguabeitai}," +
            "#{dingmifengtiao},#{weiyiraoliu},#{xinglizhijia},#{beijitianxian},#{hotindex},#{creator},#{createtime}," +
            "#{hotindexSort},#{brandInitials},#{yearCode},#{brandName},0,#{saishi},#{chechuangneikuang}";

    @Select("select * from biz_vehicle where brand = #{bid} and series = #{sid} and modelyear = #{yid} and model = #{mid}")
    List<Feature> getAllFeatureByBSYM(@Param("bid") String bid,@Param("sid") String sid,@Param("yid") String yid,@Param("mid") String mid);

    @Select("select * from biz_vehicle")
    List<Feature> getAllFeature();

    @Select("select * from biz_vehicle where id = #{id}")
    Feature getById(String id);

    @Delete("delete from biz_vehicle where id = #{id}")
    void  deleteById(String id);


    @Insert("insert into biz_vehicle("+VehicleFeatureMapper.fieldsSql+") values("+VehicleFeatureMapper.valuesSql+")")
    @SelectKey(before = true, keyProperty = "hotindexSort,brandInitials,brandName,yearCode",
            statement = "SELECT 5 - ${hotindex} + 1 AS hotindexSort,b.initials  AS brandInitials,b.`Name` as brandName,c.code AS yearCode"+
            " FROM  biz_brand  b ,biz_year c WHERE b.id = #{brand} and c.id=#{modelyear}",
            resultType = Feature.class)
    void insertFeature(Feature feature);

    @Update("update biz_vehicle set "+VehicleFeatureMapper.updateSql+" where id = #{id}")
    @SelectKey(before = true, keyProperty = "hotindexSort,brandInitials,brandName,yearCode",
            statement = "SELECT 5 - ${hotindex} + 1 AS hotindexSort,b.initials  AS brandInitials,b.`Name` as brandName,c.code AS yearCode"+
                    " FROM  biz_brand  b ,biz_year c WHERE b.id = #{brand} and c.id=#{modelyear}",
            resultType = Feature.class)
    void updateFeature(Feature feature);

    @Update("update biz_vehicle set " +VehicleFeatureMapper.updateSql+
            "where brand = #{brand} and series = #{series} and modelyear =#{modelyear} and model = #{model}")
    @SelectKey(before = true, keyProperty = "hotindexSort,brandInitials,brandName,yearCode",
            statement = "SELECT 5 - ${hotindex} + 1 AS hotindexSort,b.initials  AS brandInitials,b.`Name` as brandName,c.code AS yearCode"+
                    " FROM  biz_brand  b ,biz_year c WHERE b.id = #{brand} and c.id=#{modelyear}",
            resultType = Feature.class)
    void updateFeatureByBSYM(Feature feature);

    @Update("update biz_vehicle set `status` = #{audit}  WHERE id = #{id}")
    void  auditById(@Param("id")String id,@Param("audit") int auditedPass);

    @Update("UPDATE  biz_vehicle b " +
            " SET b.image = IF(#{imagePath} IS NULL OR #{imagePath} ='',(SELECT CONCAT(p.path,'/',p.smallname) from biz_vehicle_picture p WHERE p.parentid = #{id} LIMIT 1),#{imagePath}) " +
            " WHERE b.id = #{id}")
    void  updateDisplayImage(@Param("id")String id,@Param("imagePath") String imagePath);
}
