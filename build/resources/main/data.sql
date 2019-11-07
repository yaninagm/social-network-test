DROP TABLE IF EXISTS `user`;
CREATE TABLE user(user_id BIGINT(20) AUTO_INCREMENT, username VARCHAR (255), password VARCHAR (255));

DROP TABLE IF EXISTS `friendshiprequest`;
CREATE TABLE friendshiprequest(id BIGINT(20) AUTO_INCREMENT, user_from_id BIGINT(20), user_to_id BIGINT(20), status VARCHAR (255));
