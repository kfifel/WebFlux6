package com.webflux.service;

import com.webflux.entity.Post;
import com.webflux.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Flux<Post> getAll() {
        return postRepository.findAll();
    }

    public Mono<Post> find(String id) {
        return postRepository.findById(id);
    }

    public Mono<Post> save(Post post) {
        post.setPostId(UUID.randomUUID().toString());
        return postRepository.save(post)
                .doOnNext(savedPost -> log.info("Created Post: {}", savedPost))
                .doOnError(error -> log.error("Error creating post: {}", error.getMessage(), error));
    }

    public Mono<Post> update(Post post) {
        return postRepository.findByPostId(post.getPostId())
                .flatMap(existingPost -> {
                    existingPost.setTitle(post.getTitle());
                    existingPost.setContent(post.getContent());
                    return postRepository.save(existingPost);
                })
                .doOnNext(updatedPost -> log.info("Updated Post: {}", updatedPost))
                .doOnError(error -> log.error("Error updating post: {}", error.getMessage(), error));
    }
}
