package com.gb.examspringboot.Database;

import com.gb.examspringboot.Utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader iFileReader;


    @Override
    public void run(String... args) throws Exception {
        ProductDB.productList = iFileReader.readFile("Product.json");
    }
}
