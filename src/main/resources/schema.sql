CREATE TABLE users
(
    id       LONG PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    image    VARCHAR(255) NOT NULL,
    bio      VARCHAR(255) NOT NULL
);
