package com.webflux.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("posts")
public class Post {

    @Column("id")
    @JsonProperty("id")
    private String postId;

    @Column("title")
    private String title;

    @Column("content")
    private String content;

}
