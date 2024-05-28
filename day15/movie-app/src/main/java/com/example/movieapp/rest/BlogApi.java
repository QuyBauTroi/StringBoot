package com.example.movieapp.rest;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.model.request.UpsertBlogRequest;
import com.example.movieapp.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/blogs")
public class BlogApi {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<?> createBlog(@Valid @RequestBody UpsertBlogRequest blogRequest) {
        Blog blog = blogService.createBlog(blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.CREATED); //201
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@Valid @PathVariable Integer id, @Valid @RequestBody UpsertBlogRequest blogRequest) {
        Blog blog = blogService.updateBlog(blogRequest, id);
        return ResponseEntity.ok(blog); //200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build(); //204
    }

    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable Integer id, @RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(blogService.uploadThumbnail(id,file));
    }
}
