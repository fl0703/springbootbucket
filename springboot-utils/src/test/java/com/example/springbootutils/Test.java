package com.example.springbootutils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.utils.DateUtil;
import com.example.utils.StringUtil;
import com.example.utils.http.CommunicationUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 */
public class Test {

    @org.junit.Test
    public void send(){
        Map<String,Object> map = new HashMap<>();
        map.put("timeStamp", DateUtil.getNow().getTime());
        map.put("name","李静");
        map.put("mobile","18888888888");
        map.put("idNo","211432198804048432");
        map.put("idNoImage","");
        map.put("plateLicense","京A88888");
        map.put("carType","1");
        map.put("owner","李静");
        map.put("address","北京市朝阳区");
        map.put("useNature","1");
        map.put("brandModel","XXX");
        map.put("identifyCode","VIN");
        map.put("engineNumber","aaaaaa");
        map.put("registDate","2018-01-01 18:34:22");
        map.put("certificateDate","2018-01-01 18:34:22");
        map.put("drivingLicenseImage","");
        map.put("orderId","N1834345153241");
        map.put("orderType","1");
        map.put("orderAmount",5000.00);
        map.put("orderTime","2018-06-12 18:34:22");
        map.put("insureCity","北京");
        map.put("busiOrderNumber","B5245325");
        map.put("busiAmount",20.00);
        map.put("ctaliOrderNumber","C3412341");
        map.put("ctaliAmount",30.00);
        map.put("insureUser","李静");
        map.put("insurant","李静");
        String url = "http://localhost:8080/pay/order/in";
        String s = JSON.toJSONString(map);
        System.out.println("开始推送数据");
        JSONObject jsonObject = CommunicationUtil.sendPost(url, s, "czbt", "za");
        System.out.println(jsonObject);
        System.out.println("推送数据结束");
    }

    @org.junit.Test
    public void query() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("timeStamp", DateUtil.getNow().getTime());
        map.put("orderId","李静");
        map.put("idNo","211432198804048432");
        map.put("trade", StringUtil.getUUID());
        String url = "http://localhost:8080/pay/order/query";
        String s = JSON.toJSONString(map);
        System.out.println("查询交易信息开始");
        JSONObject jsonObject = CommunicationUtil.sendPost(url, s, "za", "czbt");
        System.out.println(jsonObject);
        System.out.println("查询交易信息结束");
    }
}
