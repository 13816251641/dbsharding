package com.lujieni.dbsharding.simple.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OrderDao {

     /**
      * 插入订单
      * @param price
      * @param userId
      * @param status
      * @return
      */
     int insertOrder(@Param("price")BigDecimal price,@Param("userId") Long userId,@Param("status") String status);

     /**
      * 根据id列表查询订单
      * @return
      */
     List<Map> selectOrderById(@Param("ids") List<Long> ids);

     /**
      * 根据id列表查询订单
      * @return
      */
     List<Map> selectOrderByIdAndUserId(@Param("ids") List<Long> ids,@Param("userId") Long userId);


}
