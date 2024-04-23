package com.gb.examspringboot.Service;

import com.gb.examspringboot.Model.Product;
import com.gb.examspringboot.Model.Review;
import com.gb.examspringboot.Responsive.PageRes;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    PageRes<Product> getProducts(int page, int size);

    List<Review> getReviews ();

    List<Review> getReviewsByProductId(int productId);
    Product getProductById(int id);

}
