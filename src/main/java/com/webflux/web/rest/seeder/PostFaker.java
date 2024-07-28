package com.webflux.web.rest.seeder;

import com.webflux.service.PostService;
import com.webflux.web.rest.response.SeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/seed/v1/posts")
@RequiredArgsConstructor
public class PostFaker {

    private final PostService postService;

    @PostMapping("/{size}")
    public SeedResponse seed(@PathVariable Long size) {
        try {
            postService.seed(size);
            return new SeedResponse("Seeding completed successfully", size, true);
        } catch (Exception e) {
            return new SeedResponse("Seeding failed: " + e.getMessage(), size, false);
        }
    }
}
