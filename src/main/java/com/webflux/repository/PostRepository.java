package com.webflux.repository;

import com.webflux.entity.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends R2dbcRepository<Post, String> {
    Mono<Post> findByPostId(String postId);
    Flux<Post> findAllBy(PageRequest pageRequest);
}
