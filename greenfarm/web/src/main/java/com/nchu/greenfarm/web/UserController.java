package com.nchu.greenfarm.web;


import com.nchu.greenfarm.UserService;
import com.nchu.greenfarm.model.User;
import com.nchu.greenfarm.util.MD5Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;

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
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(@Param("password") String password, @Param("username") String username, HttpSession session){
        if( password != "" && username != ""){
            System.out.println(username);
            User user = userService.selectByName(username);
            if(user.getUserPassword().equals(MD5Utils.md5(password))){
                session.setAttribute("username",user);
                return "user/index";
            }
        }
        return "redirect: /";
    }
    @RequestMapping("finish")
    public String finish(){
        return "pay/finish";
    }
    /*
    *测试Md5算法的
    *
    * */
    @RequestMapping("select")
    public void select(){
        User user = new User();
        user.setUserEmail("3212423@qq.com");
        user.setUserName("cc");
        user.setUserTelephone("12345678901");
        user.setUserType(1);
        user.setUserPassword(MD5Utils.md5("123456"));
        User user1 = userService.selectByName("cc");
        if (user1.getUserName().equals(user.getUserName())){
            if(user.getUserPassword().equals(user1.getUserPassword())){
                System.out.println(user.getUserPassword()+"登录成功"+user1.getUserPassword());
            }else{
                System.out.println(user.getUserPassword()+"登录失败"+user1.getUserPassword());
            }
        }
    }
}
