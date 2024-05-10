DELETE
FROM users;

INSERT INTO users (id, email, password, username)
VALUES (1, 'user1@example.com', '{bcrypt}$2a$10$e28zvqlPjcpL2bn8PPahQeauHjklvTv4xZGtkCHfgZ76oGeXOAgku', 'user1')
     , (2, 'user2@example.com', '{bcrypt}$2a$10$vgmrtnmQTvZV.mn5lHuOEugh0jL2VaT99SPzvlXRPnxzxx5Ph0nB2', 'user2')
     , (3, 'user3@example.com', '{bcrypt}$2a$10$Sp95Hb3x5q4qK2pF5fauDeZidLhQv1CpkqcL.0xXHS.UzlIJZ2.B.', 'user3')
;

DELETE
FROM articles;

INSERT INTO articles (id, author, title, description, body, created_at, updated_at)
VALUES (1, 1, 'Article 1', 'Description 1', 'Body 1', '2020-01-01 00:00:00', '2020-01-01 00:00:00')
     , (2, 1, 'Article 2', 'Description 2', 'Body 2', '2020-01-02 00:00:00', '2020-01-02 00:00:00')
     , (3, 2, 'Article 3', 'Description 3', 'Body 3', '2020-01-03 00:00:00', '2020-01-03 00:00:00')
     , (4, 2, 'Article 4', 'Description 4', 'Body 4', '2020-01-04 00:00:00', '2020-01-04 00:00:00')
     , (5, 3, 'Article 5', 'Description 5', 'Body 5', '2020-01-05 00:00:00', '2020-01-05 00:00:00')
     , (6, 3, 'Article 6', 'Description 6', 'Body 6', '2020-01-06 00:00:00', '2020-01-06 00:00:00')
;