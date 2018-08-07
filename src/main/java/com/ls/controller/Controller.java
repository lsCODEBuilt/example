package com.ls.controller;

import com.ls.entity.ShowInfo;
import com.ls.service.ShowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/showInfo")
public class Controller {

    @Autowired
    ShowInfoService showInfoService;

    @RequestMapping("/maps")
    public List<Map<String,Object>> getAllMaps(){
        return showInfoService.getAllMaps();
    }

    @RequestMapping("/objects")
    public List<ShowInfo> getAllObjects(){
        return showInfoService.getAllObjects();
    }
}
