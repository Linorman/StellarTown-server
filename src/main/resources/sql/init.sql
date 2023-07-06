drop database if exists db_stellar_town_user;
create database db_stellar_town_user;
use db_stellar_town_user;
create table user_info (
    id int not null primary key auto_increment,
    password varchar(1023) not null,
    username varchar(255) binary not null,
    phone_number varchar(20),
    avatar varchar(1023),
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

INSERT INTO user_info (password, username, phone_number, avatar, address, gender, age, signature, role, create_time, del_flag)
VALUES
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm', 'lyh', '1234567890', 'alice_avatar.jpg', '北京', 1, 28, 'Nice to meet you!', 'USER', '2023-06-28 08:49:39', 0),
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm', 'hzy', '9876543210', 'bob_avatar.jpg', '上海', 0, 32, 'Hello, I am Bob.', 'USER', '2023-06-28 08:49:39', 0),
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm', 'Admin', '5555555555', 'admin_avatar.jpg', '武汉', 2, NULL, 'Welcome to the admin panel.', 'ADMIN', '2023-06-28 08:49:39', 0),
    ('$2a$10$qDsDERjBgAnI02TJSquZAed.sb7Ak7VLGWbWY5ixtXkWNm2qH5JNm','ww','1234567890','ww_avatar.jpg','苏州',2,NULL,'hhh','USER','2021-06-29 09:56:23',0);
INSERT INTO user_follower_info (user_id, follower_id, follow_time, del_flag)
VALUES
    (1, 2, '2023-06-28 10:00:00', 0),
    (1, 3, '2023-06-28 10:15:00', 0),
    (3, 2, '2023-06-28 10:30:00', 0),
    (2, 1, '2023-06-28 10:45:00', 0);

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

INSERT INTO post_info (user_id, image, title, content, post_time, shot_time, address, like_count, tag, del_flag)
VALUES
    (1, 'image1.jpg', '第一篇帖子', '这是第一篇帖子的内容。', '2023-06-28 10:00:00', NULL, '北京', 0, '生活', 0),
    (2, 'image2.jpg', 'Second Post', 'This is the second post content.', '2023-06-28 11:00:00', NULL, '上海', 0, '新闻', 0),
    (3, 'image3.jpg', 'Third Post', 'This is the third post content.', '2023-06-28 12:00:00', NULL, '广州', 0, '娱乐', 0),
    (4, 'image4.jpg', 'Fourth Post', 'This is the fourth post content.', '2023-06-28 12:00:01', NULL, '日照', 0, '娱乐', 0);

INSERT INTO post_follower_info (post_id, liker_id, follow_time, del_flag)
VALUES
    (1, 1, '2023-06-28 10:00:00', 0),
    (2, 1, '2023-06-28 10:30:00', 0),
    (1, 2, '2023-06-28 10:15:00', 0),
    (2, 2, '2023-06-28 10:45:00', 0),
    (3, 2, '2023-06-28 10:45:00', 0),
    (1, 3, '2023-06-28 10:45:00', 0),
    (4, 3, '2023-06-28 10:30:00', 0);


drop database if exists  db_stellar_town_attraction;
create database db_stellar_town_attraction;
use db_stellar_town_attraction;
create table attraction_info(
    id int not null auto_increment primary key,
    name varchar(255),
    introduction varchar(255),
    address varchar(255),
    image  varchar(255),
    altitude varchar(255),
    longitude varchar(255),
    latitude varchar(255)
)comment '景点信息表' charset=utf8;

INSERT INTO attraction_info (name, introduction, address, image, longitude, latitude)
VALUES
    ('夏威夷毛伊岛', '夏威夷毛伊岛是一个迷人的星空观测点，位于美国夏威夷群岛。这里有清澈的夜空、无光污染和绚丽的星空景观。', '夏威夷, 美国', 'http://101.37.252.1:9999/stellar-town/scenery/maoyidao.jpg', '21.09', '156.33'),
    ('智利阿塔卡马沙漠', '智利阿塔卡马沙漠是世界上最干燥的沙漠，也是天文学家和摄影师们的圣地。这里的夜空清澈透明，常年无云，是观赏星空的绝佳地点。', '阿塔卡马沙漠, 智利', 'http://101.37.252.1:9999/stellar-town/scenery/atakama.jpg', '23.86', '69.13'),
    ('新西兰南岛', '新西兰南岛拥有壮丽的山脉、湖泊和无污染的大气环境，是观赏星空的理想地点。在这里，您可以欣赏到南半球特有的星座和银河系。', '新西兰南岛', 'http://101.37.252.1:9999/stellar-town/scenery/nandao.jpg', '44.83', '168.73'),
    ('美国犹他州布赖斯峡谷', '美国犹他州布赖斯峡谷是一个巨大的自然户外天文馆，被誉为地球上最美丽的星空剧院之一。夜晚，数以千计的星星在红色岩石峡谷上闪烁。', '犹他州, 美国', 'http://101.37.252.1:9999/stellar-town/scenery/bulaisixiagu.jpg', '37.59', '112.18'),
    ('挪威罗弗敦市', '挪威罗弗敦市享有极光和星空的美誉。这个小镇靠近北极圈，有无污染的环境和壮丽的山脉作为背景，是摄影师们捕捉北极光和星空的理想之地。', '罗弗敦市, 挪威', 'http://101.37.252.1:9999/stellar-town/scenery/luofudunshi.jpg', '69.64', '18.95'),
    ('澳大利亚昆士兰州', '澳大利亚昆士兰州拥有广袤无垠的荒野和无光污染的夜空。在这里，您可以欣赏到南半球最美丽的星空，包括南十字星座和银河系的明亮星云。', '昆士兰州, 澳大利亚', 'http://101.37.252.1:9999/stellar-town/scenery/kunshilanzhou.jpg', '22.82', '144.96'),
    ('加拿大亚伯达省', '加拿大亚伯达省拥有许多原始森林和国家公园，提供了绝佳的观赏星空的机会。在这里，您可以远离城市的繁忙，享受宁静的星空之旅。', '亚伯达省, 加拿大', 'http://101.37.252.1:9999/stellar-town/scenery/yabodasheng.jpg',  '53.93', '116.57'),
    ('南非卡鲁盆地', '南非卡鲁盆地是一个天文爱好者的天堂，拥有高原地形、干燥的气候和清澈的夜空。这里的星空被公认为世界上最清晰和最明亮的之一。', '卡鲁盆地, 南非', 'http://101.37.252.1:9999/stellar-town/scenery/kalupendi.jpg',  '30.61', '21.40'),
    ('中国桂林阳朔', '中国桂林阳朔以其壮丽的喀斯特地貌和清新的空气而闻名，在晴朗的夜晚，您可以在阳朔欣赏到美丽的星空和恒河沙漠。', '桂林阳朔, 中国', 'http://101.37.252.1:9999/stellar-town/scenery/yangshuo.jpg',  '24.78', '110.48'),
    ('冰岛', '冰岛位于北极圈附近，是极光和星空摄影的热门目的地之一。这里的夜空通常非常黑暗，能够清晰地看到令人叹为观止的星空景观。', '冰岛', 'http://101.37.252.1:9999/stellar-town/scenery/bingdao.jpg', '64.96', '19.02');





