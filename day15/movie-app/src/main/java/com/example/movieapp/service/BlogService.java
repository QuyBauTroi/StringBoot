package com.example.movieapp.service;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.User;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.request.UpsertBlogRequest;
import com.example.movieapp.repository.BlogRepository;
import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private  HttpSession session;
    @Autowired
    private FileService fileService;

    public Page<Blog> getBlogByStatus(Boolean status, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status,pageRequest);
    }
    public Blog getBlogByIdAndSlugAndStatus(Integer id, String slug, Boolean status) {
        return blogRepository.findByIdAndSlugAndStatus(id, slug, status);
    }
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Blog> getOwnBlogs() {
        User user = (User) session.getAttribute("currentUser");
        return blogRepository.findByUser_IdOrderByCreatedAtDesc(user.getId());
    }
    public Blog getBlogById(Integer id) {
        if (blogRepository.findById(id).isPresent()) {
            return blogRepository.findById(id).get();
        }
        return null;
    }
    public Blog createBlog(UpsertBlogRequest blogRequest) {
        User user = (User) session.getAttribute("user");

        Slugify slugify= Slugify.builder().build();
        Blog blog = Blog.builder()
                .title(blogRequest.getTitle())
                .slug(slugify.slugify(blogRequest.getTitle()))
                .content(blogRequest.getContent())
                .description(blogRequest.getDescription())
                .status(blogRequest.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        blogRepository.save(blog);
        return blog;
    }


    public Blog updateBlog(UpsertBlogRequest blogRequest, Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy blog"));
        User user = (User) session.getAttribute("user");

        if (!blog.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Người dùng không được phép cập nhật blog");
        }
        Slugify slugify= Slugify.builder().build();
        blog.setTitle(blogRequest.getTitle());
        blog.setSlug(slugify.slugify(blog.getTitle()));
        blog.setContent(blogRequest.getContent());
        blog.setDescription(blogRequest.getDescription());
        blog.setStatus(blogRequest.getStatus());
        blog.setUpdatedAt(LocalDateTime.now());
        blogRepository.save(blog);
        return blog;
    }

    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy blog"));
        User user = (User) session.getAttribute("user");

        if (!blog.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Người dùng không được phép cập nhật blog");
        }
        blogRepository.delete(blog);
    }

    public String uploadThumbnail(Integer id,MultipartFile file) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Blog not found"));
        try {
            Map data = fileService.uploadImage(file);
            String url = (String) data.get("url");
            blog.setThumbnail(url);
            blogRepository.save(blog);
            return url;
        }catch (IOException e) {
            throw new RuntimeException("Error upload image file");
        }
    }

}
