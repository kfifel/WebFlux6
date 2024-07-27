package com.webflux.repository;

import com.webflux.entity.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveCrudRepository<Post, String> {
    Mono<Post> findByPostId(String postId);
}
