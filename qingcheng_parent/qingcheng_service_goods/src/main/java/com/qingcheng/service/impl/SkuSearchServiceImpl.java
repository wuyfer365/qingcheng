package com.qingcheng.service.impl;
import com.qingcheng.util.CacheKey;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.BrandMapper;
import com.qingcheng.dao.SpecMapper;
import com.qingcheng.service.goods.SkuSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SpecMapper specMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    public Map search(Map<String,String> searchMap) {
//        封装查询请求
        SearchRequest searchRequest = new SearchRequest("sku");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        1.1关键字搜索
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name",searchMap.get("keywords"));
        boolQueryBuilder.must(matchQueryBuilder);
//        1.2商品分类过滤
        if (searchMap.get("category") != null) {
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("categoryName", searchMap.get("category"));
            boolQueryBuilder.filter(termQueryBuilder);
        }
//        1.3品牌过滤
        if (searchMap.get("brand") != null) {
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brandName", searchMap.get("brand"));
            boolQueryBuilder.filter(termQueryBuilder);
        }
        //1.4规格过滤
        for (String key : searchMap.keySet()) {
            if (key.startsWith("spec.")) {
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(key+".keyword", searchMap.get(key));
                boolQueryBuilder.filter(termQueryBuilder);
            }
        }
        //1.5价格过滤
        if (searchMap.get("price") != null) {
            String[] prices = searchMap.get("price").split("-");
            if (!prices[0].equals("0")) {
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gte(prices[0] + "00");
                boolQueryBuilder.filter(rangeQueryBuilder);
            }
            if (!prices[1].equals("*")) {
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").lte(prices[1] + "00");
                boolQueryBuilder.filter(rangeQueryBuilder);
            }
        }
        searchSourceBuilder.query(boolQueryBuilder);
        //分页
        int pageNo = Integer.parseInt(searchMap.get("pageNo"));
        int pageSize=30;
        int fromIndex=(pageNo-1)*pageSize;
        searchSourceBuilder.from(fromIndex);
        searchSourceBuilder.size(pageSize);

        //排序
        String sort = searchMap.get("sort");
        String sortOrder = searchMap.get("sortOrder");
        if (!"".equals(sort)) {
            searchSourceBuilder.sort(sort, SortOrder.valueOf(sortOrder));
        }

        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name").preTags("<font style='color:red'>").postTags("</font>");
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);

        //聚合查询（商品分类）
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("sku_category").field("categoryName");
        searchSourceBuilder.aggregation(termsAggregationBuilder);
//        封装查询结果
        SearchResponse searchResponse = null;
        Map resultMap = new HashMap();
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits();
            SearchHit[] hits1 = hits.getHits();
//            2.1商品列表
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            for (SearchHit hit : hits1) {
                Map<String, Object> skuMap = hit.getSourceAsMap();

                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField name = highlightFields.get("name");
                Text[] fragments = name.fragments();
                skuMap.put("name", fragments[0].toString());
                resultList.add(skuMap);
            }
            resultMap.put("rows", resultList);
            //2.2商品分类列表
            Aggregations aggregations = searchResponse.getAggregations();
            Map<String, Aggregation> aggregationMap = aggregations.getAsMap();

            Terms terms = (Terms) aggregationMap.get("sku_category");

            List<? extends Terms.Bucket> buckets =  terms.getBuckets();
            List<String> categoryList = new ArrayList<String>();
            for( Terms.Bucket bucket:buckets ){
                categoryList.add(bucket.getKeyAsString());
            }
            resultMap.put("categoryList", categoryList);


            String categoryName="";
            if (searchMap.get("category") == null) {
                if (categoryList.size() > 0) {
                    categoryName = categoryList.get(0);
                }
            }else {
                categoryName = searchMap.get("category");
            }
            //2.3品牌列表
            if (searchMap.get("brand") == null) {
                List<Map> brandList = new ArrayList<Map>();
                if (redisTemplate.boundHashOps(CacheKey.BRAND).get(categoryName) == null) {
                    brandList = brandMapper.findListByCategoryName(categoryName);
                } else {
                    brandList = (List<Map>)redisTemplate.boundHashOps(CacheKey.BRAND).get(categoryName);
                }

                resultMap.put("brandList", brandList);
            }

            //2.4规格列表
            List<Map> specList = new ArrayList<Map>();
            if (redisTemplate.boundHashOps(CacheKey.SPEC).get(categoryName) == null) {
                specList = specMapper.findListByCategoryName(categoryName);
            }else {
                specList = (List<Map>)redisTemplate.boundHashOps(CacheKey.SPEC).get(categoryName);
            }
            for (Map map : specList) {
                String[] options = ((String) map.get("options")).split(",");
                map.put("options", options);
            }
            resultMap.put("specList", specList);

            //2.5页码
            long totalCount = hits.getTotalHits();
            long pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
            resultMap.put("totalPages", pageCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
