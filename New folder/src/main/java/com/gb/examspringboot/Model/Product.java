package com.gb.examspringboot.Model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {
    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private int price;
    private double rating;
    private double priceDiscount;
    private List<Review> review;

    public int getDiscountedPrice() {
        return (int) Math.floor(this.price * (1 - this.priceDiscount / 100));
    }

}
