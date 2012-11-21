CREATE  table lottery(
       id int primary key,
       date date not null,
       type_id int DEFAULT 0,
       number varchar(100)
)
create table lottery_number(
       id int not null AUTO_INCREMENT,
       lottery_id int not null,
       number int not null,
       name varchar(200),
       PRIMARY key(id),
       FOREIGN KEY (lottery_id) REFERENCES lottery (id)
)