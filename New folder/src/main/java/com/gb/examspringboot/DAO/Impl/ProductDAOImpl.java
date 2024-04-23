package com.gb.examspringboot.DAO.Impl;

import com.gb.examspringboot.DAO.ProductDAO;
import com.gb.examspringboot.Database.ProductDB;
import com.gb.examspringboot.Model.Product;
import com.gb.examspringboot.Model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAllProducts() {
        return ProductDB.productList;
    }

    @Override
    public List<Product> getProducts() {
        return ProductDB.productList;
    }

    @Override
    public List<Review> getReviews() {
        for (Product product : ProductDB.productList) {
            return product.getReview();
        }
        return null;
    }

    @Override
    public List<Review> getReviewsByProductId(int productId) {
        for (Product product : ProductDB.productList) {
            if (product.getId() == productId) {
                return product.getReview();
            }
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return ProductDB.productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
