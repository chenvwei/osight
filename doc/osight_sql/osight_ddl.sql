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
       CREATEDSERVER VARCHAR(20),
       UPDATEDSERVER VARCHAR(20),
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
   CREATEDSERVER VARCHAR(20),
   UPDATEDSERVER VARCHAR(20),
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
   CREATEDSERVER VARCHAR(20),
   UPDATEDSERVER VARCHAR(20),
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
       CREATEDSERVER VARCHAR(20),
       UPDATEDSERVER VARCHAR(20),
       PRIMARY KEY (id),
       FOREIGN KEY (album_id) REFERENCES album (id)
)


select * from album;
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
