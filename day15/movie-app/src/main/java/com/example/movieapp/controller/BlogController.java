package com.example.movieapp.controller;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.service.BlogService;
import com.example.movieapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;

    @GetMapping("/tin-tuc")
    public String tinTuc(Model model, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "4") int pageSize) {
        Page<Blog> pageData = blogService.getBlogByStatus(true,page,pageSize);
        model.addAttribute("pageData",pageData);
        model.addAttribute("currentPage",page);
        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String chiTietBlog(@PathVariable int id, @PathVariable String slug, Model model) {
        model.addAttribute("blog",blogService.getBlogByIdAndSlugAndStatus(id,slug,true));
        model.addAttribute("comments",commentService.findByBlog_IdOrderByCreatedAtDesc(id));
        return "web/chi-tiet-tin-tuc";
    }
}
