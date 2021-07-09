package com.qingcheng.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @RequestMapping("/back")
    public String back(@RequestHeader(value = "referer", required = false) String referer) {
        if (!StringUtils.isEmpty(referer)) {
            return "redirect:"+referer;
        }
        return "/seckill-index.html";
    }
}
