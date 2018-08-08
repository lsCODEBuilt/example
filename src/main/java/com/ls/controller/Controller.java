package com.ls.controller;

import com.ls.entity.ShowInfo;
import com.ls.service.ShowInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "showinfo")
@RequestMapping(value = "/showInfo")
public class Controller {

    @Autowired
    ShowInfoService showInfoService;

    @ApiOperation(value = "")
    @RequestMapping("/maps")
    public List<Map<String,Object>> getAllMaps(){
        return showInfoService.getAllMaps();
    }

    @ApiOperation(value = "")
    @RequestMapping("/objects")
    public List<ShowInfo> getAllObjects(){
        return showInfoService.getAllObjects();
    }
}
