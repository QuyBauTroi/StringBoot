package com.example.movieapp.service;

import com.example.movieapp.entity.Comment;
import com.example.movieapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findByBlog_IdOrderByCreatedAtDesc(Integer blogId) {
        return commentRepository.findByBlog_IdOrderByCreatedAtDesc(blogId);
    }
}
