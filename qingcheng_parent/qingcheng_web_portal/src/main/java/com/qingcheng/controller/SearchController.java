package com.qingcheng.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qingcheng.service.goods.SkuSearchService;
import com.qingcheng.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SearchController {
    @Reference
    private SkuSearchService skuSearchService;
    @GetMapping("/search")
    public String search(Model model, @RequestParam Map<String, String> searchMap) throws Exception {
//        字符集处理(解决中文乱码)
        searchMap = WebUtil.convertCharsetToUTF8(searchMap);
        if (searchMap.get("pageNo") == null) {
            searchMap.put("pageNo", "1");
        }

        Map result = skuSearchService.search(searchMap);
        model.addAttribute("result", result);
        //url处理
        StringBuilder url = new StringBuilder("/search.do?");
        for (String key : searchMap.keySet()) {
            url.append("&" + key + "=" + searchMap.get(key));
        }
        model.addAttribute("url", url);
        model.addAttribute("searchMap", searchMap);
        int pageNo = Integer.parseInt(searchMap.get("pageNo"));
        model.addAttribute("pageNo", pageNo);
        return "search";
    }
}
