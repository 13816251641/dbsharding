package com.lujieni.dbsharding.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    int insertUser(@Param("userId")Long userId,@Param("fullname")String fullname);


}
