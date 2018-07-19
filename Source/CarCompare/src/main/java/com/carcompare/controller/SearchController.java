package com.carcompare.controller;

import com.carcompare.Entry;
import com.carcompare.base.ApiResult;
import com.carcompare.dto.car.GetAllVehiclesInput;
import com.carcompare.dto.car.GetAllVehiclesOutput;
import com.carcompare.dto.car.Vehicle;
import com.carcompare.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/search")
public class SearchController extends BaseController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ApiResult searchVehicles(GetAllVehiclesInput input){
        GetAllVehiclesOutput outPut = this.vehicleService.getAllVehicles(input);
        List<Vehicle> vehicles = outPut.getItems();

        List<Map<String, Object>> items = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        List<Vehicle> vehicleList = null;

        for (Vehicle vehicle : vehicles) {
            String key = vehicle.getBrandId();
            if (!map.containsKey(key)) {
                Map<String, Object> brandMap = new HashMap<>();
                vehicleList = new ArrayList<>();

                brandMap.put("id", vehicle.getBrandId());
                brandMap.put("initials", vehicle.getInitials());
                brandMap.put("name", vehicle.getBrand());

                Map<String, Object> itemMap = new HashMap<>();
                itemMap.put("brand", brandMap);
                itemMap.put("items", vehicleList);

                map.put(key, itemMap);
                items.add(itemMap);
            }

            vehicleList.add(vehicle);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", outPut.getTotalCount());
        resultMap.put("items", items);

        return new ApiResult(true, resultMap);
    }

    /**
     * 对车型结果排序
     * @param vehicles
     */
//    private void vechiclesSort(List<Vehicle> vehicles){
//
//        /*
//         * int compare(Vehicle o1, Vehicle o2) 返回一个基本类型的整型，
//         * 返回负数表示：o1 小于o2，
//         * 返回0 表示：o1和o2相等，
//         * 返回正数表示：o1大于o2。
//         */
//        Collections.sort(vehicles, (Vehicle o1, Vehicle o2)->{
//            int result = o1.getInitials().compareTo(o2.getInitials());
//            if(result != 0){
//                return result;
//            }
//
//            return  o1.getBrandId().compareTo(o2.getBrandId());
//        });
//
//    }
}
