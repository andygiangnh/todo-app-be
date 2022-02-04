CREATE TABLE user(
id INT primary key AUTO_INCREMENT,
full_name VARCHAR(100) NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(120) NOT NULL,
password VARCHAR(256) NOT NULL);

CREATE TABLE user_roles(
user_id INT NOT NULL,
role_id INT NOT NULL,
CONSTRAINT PRIMARY KEY user_role_PK (user_id, role_id)
);

CREATE TABLE role(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL);

CREATE TABLE todo(
id INT PRIMARY KEY AUTO_INCREMENT,
description VARCHAR(250) NOT NULL,
completed TINYINT default 0,
user_id INT NOT NULL,
CONSTRAINT FOREIGN KEY todo_user_fk(user_id) REFERENCES user(id)
ON DELETE CASCADE
);

insert into role(name) values('ROLE_ADMIN');
insert into role(name) values('ROLE_USER');

CREATE USER 'todo_user'@'localhost' IDENTIFIED BY 'Demo@1234';
CREATE USER 'todo_user'@'%' IDENTIFIED BY 'Demo@1234';
GRANT ALL PRIVILEGES ON `todo`.* TO 'todo_user'@'localhost';
GRANT ALL PRIVILEGES ON `todo`.* TO 'todo_user'@'%';