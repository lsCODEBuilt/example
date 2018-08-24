package com.ls.controller;

import com.ls.commom.dto.Result;
import com.ls.commom.service.RedisService;
import com.ls.commom.untils.JwtUtils;
import com.ls.entity.ShowInfo;
import com.ls.entity.User;
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
import java.util.HashMap;
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


    @ApiOperation(value = "")
    @RequestMapping(value = "/objects/key/{key}",method = RequestMethod.GET)
    @ResponseBody
    public Result getRedisKey(@ApiParam(name = "key",value = "键")@RequestParam String key){
        User u =  new User();
        u.setPhone("114544555");
        u.setUserId("1111111");


        Map<String,Object> map = new HashMap<String, Object>();
        map.put("phone",15258744);
        map.put("user",u);
        map.put("id",111111);
        return Result.success(JwtUtils.encryptToken("user", map));
        //return Result.success(redisService.get(key));
    }


    @RequestMapping(value = "/html")
    public String forwardHtml(Model model){
        model.addAttribute("name","world");

        return "index";
    }
}
