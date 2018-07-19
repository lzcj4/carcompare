package com.carcompare.service;

import com.carcompare.core.features.FeatureGroup;
import com.carcompare.mapper.FeatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 车型特征服务类
 */
@Service
public class FeatureService extends BaseService {
    @Autowired
    FeatureMapper featureMapper;

    public FeatureGroup getFeatureGroupByCode(String code){
        return  this.featureMapper.getFeatureGroupByCode(code);
    }
}
