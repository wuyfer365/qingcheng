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

        if (searchMap.get("sort") == null) {
            searchMap.put("sort", "");
        }
        if (searchMap.get("sortOrder") == null) {
            searchMap.put("sortOrder", "DESC");
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
        //页码
        int pageNo = Integer.parseInt(searchMap.get("pageNo"));
        model.addAttribute("pageNo", pageNo);

        Long totalPages = (Long) result.get("totalPages");
        int startPage=1;
        int endPage=totalPages.intValue();

        if (totalPages > 5) {
            startPage=pageNo-2;
            if (startPage < 1) {
                startPage=1;
            }
            endPage=startPage+4;
            if (endPage > totalPages) {
                endPage=totalPages.intValue();
            }
        }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "search";
    }
}
