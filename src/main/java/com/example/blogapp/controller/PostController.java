package com.example.blogapp.controller;


import com.example.blogapp.dto.PostDto;
import com.example.blogapp.payloads.PostResponse;
import com.example.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // CREATE post with user and category
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto created = postService.createPost(postDto, userId, categoryId);
        return ResponseEntity.ok(created);
    }

    // GET posts by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }

    // GET posts by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(postService.getPostsByCategory(categoryId));
    }

    // GET all posts with pagination + sorting
    @GetMapping("/")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir));
    }

    // GET single post
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    // DELETE post
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    // UPDATE post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer postId) {
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }

    // SEARCH posts by title keyword
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
        return ResponseEntity.ok(postService.searchPosts(keyword));
    }
}