package com.tyuan.test.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName com.tyuan.proxy.manage.web.TestController
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/12/10 10:11
 */

@RequestMapping("/test")
public class TestController {

    @GetMapping("/doc")
    public String test(Model model) {
        return "/sys/doc/edit";
    }


}
