--用户表的建库脚本
create table users(id int unsigned NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL,password varchar(100) NOT NULL,signuptime datetime NOT NULL,PRIMARY KEY(id),KEY name_index(name(10)));