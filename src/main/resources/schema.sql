CREATE TABLE IF NOT EXISTS users
(
    id       LONG PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    image    VARCHAR(255),
    bio      VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS articles
(
    id          LONG PRIMARY KEY AUTO_INCREMENT,
    author      LONG         NOT NULL,
    slug        VARCHAR(255) NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    body        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,
    FOREIGN KEY (author) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS tags
(
    id   LONG PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS article_tags
(
    article_id LONG,
    tag_id     LONG,
    PRIMARY KEY (article_id, tag_id),
    FOREIGN KEY (article_id) REFERENCES articles (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE IF NOT EXISTS article_favorites
(
    article_id LONG,
    user_id    LONG,
    PRIMARY KEY (article_id, user_id),
    FOREIGN KEY (article_id) REFERENCES articles (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);