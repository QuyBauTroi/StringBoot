package com.gb.examspringboot.Responsive;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResImpl<T> implements PageRes<T>{
    List<T> data;
    int currentPage;
    int pageSize;

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public List getContent() {
        return data.subList((currentPage - 1) * pageSize, Math.min(currentPage * pageSize, data.size()));
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    @Override
    public int getTotalElements() {
        return data.size();
    }
}
