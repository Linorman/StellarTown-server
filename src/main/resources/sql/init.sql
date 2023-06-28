drop database if exists db_stellar_town_user;
create database db_stellar_town_user;
use db_stellar_town_user;
create table user_info (
    id int not null primary key auto_increment,
    password varchar(1023) not null,
    username varchar(255) binary not null,
    phone_number varchar(20),
    avatar varchar(255),
    address varchar(255),
    gender int not null comment '0:男,1:女,2:保密',
    age int,
    signature varchar(512),
    role varchar(10) not null default 'USER' comment 'user:普通用户,admin:管理员',
    create_time datetime not null,
    del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '用户信息表' charset=utf8;

create table user_follower_info (
    id int not null auto_increment primary key,
    user_id int not null,
    follower_id int not null,
    follow_time datetime not null,
    del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '用户关注表' charset=utf8;
create index userId_follower_index on user_follower_info(user_id);
create index followerId_follower_index on user_follower_info(follower_id);

drop database if exists db_stellar_town_post;
create database db_stellar_town_post;
use db_stellar_town_post;
create table post_info (
    id int not null primary key auto_increment,
    user_id int not null,
    image varchar(255),
    title varchar(255),
    content varchar(1024),
    post_time datetime not null,
    shot_time datetime,
    address varchar(255),
    like_count int not null default 0,
    tag varchar(255),
    del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '帖子信息表' charset=utf8;

create table post_follower_info (
    id int not null auto_increment primary key,
    post_id int not null,
    liker_id int not null,
    follow_time datetime not null,
    del_flag int not null default 0 comment '0:未删除,1:已删除'
) comment '帖子点赞表' charset=utf8;
