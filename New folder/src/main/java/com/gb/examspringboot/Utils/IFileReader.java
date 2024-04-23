package com.gb.examspringboot.Utils;

import com.gb.examspringboot.Model.Product;

import java.util.List;

public interface IFileReader  {
    List<Product> readFile(String path);
}
