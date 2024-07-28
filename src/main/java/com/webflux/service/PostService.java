package com.webflux.service;

import com.webflux.entity.Post;
import com.webflux.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Flux<Post> getAll(PageRequest pageRequest) {
        return postRepository.findAllBy(pageRequest);
    }

    public Mono<Post> find(String id) {
        return postRepository.findById(id);
    }

    public Mono<Post> save(Post post) {
        post.setPostId(UUID.randomUUID().toString());
        return postRepository.save(post)
                .doOnError(error -> log.error("Error creating post: {}", error.getMessage(), error));
    }

    public Flux<Post> saveAll(List<Post> posts) {
        return postRepository.saveAll(posts)
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

    public void seed(Long size) {
        List<Post> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            if(i % 500 == 0){
                if(list.isEmpty())
                    continue;
                saveAll(list).subscribe();
                list.clear();
            } else {
                list.add(Post.builder()
                        .postId(UUID.randomUUID().toString())
                        .title("Post " + i)
                        .content("Content " + i)
                        .build());
            }
        }
        if (!list.isEmpty())
            saveAll(list).subscribe();
    }
}
