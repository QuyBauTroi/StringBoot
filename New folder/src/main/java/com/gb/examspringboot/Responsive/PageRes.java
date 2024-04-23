package com.gb.examspringboot.Responsive;

import java.util.List;

public interface PageRes<T> {
    int getCurrentPage();
    List<T> getContent();
    int getPageSize();
    int getTotalPages();
    int getTotalElements();

}
