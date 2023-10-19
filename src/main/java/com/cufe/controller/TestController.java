package com.cufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class TestController {

    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("message", "abc");

        return "test";
    }
}
