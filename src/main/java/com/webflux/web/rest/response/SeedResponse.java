package com.webflux.web.rest.response;

import lombok.Data;

@Data
public class SeedResponse {
    private String message;
    private Long size;
    private boolean success;

    public SeedResponse(String message, Long size, boolean success) {
        this.message = message;
        this.size = size;
        this.success = success;
    }
}

