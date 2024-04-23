package com.gb.examspringboot.Service.Impl;

import com.gb.examspringboot.DAO.ProductDAO;
import com.gb.examspringboot.Model.Product;
import com.gb.examspringboot.Model.Review;
import com.gb.examspringboot.Responsive.PageRes;
import com.gb.examspringboot.Responsive.PageResImpl;
import com.gb.examspringboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;
    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public PageRes<Product> getProducts(int page, int size) {
        return new PageResImpl<>(productDAO.getAllProducts(),page,size);
    }

    @Override
    public List<Review> getReviews() {
        return productDAO.getReviews();
    }

    @Override
    public List<Review> getReviewsByProductId(int productId) {
        return productDAO.getReviewsByProductId(productId);
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }


}
