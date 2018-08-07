package com.ls.dao;

import com.ls.entity.ShowInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShowInfoDao {

    @Select("select * from b_show_info")
    List<Map<String,Object>> queryAllMaps();

    @Select("select * from b_show_info")
    List<ShowInfo> queryAllObjects();
}
