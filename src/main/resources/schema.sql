CREATE TABLE users
(
    id       LONG PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    image    VARCHAR(255) NOT NULL,
    bio      VARCHAR(255) NOT NULL
);

CREATE TABLE articles
(
    id          LONG PRIMARY KEY AUTO_INCREMENT,
    author      LONG         NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    body        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,
    FOREIGN KEY (author) REFERENCES users (id)
);

CREATE TABLE tags
(
    id   LONG PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE article_tags
(
    article_id LONG,
    tag_id     LONG,
    PRIMARY KEY (article_id, tag_id),
    FOREIGN KEY (article_id) REFERENCES articles (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
)