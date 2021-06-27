package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.entity.PageResult;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.service.goods.SkuService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class ImportData {
    @Reference
    private SkuService skuService;

    @GetMapping("/import")
    public void importData() {
//1.连接rest接口
        HttpHost http=new HttpHost("127.0.0.1",9200,"http");
        RestClientBuilder builder= RestClient.builder(http);//rest构建器
        RestHighLevelClient restHighLevelClient=new RestHighLevelClient(builder);//高级客户端对象 （连接）

        //2.封装查询请求
        PageResult<Sku> page = skuService.findPage(1, 1000);

        BulkRequest bulkRequest=new BulkRequest();
        IndexRequest indexRequest=null;
        for (Sku sku : page.getRows()) {
            indexRequest=new IndexRequest("sku","doc",sku.getId());
            Map skuMap=new HashMap();
            skuMap.put("name",sku.getName());
            skuMap.put("brandName",sku.getBrandName());
            skuMap.put("categoryName",sku.getCategoryName());
            skuMap.put("price",sku.getPrice());
            skuMap.put("createTime",sku.getCreateTime());
            skuMap.put("saleNum",sku.getNum());
            skuMap.put("commentNum",sku.getCommentNum());
            skuMap.put("image", sku.getImage());
            Map spec=new HashMap();
            spec.put("网络制式","移动4G");
            spec.put("屏幕尺寸","5");
            skuMap.put("spec",spec);
            indexRequest.source(skuMap);
            bulkRequest.add(indexRequest);
        }


        //3.获取查询结果
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            int status = bulkResponse.status().getStatus();
            System.out.println(status);
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ImportData().importData();
    }
}
