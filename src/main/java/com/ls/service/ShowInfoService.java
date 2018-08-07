package com.ls.service;

import com.ls.dao.ShowInfoDao;
import com.ls.entity.ShowInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ShowInfoService {

    @Autowired
    private ShowInfoDao showInfoDao;

    @Transactional
    public List<Map<String,Object>> getAllMaps(){
        return showInfoDao.queryAllMaps();
    }

    public  List<ShowInfo> getAllObjects(){
        return showInfoDao.queryAllObjects();
    }

}
