DROP TABLE IF EXISTS `user`;
CREATE TABLE user(id BIGINT(20) AUTO_INCREMENT, username VARCHAR (255), password VARCHAR (255));

DROP TABLE IF EXISTS `friendshiprequest`;
CREATE TABLE friendshiprequest(id BIGINT(20) AUTO_INCREMENT, user_from VARCHAR (255), user_to VARCHAR (255), status VARCHAR (255), `date_created` DATETIME,`date_last_modified`DATETIME,);
