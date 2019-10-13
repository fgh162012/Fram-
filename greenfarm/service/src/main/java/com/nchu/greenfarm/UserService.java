package com.nchu.greenfarm;


import com.nchu.greenfarm.model.User;

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
public interface UserService {

    int insert(User record);
    User selectByName(String name);
}
