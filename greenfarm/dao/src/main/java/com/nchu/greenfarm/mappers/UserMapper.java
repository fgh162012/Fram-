package com.nchu.greenfarm.mappers;

import com.nchu.greenfarm.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    int insert(User record);


    User selectByName(String name);


}