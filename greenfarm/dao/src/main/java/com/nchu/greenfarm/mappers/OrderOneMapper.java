package com.nchu.greenfarm.mappers;

import com.nchu.greenfarm.model.OrderOne;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderOneMapper {


    int insert(OrderOne record);

    List<OrderOne> selectUserById(Integer userId);

    int updateByorderId(OrderOne order);

    int updateByOutTradeNo(OrderOne order);
}