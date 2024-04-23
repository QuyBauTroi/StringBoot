package com.gb.examspringboot.DAO;

import com.gb.examspringboot.Model.Product;
import com.gb.examspringboot.Model.Review;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();

    List<Product> getProducts();

    List<Review> getReviews();

    List<Review> getReviewsByProductId(int productId);

    Product getProductById(int id);
}
