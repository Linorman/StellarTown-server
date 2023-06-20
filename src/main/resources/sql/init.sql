drop database if exists db_stellar_town_user;
create database db_stellar_town_user;
use db_stellar_town_user;
create table user_info (
    id varchar(50) not null primary key,
    password varchar(255) not null,
    username varchar(255) not null,
    phone_number varchar(20),
    avatar varchar(255),
    address varchar(255),
    gender int not null comment '0:男,1:女,2:保密',
    age int,
    signature varchar(512),
    create_time datetime not null,
    del_flag int not null default 0 comment '0:未删除,1:已删除'
);

drop database if exists db_stellar_town_follower;
create database db_stellar_town_follower;
use db_stellar_town_follower;
create table userId_follower (
    id varchar(50) not null,
    follower_id varchar(50) not null,
    follow_time datetime not null,
    del_flag int not null default 0 comment '0:未删除,1:已删除'
);

drop database if exists db_stellar_town_post;
create database db_stellar_town_post;
use db_stellar_town_post;
create table post_info (
    id int not null primary key,
    user_id varchar(50) not null,
    image varchar(255),
    title varchar(255),
    content varchar(1024),
    post_time datetime not null,
    shot_time datetime,
    address varchar(255),
    like_count int not null default 0,
    tag varchar(255),
    del_flag int not null default 0 comment '0:未删除,1:已删除'
);

create table post_follower_info (
    id int not null,
    post_id int not null,
    liker_id varchar(50) not null,
    create_time datetime not null,
    del_flag int not null default 0 comment '0:未删除,1:已删除'
);
