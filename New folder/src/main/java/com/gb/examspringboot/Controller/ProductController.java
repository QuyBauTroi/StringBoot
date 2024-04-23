package com.gb.examspringboot.Controller;

import com.gb.examspringboot.Model.Product;
import com.gb.examspringboot.Model.Review;
import com.gb.examspringboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;



    @RequestMapping()
    public String getAllProducts(Model model,
                                 @RequestParam(required = false ,defaultValue = "1") int page,
                                 @RequestParam(required = false ,defaultValue = "8") int size) {
        model.addAttribute("pageData", productService.getProducts(page,size));
        return "/index";
    }

    @RequestMapping("/detail")
    public String getProductDetail(Model model, @RequestParam int id) {
        Product product = productService.getProductById(id);
        List<Review> reviews = productService.getReviewsByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        return "/review";
    }



}
