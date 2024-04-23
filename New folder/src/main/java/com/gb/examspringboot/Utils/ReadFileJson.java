package com.gb.examspringboot.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gb.examspringboot.Model.Product;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
public class ReadFileJson implements IFileReader {

    @Override
    public List<Product> readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        List<Product> personList = Collections.emptyList();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                personList = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }
}
