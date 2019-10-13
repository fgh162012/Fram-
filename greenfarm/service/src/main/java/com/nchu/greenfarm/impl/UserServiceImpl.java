package com.nchu.greenfarm.impl;


import com.nchu.greenfarm.UserService;
import com.nchu.greenfarm.mappers.UserMapper;
import com.nchu.greenfarm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
