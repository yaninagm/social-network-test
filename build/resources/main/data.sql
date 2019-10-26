DROP TABLE IF EXISTS `user`;

CREATE TABLE user(id BIGINT(20) AUTO_INCREMENT, user_name VARCHAR (255), password VARCHAR (255));

insert into user(id, user_name, password)
values(1, 'johndoe','j12345678');

insert into user(id, user_name, password)
values(2, 'roseanne','r3456789');

insert into user(id, user_name, password)
values(3, 'peter','p4567890');

insert into user(id, user_name, password)
values(4, 'jessica','j5678901');

insert into user(id, user_name, password)
values(4, 'robert','r0123456');


