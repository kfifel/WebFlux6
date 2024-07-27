package com.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("post_images")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostImage {

    @Id
    private String id;
    @Column("post_id")
    private String postId;
    private String name;
    private byte[] data;
}
