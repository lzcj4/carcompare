package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.core.menus.Menu;
import com.carcompare.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController extends BaseController {
    @Autowired
    MenuService menuService;

    @GetMapping("/leaf")
    public ApiResult getLeafMenus(){
        List<Menu> menus = this.menuService.getLeafMenus();
        return  new ApiResult(true, menus);
    }
}
