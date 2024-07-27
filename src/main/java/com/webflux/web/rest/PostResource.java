package com.webflux.web.rest;

import com.webflux.entity.Post;
import com.webflux.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Flux<Post> getAllPosts() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Post> getPost(@PathVariable String id) {
        return postService.find(id);
    }

    @PostMapping
    public Mono<Post> createPost(@RequestBody Post post) {
        log.info("Request to create post: {}", post);
        return postService.save(post);
    }

    @PatchMapping
    public Mono<Post> updatePost(@RequestBody Post post) {
        log.info("Request to update post: {}", post);
        return postService.update(post);
    }

}
