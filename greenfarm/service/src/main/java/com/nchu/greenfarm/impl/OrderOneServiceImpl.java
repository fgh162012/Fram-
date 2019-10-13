package com.nchu.greenfarm.impl;

import com.nchu.greenfarm.OrderOneService;
import com.nchu.greenfarm.mappers.OrderOneMapper;
import com.nchu.greenfarm.model.OrderOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SecondMenuController
 * @Description: java类作用描述
 * @Author: 3162748949fgh
 * @CreateDate: 2019/1/6 10:54
 * @UpdateUser: 3162748949fgh
 * @UpdateDate: 2019/1/6 10:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 **/
@Service
public class OrderOneServiceImpl implements OrderOneService {
    @Autowired
    private OrderOneMapper orderOneMapper;
    @Override
    public int insert(OrderOne order) {
        return orderOneMapper.insert(order);
    }

    @Override
    public int updateByOutTradeNo(OrderOne order) {
        return orderOneMapper.updateByOutTradeNo(order);
    }

//    @Override
//    public int updateByorderId(OrderOne order) {
//        return orderOneMapper.updateByorderId(order);
//    }

    @Override
    public List<OrderOne> selectUserById(Integer userId) {
        return orderOneMapper.selectUserById(userId);
    }
}
