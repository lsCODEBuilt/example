package com.ls.controller;

import com.ls.commom.service.RedisService;
import com.ls.entity.ShowInfo;
import com.ls.service.ShowInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



@Api(value = "showinfo")
@Controller
@RequestMapping(value = "/showInfo")
public class UserController {

    @Autowired
    ShowInfoService showInfoService;
    @Autowired
    RedisService redisService;

    @ApiOperation(value = "")
    @RequestMapping("/maps")
    @ResponseBody
    public List<Map<String,Object>> getAllMaps(){
        return showInfoService.getAllMaps();
    }

    @ApiOperation(value = "")
    @RequestMapping("/objects")
    @ResponseBody
    public List<ShowInfo> getAllObjects(){
        return showInfoService.getAllObjects();
    }

    @ApiOperation(value = "")
    @RequestMapping(value = "/objects/value/{value}",method = RequestMethod.GET)
    @ResponseBody
    public void setRedis(@ApiParam(name = "value",value = "值")@RequestParam String value){
        redisService.set("redisValue",value);


        System.out.println(redisService.get("redisValue"));
    }

    @RequestMapping(value = "/html")
    public String forwardHtml(Model model){
        model.addAttribute("name","world");

        return "index";
    }
}
