DROP TABLE IF EXISTS `user`;
CREATE TABLE user(id BIGINT(20) AUTO_INCREMENT, user_name VARCHAR (255), password VARCHAR (255));

DROP TABLE IF EXISTS `relationship`;
CREATE TABLE relationship(user_from VARCHAR (255), user_to VARCHAR (255), status VARCHAR (255));

/*
insert into user(id, user_name, password)
values(1, 'johndoe','j12345678');
*/