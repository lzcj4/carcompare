package com.carcompare.mapper;

import com.carcompare.core.features.FeatureGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeatureMapper {
    /**
     * 根据特征组编码获取数据
     * @param code
     * @return
     */
    FeatureGroup getFeatureGroupByCode(@Param("code") String code);

    /**
     * 获取所有特征组
     * @return
     */
    List<FeatureGroup> getAllFeatureGroups();
}