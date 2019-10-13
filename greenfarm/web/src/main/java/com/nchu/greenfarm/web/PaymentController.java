package com.nchu.greenfarm.web;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.nchu.greenfarm.OrderOneService;
import com.nchu.greenfarm.model.OrderOne;
import com.nchu.greenfarm.web.config.AlipayConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class PaymentController {
    @RequestMapping("test")
    public String test(){
        return "toShow";
    }
    @RequestMapping("top")
    public String top(){
        return "top";
    }
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private OrderOneService orderOneService;

    @RequestMapping("alipay/callback/return")
    public String aliPayCallBackReturn(HttpServletRequest request){

        // 回调请求中获取支付宝参数
        String sign = request.getParameter("sign");
        String out_trade_no = request.getParameter("out_trade_no");

        // 通过支付宝的paramsMap进行签名验证，2.0版本的接口将paramsMap参数去掉了，导致同步请求没法验签
        if(StringUtils.isNotBlank(sign)){
            // 验签成功
            OrderOne orderOne = new OrderOne();
            orderOne.setOutTradeNo(out_trade_no);
            orderOne.setOrderTime(new Date());
            orderOne.setUserId(1);
            // 更新用户的支付状态
            orderOne.setOrderStatus(1);

            orderOneService.updateByOutTradeNo(orderOne);
            System.out.println("更新用户的支付状态");
            return "pay/finish";
        }else {
            return "index";
        }

    }

    @RequestMapping("alipay/submit")
    @ResponseBody
    public String alipay(@ModelAttribute("order") OrderOne order){
        System.out.println(order.getOrderTime()+"CC");
        // 获得一个支付宝请求的客户端(它并不是一个链接，而是一个封装好的http的表单请求)
        String form = null;
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request

        // 回调函数
        alipayRequest.setReturnUrl(AlipayConfig.return_payment_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_payment_url);

        Map<String,Object> map = new HashMap<>();
       // String out_trade_no = ""+System.currentTimeMillis();
        map.put("out_trade_no",order.getOutTradeNo());
        map.put("product_code","FAST_INSTANT_TRADE_PAY");
        map.put("total_amount",order.getOrderMoney());
        map.put("subject",order.getOrderDescribe());

        String param = JSON.toJSONString(map);

        alipayRequest.setBizContent(param);

        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

//            System.out.println(form);
//            OrderOne orderOne = new OrderOne();
//           // orderOne.setOrderId(1);
//            orderOne.setOrderNum(1);
//            orderOne.setUserId(1);
//            orderOne.setOrderMoney(order.getMoney());
//            orderOne.setProduceId(1);
//            orderOne.setOrderStatus(1);
//           orderOne.setOrderTime(new Date());
//            orderOne.setOutTradeNo(out_trade_no);
//            orderOneService.insert(orderOne);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    @RequestMapping("order/index")
    public String index(Model model){
        List<OrderOne> orderList = orderOneService.selectUserById(1);
//        for(int i = 1;i<= 10;i++){
//            Order order = new Order();
//            order.setDescription("商品消息"+i);
//            order.setMoney(i*0.01);
//            order.setName("商品"+i);
//            order.setStatus(i%2);
//            orderList.add(order);
//        }


        model.addAttribute("list",orderList);
        return "pay/order";
    }
}
