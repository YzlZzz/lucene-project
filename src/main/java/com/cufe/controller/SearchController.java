package com.cufe.controller;

import org.apache.lucene.document.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cufe.lucene.SearchFile;


@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping
    public String searchFiles(@RequestParam("searchInput") String query, Model model) throws Exception {
        Document[] docs = SearchFile.search(query, "contents");
        System.out.println(query);
        model.addAttribute("pageTitle", query);
        model.addAttribute("docs", docs);

        for (Document doc : docs) {
            System.out.println(doc.get("path"));
        }

        // 执行文件搜索操作，返回匹配的文件列表
        return "search";

    }

    @PostMapping
    public String advancedSearch(Model model, HttpServletRequest request) throws Exception {

        String andQuery = request.getParameter("and");
        String orQuery = request.getParameter("or");
        String notQuery = request.getParameter("not");
        String fields = request.getParameter("searchFields");

        if(!andQuery.isEmpty()){
            andQuery = SearchFile.insertString(andQuery, "+");
        }
        if(!notQuery.isEmpty()) {
            notQuery = SearchFile.insertString(notQuery, "-");

        }

        String query = orQuery + " " + andQuery + " " + notQuery ;
        switch (fields){
            case "0":
                fields = "contents";
                break;
            case "1":
                fields = "title";
                break;
            case "2":
                fields = "author";
                break;
        }
        Document[] docs = SearchFile.search(query, fields);
        model.addAttribute("pageTitle", "高级检索");
        model.addAttribute("docs", docs);

        System.out.println(andQuery);
        System.out.println(orQuery);
        System.out.println(notQuery);
        System.out.println(fields);

        return "search";
    }

}

