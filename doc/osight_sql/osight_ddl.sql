--彩票
CREATE table lottery(
       id int not null AUTO_INCREMENT,
       issueno int not null,
       date date not null,
       type_id int DEFAULT 0,
       number varchar(100) not null,
       
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp not null default '0000-00-00 00:00:00',
       UPDATEDON     timestamp not null default '0000-00-00 00:00:00' on update current_timestamp,
       CREATEDIP     VARCHAR(100),
       UPDATEDIP     VARCHAR(100),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       PRIMARY key(id)
);
create table lottery_number(
       id int not null AUTO_INCREMENT,
       lottery_id int not null,
       idx int not null,
       number int not null,
       name varchar(200),
       PRIMARY key(id),
       FOREIGN KEY (lottery_id) REFERENCES lottery (id)
);
--相册
CREATE TABLE album (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  description text,
  access_type int(11) NOT NULL DEFAULT '0',
  cover bigint,
  CREATEDBY     VARCHAR(32), 
   UPDATEDBY     VARCHAR(32),
   CREATEDON     timestamp,
   UPDATEDON     timestamp,
   CREATEDIP     VARCHAR(100),
   UPDATEDIP     VARCHAR(100),
   CREATEDSERVER VARCHAR(50),
   UPDATEDSERVER VARCHAR(50),
  PRIMARY KEY (id)
);
CREATE TABLE album_access (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  album_id bigint(20) NOT NULL,
  type int(11) NOT NULL DEFAULT '0',
  question text,
  answer text,
  password varchar(100) DEFAULT NULL,
  CREATEDBY     VARCHAR(32), 
   UPDATEDBY     VARCHAR(32),
   CREATEDON     timestamp,
   UPDATEDON     timestamp,
   CREATEDIP     VARCHAR(100),
   UPDATEDIP     VARCHAR(100),
   CREATEDSERVER VARCHAR(50),
   UPDATEDSERVER VARCHAR(50),
  PRIMARY KEY (id),
  FOREIGN KEY (album_id) REFERENCES album (id)
);
create table album_photo(
       id bigint not null AUTO_INCREMENT,
       album_id bigint not null,
       description text,
       realpath text not null,
       url text not null,
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp,
       UPDATEDON     timestamp,
       CREATEDIP     VARCHAR(100),
       UPDATEDIP     VARCHAR(100),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       PRIMARY KEY (id),
       FOREIGN KEY (album_id) REFERENCES album (id)
);
--博客
create table article(
       id bigint not null AUTO_INCREMENT,
       user_id bigint not null,
       title text not null,
       content text not null,
       pv bigint not null default 0,
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp,
       UPDATEDON     timestamp,
       CREATEDIP     VARCHAR(30),
       UPDATEDIP     VARCHAR(30),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       PRIMARY KEY (id),
       FOREIGN KEY (user_id) REFERENCES user (id)
);
create table article_comment(
       id bigint not null AUTO_INCREMENT,
       user_id bigint not null,     
       article_id bigint not null,
       title text not null,
       content text not null,
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp,
       UPDATEDON     timestamp,
       CREATEDIP     VARCHAR(30),
       UPDATEDIP     VARCHAR(30),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       FOREIGN KEY (article_id) REFERENCES article (id),
       FOREIGN KEY (user_id) REFERENCES user (id),
       PRIMARY KEY (id)
       
)
create table label(
       id bigint NOT null AUTO_INCREMENT,
       name text not null,
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp,
       UPDATEDON     timestamp,
       CREATEDIP     VARCHAR(30),
       UPDATEDIP     VARCHAR(30),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       PRIMARY KEY (id)     
);
create table article_label(
       id bigint not null AUTO_INCREMENT,
       article_id bigint not null,
       label_id bigint not null,
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp,
       UPDATEDON     timestamp,
       CREATEDIP     VARCHAR(30),
       UPDATEDIP     VARCHAR(30),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       PRIMARY KEY (id),
       FOREIGN KEY (article_id) REFERENCES article (id),
       FOREIGN KEY (label_id) REFERENCES label (id)
);
create table user(
       id bigint not null AUTO_INCREMENT,
       user_name varchar(32),
       nick_name tinytext not null,
       email varchar(100) not null,
       password varchar(32),
       status int not null default 0,
       salt varchar(32),
       website text,
       CREATEDBY     VARCHAR(32), 
       UPDATEDBY     VARCHAR(32),
       CREATEDON     timestamp,
       UPDATEDON     timestamp,
       CREATEDIP     VARCHAR(30),
       UPDATEDIP     VARCHAR(30),
       CREATEDSERVER VARCHAR(50),
       UPDATEDSERVER VARCHAR(50),
       PRIMARY KEY (id)
)
select * from user;
update user set email='aaa@aaa.aa' where id=1;
commit;
select * from article;
select * from album_photo;
alter table user add(salt varchar(32));
select now();
insert into lottery(id,date,number,createdon) values(2,NOW(),'1234',null);
select * from lottery;
select *   from lottery_number;
delete from  lottery;
update lottery set number='1111';

SELECT LAST_INSERT_ID()
select @@IDENTITY
SHOW TABLE STATUS

show variables like "%char%";
show create database osight;
show create table lottery_number;
set names utf8
