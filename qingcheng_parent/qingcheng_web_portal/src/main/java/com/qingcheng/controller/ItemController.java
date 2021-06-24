package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qingcheng.pojo.goods.Category;
import com.qingcheng.pojo.goods.Goods;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.pojo.goods.Spu;
import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Reference
    private SpuService spuService;
    @Value("${pagePath}")
    private String pagePath;
    @Autowired
    private TemplateEngine templateEngine;
    @Reference
    private CategoryService categoryService;

    @GetMapping("/createPage")
    public void createPage(String spuId) {
        //商品信息
        Goods goods = spuService.findGoodsById(spuId);
        //spu信息
        Spu spu = goods.getSpu();
        //sku信息
        List<Sku> skuList = goods.getSkuList();
        //商品分类
        List<String> categoryList = new ArrayList<>();
        categoryList.add(categoryService.findById(spu.getCategory1Id()).getName());
        categoryList.add(categoryService.findById(spu.getCategory2Id()).getName());
        categoryList.add(categoryService.findById(spu.getCategory3Id()).getName());

        Map<String, String> urlMap = new HashMap<>();
        for (Sku sku : skuList) {
            if ("1".equals(sku.getStatus())) {
                String specJson = JSON.toJSONString(JSON.parseObject(sku.getSpec()), SerializerFeature.MapSortField);
                urlMap.put(specJson,sku.getId()+".html");
            }

        }

        for (Sku sku : skuList) {
            Context context = new Context();
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("sku", sku);
            dataModel.put("spu", spu);
            dataModel.put("categoryList", categoryList);
            dataModel.put("spuImages", spu.getImage().split(","));
            dataModel.put("skuImages", sku.getImage().split(","));
            Map paraItems=JSON.parseObject(spu.getParaItems());
            dataModel.put("paraItems", paraItems);
            Map<String,String> specItems = (Map)JSON.parseObject(sku.getSpec());
            dataModel.put("specItems", specItems);

            Map<String,List> specMap = (Map)JSON.parseObject(spu.getSpecItems());
            for (String key : specMap.keySet()) {
                List<String> list = specMap.get(key);
                List<Map> mapList = new ArrayList<>();
                for (String value : list) {
                    Map map = new HashMap();
                    map.put("option", value);
                    if (specItems.get(key).equals(value)) {
                        map.put("checked", true);
                    } else {
                        map.put("checked", false);
                    }

                    Map<String, String> spec = (Map) JSON.parseObject(sku.getSpec());//当前sku
//                    Map<String, String> spec = new HashMap<>();//当前sku
                    spec.put(key, value);
                    String specJson = JSON.toJSONString(spec, SerializerFeature.MapSortField);
                    map.put("url", urlMap.get(specJson));
                    mapList.add(map);
                }
                specMap.put(key, mapList);
            }
            dataModel.put("specMap", specMap);

            context.setVariables(dataModel);

            //准备文件
            File dir = new File(pagePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File dest = new File(dir, sku.getId() + ".html");

            //生成页面
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(dest,"UTF-8");
                templateEngine.process("item", context, writer);
                System.out.println("生成页面："+sku.getId() + ".html");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }
}
