/* 建库语句 */

/* 删表语句 */
drop table if exists list_music;
drop table if exists user_musiclist;
drop table if exists music;
drop table if exists category;
drop table if exists album;
drop table if exists user;

/* 建表语句 */
/* 1: 用户表 */
create table user(
user_id int(11) primary key auto_increment,
user_name varchar(20) not null,
user_pwd varchar(16) not null,
user_gender char(2),
user_desc varchar(300),
user_pic varchar(100),
user_nick varchar(20),
user_iddr varchar(30),
user_birthday date,
user_type int(1) not null default 1,
user_created_date date,
constraint check_gender check(user_gender in ('男', '女'))
);

/* 2: 专辑表 */
create table album(
album_id int(11) primary key auto_increment,
album_name varchar(50) not null
);

/* 3: 歌曲分类表 */
create table category(
category_id int(11) primary key auto_increment,
category_type varchar(10) not null
);

/* 4: 音乐表 */
create table music(
music_id int(11) primary key auto_increment,
music_name varchar(100) not null,
music_author varchar(20) not null,
music_album_id int(11),
music_pic varchar(100),
music_path varchar(500) not null,
music_created_time timestamp not null default current_timestamp,
music_lyric_path varchar(500) not null,
music_type_id int(11),
music_duration varchar(10),
constraint foreign_album foreign key(music_album_id) references album(album_id),
constraint foreign_type foreign key(music_type_id) references category(category_id)
);

/* 5: 用户歌单表 */
create table user_musiclist(
list_id int(11) primary key auto_increment,
list_name varchar(20) not null,
list_time timestamp not null default current_timestamp,
list_uid int(11) not null,
/* 0:普通 1: 喜欢  2: 默认收藏 */
list_love int(1) default 0 check(list_love in (0,1,2)),
constraint foreign_uid foreign key(list_uid) references user(user_id)
);

/* 6: 歌单音乐表: 歌曲收藏表 */
create table list_music(
lid int(11),
mid int(11),
save_time timestamp not null default current_timestamp,
constraint primary_lid_mid primary key(lid,mid),
constraint foreign_lid foreign key(lid) references user_musiclist(list_id)
	       on delete cascade,
constraint foreign_mid foreign key(mid) references music(music_id)
);

/* 7: 歌曲评论表 */
create table music_comment(
mc_id int(11) primary key auto_increment,
music_id int(11) not null,
user_id int(11) not null,
comment varchar(1000) not null,
comment_date timestamp not null default current_timestamp,
constraint foreign_mc_mid foreign key(music_id) references music(music_id),
constraint foreign_mc_uid foreign key(user_id) references user(user_id)
);

/* 8: 歌曲下载记录表 */
create table music_download(
download_id int(11) primary key auto_increment,
music_id int(11) not null,
download_date date,
constraint foreign_md_mid foreign key(music_id) references music(music_id)
);


/* 初始化数据 */
/* 用户 */
insert into user(user_id,user_name,user_pwd,user_gender,user_pic,user_nick,user_type) values(1,'chenpeipei','123456','女','musicCloud/userPic/chenpeipei.jpg','chenpeipei',1);
insert into user(user_id,user_name,user_pwd,user_gender,user_pic,user_nick,user_type) values(2,'admin','123456','女','musicCloud/userPic/chenpeipei.jpg','admin',2);
/* 歌曲分类 */
insert into category(category_id,category_type) values(1,'中国风');
insert into category(category_id,category_type) values(2,'流行');
insert into category(category_id,category_type) values(3,'经典');
insert into category(category_id,category_type) values(4,'国语');
insert into category(category_id,category_type) values(5,'影视');

/* 专辑 */
insert into album(album_id,album_name) values(1,'《走着走着花就开了》');
insert into album(album_id,album_name) values(2,'《星空剪影》');
insert into album(album_id,album_name) values(3,'《琉璃 电视剧原声带》');
insert into album(album_id,album_name) values(4,'《延禧攻略》');
insert into album(album_id,album_name) values(5,'《三生三世十里桃花 电视剧原声带》');
insert into album(album_id,album_name) values(6,'华宇热门');
INSERT INTO `album` VALUES ('7', '关山酒 ');

/* 音乐 */
insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic,music_duration) values('走着走着花就开了','卓舒晨',1,'musicCloud/music/卓舒晨_走着走着花就开了.mp3','musicCloud/lyric/卓舒晨_走着走着花就开了_1.txt',4,'musicCloud/musicPic/卓舒晨_走着走着花就开了_1.jpg','03:48');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic,music_duration) values('星空剪影','蓝心羽',2,'musicCloud/music/蓝心羽_星空剪影.mp3','musicCloud/lyric/蓝心羽_星空剪影_2.txt',2,'musicCloud/musicPic/蓝心羽_星空剪影_2.jpg','03:17');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic,music_duration) values('情人咒','阿云嘎',3,'musicCloud/music/阿云嘎_郁可唯_情人咒.mp3','musicCloud/lyric/阿云嘎_情人咒_3.txt',5,'musicCloud/musicPic/阿云嘎_情人咒_3.jpg','06:02');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic,music_duration) values('雪落下的声音','陆虎',4,'musicCloud/music/雪落下的声音_陆虎.mp3','musicCloud/lyric/陆虎_雪落下的声音_4.txt',5,'musicCloud/musicPic/陆虎_雪落下的声音_4.jpg','05:11');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic,music_duration) values('繁花','董贞',5,'musicCloud/music/董贞_繁花.mp3','musicCloud/lyric/董贞_繁花_5.txt',5,'musicCloud/musicPic/董贞_繁花_5.jpg','03:45');
INSERT INTO `music` VALUES ('6', '关山酒 ', '等什么君', '7', 'musicCloud/musicPic/关山酒.jpg', 'musicCloud/music/关山酒.mp3', '2021-01-02 15:36:44', 'musicCloud/lyric/关山酒_等什么君.txt', '1', '03:54');
INSERT INTO `music` VALUES ('7', '归去来兮', '花粥', '6', 'musicCloud/musicPic/归去来兮_花粥.jpg', 'musicCloud/music/归去来兮_花粥.mp3', '2021-01-02 15:41:20', 'musicCloud/lyric/归去来兮_花粥.txt', '1', '03:26');
INSERT INTO `music` VALUES ('8', '悟空', '戴荃', '6', 'musicCloud/musicPic/悟空_戴荃.jpg', 'musicCloud/music/悟空_戴荃.mp3', '2021-01-02 15:45:53', 'musicCloud/lyric/悟空_戴荃.txt', '1', '03:20');
INSERT INTO `music` VALUES ('9', '断桥残雪', '许嵩', '6', 'musicCloud/musicPic/断桥残雪_许嵩.jpg', 'musicCloud/music/断桥残雪_许嵩.mp3', '2021-01-02 15:51:15', 'musicCloud/lyric/断桥残雪_许嵩.txt', '1', '03:47');
INSERT INTO `music` VALUES ('10', '醉赤壁', '林俊杰', '6', 'musicCloud/musicPic/醉赤壁_林俊杰 .jpg', 'musicCloud/music/醉赤壁_林俊杰.mp3', '2021-01-02 19:25:36', 'musicCloud/lyric/醉赤壁_林俊杰.txt', '1', '04:41');
INSERT INTO `music` VALUES ('11', '声声慢', '崔开潮', '6', 'musicCloud/musicPic/声声慢_崔开潮.jpg', 'musicCloud/music/声声慢_崔开潮.mp3', '2021-01-02 19:31:12', 'musicCloud/lyric/声声慢_崔开潮.txt', '1', '03:32');
INSERT INTO `music` VALUES ('12', '执迷不悟', '鱿籽酱', '6', 'musicCloud/musicPic/执迷不悟_鱿籽酱.jpg', 'musicCloud/music/执迷不悟_鱿籽酱.mp3', '2021-01-02 19:57:38', 'musicCloud/lyric/执迷不悟_鱿籽酱.txt', '2', '03:00');
INSERT INTO `music` VALUES ('13', '踏山河', '是七叔呢', '6', 'musicCloud/musicPic/踏山河_是七叔呢.jpg', 'musicCloud/music/踏山河_是七叔呢.mp3', '2021-01-02 19:58:41', 'musicCloud/lyric/踏山河_是七叔呢.txt', '2', '02:48');
INSERT INTO `music` VALUES ('14', '追光者', '岑宁儿', '6', 'musicCloud/musicPic/追光者_岑宁儿.jpg', 'musicCloud/music/追光者_岑宁儿.mp3', '2021-01-02 19:59:22', 'musicCloud/lyric/追光者_岑宁儿.txt', '2', '03:55');
INSERT INTO `music` VALUES ('15', '我好想你', '苏打绿', '6', 'musicCloud/musicPic/我好想你_苏打绿.jpg', 'musicCloud/music/我好想你_苏打绿.mp3', '2021-01-02 20:00:45', 'musicCloud/lyric/我好想你_苏打绿.txt', '2', '05:24');
INSERT INTO `music` VALUES ('16', '不染', '毛不易', '6', 'musicCloud/musicPic/不染_毛不易.jpg', 'musicCloud/music/不染_毛不易.mp3', '2021-01-02 20:01:48', 'musicCloud/lyric/不染_毛不易.txt', '2', '05:25');
INSERT INTO `music` VALUES ('17', '烟雨行舟', '司南', '6', 'musicCloud/musicPic/烟雨行舟_司南.jpg', 'musicCloud/music/烟雨行舟_司南.mp3', '2021-01-02 20:57:03', 'musicCloud/lyric/烟雨行舟_司南.txt', '2', '04:27');
INSERT INTO `music` VALUES ('18', '月半小夜曲', '李克勤', '6', 'musicCloud/musicPic/月半小夜曲_李克勤.jpg', 'musicCloud/music/月半小夜曲_李克勤.mp3', '2021-01-02 20:58:10', 'musicCloud/lyric/月半小夜曲_李克勤.txt', '3', '04:51');
INSERT INTO `music` VALUES ('19', '甜蜜蜜', '邓丽君', '6', 'musicCloud/musicPic/甜蜜蜜_邓丽君.jpg', 'musicCloud/music/甜蜜蜜_邓丽君.mp3', '2021-01-02 21:00:13', 'musicCloud/lyric/甜蜜蜜_邓丽君.txt', '3', '03:30');
INSERT INTO `music` VALUES ('20', '谢谢你的爱', '刘德华', '6', 'musicCloud/musicPic/谢谢你的爱_刘德华.jpg', 'musicCloud/music/谢谢你的爱_刘德华.mp3', '2021-01-02 21:01:07', 'musicCloud/lyric/谢谢你的爱_刘德华.txt', '3', '04:39');
INSERT INTO `music` VALUES ('21', '遇见', '孙燕姿', '6', 'musicCloud/musicPic/遇见_孙燕姿.jpg', 'musicCloud/music/遇见_孙燕姿.mp3', '2021-01-02 21:06:16', 'musicCloud/lyric/遇见_孙燕姿.txt', '3', '03:30');
INSERT INTO `music` VALUES ('22', '千千阙歌', '陈慧娴', '6', 'musicCloud/musicPic/千千阙歌_陈慧娴.jpg', 'musicCloud/music/千千阙歌_陈慧娴.mp3', '2021-01-02 21:07:31', 'musicCloud/lyric/千千阙歌_陈慧娴.txt', '3', '04:59');
INSERT INTO `music` VALUES ('23', '我恨我痴心', '刘德华', '6', 'musicCloud/musicPic/我恨我痴心_刘德华.jpg', 'musicCloud/music/我恨我痴心_刘德华.mp3', '2021-01-02 21:08:07', 'musicCloud/lyric/我恨我痴心_刘德华.txt', '3', '04:10');
INSERT INTO `music` VALUES ('24', '岁月神偷', '金玟岐', '6', 'musicCloud/musicPic/岁月神偷_金玟岐.jpg', 'musicCloud/music/岁月神偷_金玟岐.mp3', '2021-01-02 21:44:49', 'musicCloud/lyric/岁月神偷_金玟岐.txt', '4', '02:42');
INSERT INTO `music` VALUES ('25', '说散就散', 'JC 陈咏桐', '6', 'musicCloud/musicPic/说散就散_JC 陈咏桐.jpg', 'musicCloud/music/说散就散_JC陈咏桐.mp3', '2021-01-02 21:45:43', 'musicCloud/lyric/说散就散_JC陈咏桐.txt', '4', '03:52');
INSERT INTO `music` VALUES ('26', '云烟成雨', '房东的猫', '6', 'musicCloud/musicPic/云烟成雨_房东的猫.jpg', 'musicCloud/music/云烟成雨_房东的猫.mp3', '2021-01-02 21:46:38', 'musicCloud/lyric/云烟成雨_房东的猫.txt', '4', '04:00');
INSERT INTO `music` VALUES ('27', '往后余生', '马良', '6', 'musicCloud/musicPic/往后余生_马良.jpg', 'musicCloud/music/往后余生_马良.mp3', '2021-01-02 21:47:32', 'musicCloud/lyric/往后余生_马良.txt', '4', '03:15');
INSERT INTO `music` VALUES ('28', '有点甜', '汪苏泷,By2', '6', 'musicCloud/musicPic/有点甜_汪苏泷,By2.jpg', 'musicCloud/music/有点甜_汪苏泷,By2.mp3', '2021-01-02 21:48:34', 'musicCloud/lyric/有点甜_汪苏泷,By2.txt', '4', '03:55');
INSERT INTO `music` VALUES ('29', '小手拉大手', '梁静茹,三十位素人', '6', 'musicCloud/musicPic/小手拉大手 (Live)_梁静茹,三十位素人.jpg', 'musicCloud/music/小手拉大手 (Live)_梁静茹,三十位素人.mp3', '2021-01-02 21:49:18', 'musicCloud/lyric/小手拉大手 (Live)_梁静茹,三十位素人.txt', '4', '03:38');
INSERT INTO `music` VALUES ('30', 'Freedom', 'Anthony Hamilton', '6', 'musicCloud/musicPic/Freedom_Anthony Hamilton,Elayna Boynton.jpg', 'musicCloud/music/Freedom_Anthony Hamilton,Elayna Boynton.mp3', '2021-01-02 21:52:14', 'musicCloud/lyric/Freedom_Anthony Hamilton,Elayna Boynton.txt', '5', '03:56');
INSERT INTO `music` VALUES ('31', 'Django', 'Luis Bacalov', '6', 'musicCloud/musicPic/Django_Luis Bacalov,Rocky Roberts.jpg\r\n', 'musicCloud/music/Django_Luis Bacalov,Rocky Roberts.mp3', '2021-01-02 21:53:25', 'musicCloud/lyric/Django_Luis Bacalov,Rocky Roberts.txt', '5', '02:53');
INSERT INTO `music` VALUES ('32', 'You Never Can Tell', 'Chuck Berry', '6', 'musicCloud/musicPic/You Never Can Tell_Chuck Berry.jpg', 'musicCloud/music/You Never Can Tell_Chuck Berry.mp3', '2021-01-02 22:04:42', 'musicCloud/lyric/You Never Can Tell_Chuck Berry.txt', '5', '02:42 ');
INSERT INTO `music` VALUES ('33', 'Bang Bang(My Baby Shot Me Down)', 'Nancy Sinatra', '6', 'musicCloud/musicPic/Bang Bang (My Baby Shot Me Down)_Nancy Sinatra.jpg', 'musicCloud/music/Bang Bang (My Baby Shot Me Down)_Nancy Sinatra.mp3', '2021-01-02 22:05:39', 'musicCloud/lyric/Bang Bang (My Baby Shot Me Down)_Nancy Sinatra.txt', '5', '02:40');
INSERT INTO `music` VALUES ('34', 'Twisted Nerve', 'Bernard Herrmann', '6', 'musicCloud/musicPic/Twisted Nerve_Bernard Herrmann.jpg', 'musicCloud/music/Twisted Nerve_Bernard Herrmann.mp3', '2021-01-02 22:13:58', 'musicCloud/lyric/Twisted Nerve_Bernard Herrmann.txt', '5', '01:29');
INSERT INTO `music` VALUES ('35', 'Now You\'re All Alone', 'David Hess', '6', 'musicCloud/musicPic/Now You\'re All Alone_David Hess.jpg', 'musicCloud/music/Now You\'re All Alone_David Hess.mp3', '2021-01-02 22:14:47', 'musicCloud/lyric/Now You\'re All Alone_David Hess.txt', '5', '01:29');

/* 歌单 */
insert into user_musiclist(list_id,list_name,list_uid,list_love) values(1,'我喜欢的音乐',1,1);
insert into user_musiclist(list_id,list_name,list_uid,list_love) values(2,'默认收藏',1,2);

/* 歌曲收藏表 */
insert into list_music(lid,mid) values(1,1);
insert into list_music(lid,mid) values(1,2);
insert into list_music(lid,mid) values(1,3);
insert into list_music(lid,mid) values(1,4);
insert into list_music(lid,mid) values(1,5);


SELECT @@global.sql_mode;
set GLOBAL sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';