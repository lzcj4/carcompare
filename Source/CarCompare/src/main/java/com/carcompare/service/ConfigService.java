package com.carcompare.service;

import com.carcompare.dto.common.KeyValueDto;
import com.carcompare.core.features.FeatureGroup;
import com.carcompare.core.features.FeatureItem;
import com.carcompare.core.vehicles.Brand;
import com.carcompare.mapper.ConfigMapper;
import com.carcompare.mapper.FeatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 基础数据服务（如：品牌、车系、特征等）
 */
@Service
public class ConfigService extends BaseService {
    @Autowired
    FeatureMapper featureMapper;

    @Autowired
    ConfigMapper configMapper;

    /**
     * 获取品牌列表，并以品牌字母分组
     * @return
     */
    public Map<String, Object> getBrandGroupByInitials(){
        Map<String, Object> map = new HashMap<>();
        map.put("brandSource", this.getBrandSource());
        return map;
    }

    public List<KeyValueDto> getSeriesByBrandId(String brandId){
        return configMapper.getSeriesByBrandId(brandId);
    }

    public List<KeyValueDto> getModelYearsBySeriesId(String seriesId){
        return configMapper.getModelYearsBySeriesId(seriesId);
    }

    public List<KeyValueDto> getModelsByYearId(String modelyearId){
        return configMapper.getModelsByYearId(modelyearId);
    }

    /**
     * 获取所有特征列
     * 说明：每一组字典的key都以groupCode + 'Items'形式，如：geshankuangxingItems
     * @return
     */
    public Map<String, Object> getAllFeatures(){
        List<FeatureGroup> featureGroups = this.featureMapper.getAllFeatureGroups();

        Map<String, Object> map = new HashMap<>();
        for (FeatureGroup group : featureGroups){
            String key = group.getCode() + "Items";
            map.put(key, this.getFeatureItems(group.getItems()));
        }

        return map;
    }

    /**
     * 获取品牌数据
     * @return
     */
    private List<Map<String, Object>> getBrandSource(){
        List<Brand> brands = this.configMapper.getAllBrands();

        brands.sort(Comparator.comparing(Brand::getInitials));

        List<Map<String, Object>> groupMaplist = new ArrayList<>();

        Map<String,Object> groupMap = null;
        List<Map<String, Object>> brandMapList = null;

        for (Brand item : brands){
            if(groupMap == null || !groupMap.get("name").toString().equals(item.getInitials())){
                groupMap = new HashMap<>();
                groupMap.put("name", item.getInitials());
                groupMap.put("value", item.getInitials());
                brandMapList = new ArrayList<>();
                groupMap.put("itemSource", brandMapList);
                groupMaplist.add(groupMap);
            }

            Map<String, Object> brandMap = new HashMap<>();
            brandMap.put("value", item.getId());
            brandMap.put("name", item.getName());
            brandMapList.add(brandMap);
        }
        return groupMaplist;
    }

    /**
     * 根据特征组编码获取特征项
     * @return
     */
    private List<Map<String, Object>> getFeatureItems(List<FeatureItem> featureItems){
        List<Map<String, Object>> list = new ArrayList<>();
        for (FeatureItem item : featureItems){
            Map<String, Object> map = new HashMap<>();
            map.put("name", item.getName());
            map.put("value", item.getId());
            map.put("icon", item.getCode());
            list.add(map);
        }
        return  list;
    }
}
