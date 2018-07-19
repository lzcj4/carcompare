package com.carcompare.mapper;

import com.carcompare.core.vehicles.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

public interface VehicleFeaturePictureMapper {

    @Select("select * from biz_vehicle_picture")
    List<FeaturePicture> getAllFeature();

    @Select("select * from biz_vehicle_picture where parentid = #{vehicleId}")
    List<FeaturePicture> getAllByVehicleId(String vehicleId);

    @Select("select * from biz_vehicle_picture where id = #{id}")
    FeaturePicture getById(BigInteger id);

    @Delete("delete from biz_vehicle_picture where id = #{id}")
    void deleteById(BigInteger id);

    @Delete("delete from biz_vehicle_picture where parentid = #{vehicleId}")
    void deleteByVehicleId(String vehicleId);

    @Insert("insert into biz_vehicle_picture(name,path,originname,bigname,mediumname,smallname,parentid,type)" +
            " values(#{name},#{path},#{originname},#{bigname},#{mediumname},#{smallname},#{parentid},#{type})")
    //@SelectKey(statement = "call identity()",keyProperty = "id", before = false,resultType = BigInteger.class)
    void insertPicture(FeaturePicture feature);

    @Insert("update biz_vehicle_picture set name =#{name},path =#{path},originname =#{originname},bigname =#{bigname}" +
            ",mediumname =#{mediumname},smallname =#{smallname},parentid =#{parentid},type =#{type} where id = #{id})")
    void updatePicture(FeaturePicture feature);
}
