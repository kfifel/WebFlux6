CREATE TABLE posts
(
    id         VARCHAR(255) PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE post_images
(
    id      VARCHAR(255) PRIMARY KEY,
    post_id VARCHAR(255),
    name    VARCHAR(255) NOT NULL,
    data    BLOB,
    CONSTRAINT fk_posts_images FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);
