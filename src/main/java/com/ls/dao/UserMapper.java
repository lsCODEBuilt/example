package com.ls.dao;

import com.ls.entity.User;

public interface UserMapper {
    User selectByPrimaryKey(String userId);
}