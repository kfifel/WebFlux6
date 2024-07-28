package com.webflux.web.rest;

import com.webflux.entity.Post;
import com.webflux.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
@CrossOrigin("${cross.origin.url}")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Flux<Post> getAllPosts(
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return postService.getAll(PageRequest.of(page, size));
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
