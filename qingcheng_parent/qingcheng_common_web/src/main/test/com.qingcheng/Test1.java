package com.qingcheng;

import com.alibaba.fastjson.JSONArray;
import com.qingcheng.entity.ImageVo;
//import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import sun.applet.Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Test1<T> {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        String strdate="2019-04-15";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date=LocalDate.parse(strdate,dtf);
        System.out.println(date);
    }

    private static void test1() {
        String imageItems ="[{\"url\":\"http://localhost:9101/img/1.jpg\",\"uid\":\"1548143143154\",\"status\":\"success\"},{\"url\":\"http://localhost:9101/img/7.jpg\",\"uid\":\"15481431431`z55\",\"status\":\"success\"}]";
        List<ImageVo> ts = (List<ImageVo>) JSONArray.parseArray(imageItems, ImageVo.class);
        System.out.println(ts);
        for (ImageVo imageVo : ts) {
            System.out.println(imageVo.getUrl());
        }
    }
}
