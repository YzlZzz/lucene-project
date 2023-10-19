package com.cufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AdvancedSearchController {

    @RequestMapping("advancedsearch")
    public String get(){
        return "advancedSearch";
    }

}
