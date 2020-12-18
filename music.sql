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

/* 6: 歌单音乐表 */
create table list_music(
lid int(11),
mid int(11),
constraint primary_lid_mid primary key(lid,mid),
constraint foreign_lid foreign key(lid) references user_musiclist(list_id)
	       on delete cascade,
constraint foreign_mid foreign key(mid) references music(music_id)
);

/* 初始化数据 */
/* 用户 */
insert into user(user_id,user_name,user_pwd,user_gender,user_pic,user_nick) values(1,'chenpeipei','123456','女','musicCloud/userPic/chenpeipei.jpg','chenpeipei');

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

/* 音乐 */
insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic) values('走着走着花就开了','卓舒晨',1,'musicCloud/music/卓舒晨_走着走着花就开了.mp3','musicCloud/lyric/卓舒晨_走着走着花就开了_1.txt',4,'musicCloud/musicPic/卓舒晨_走着走着花就开了_1.jpg');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic) values('星空剪影','蓝心羽',2,'musicCloud/music/蓝心羽_星空剪影.mp3','musicCloud/lyric/蓝心羽_星空剪影_2.txt',2,'musicCloud/musicPic/蓝心羽_星空剪影_2.jpg');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic) values('情人咒','阿云嘎',3,'musicCloud/music/阿云嘎_郁可唯_情人咒.mp3','musicCloud/lyric/阿云嘎_情人咒_3.txt',5,'musicCloud/musicPic/阿云嘎_情人咒_3.jpg');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic) values('雪落下的声音','陆虎',4,'musicCloud/music/雪落下的声音_陆虎.mp3','musicCloud/lyric/陆虎_雪落下的声音_4.txt',5,'musicCloud/musicPic/陆虎_雪落下的声音_4.jpg');

insert into music(music_name,music_author,music_album_id,music_path,music_lyric_path,music_type_id,music_pic) values('繁花','董贞',5,'musicCloud/music/董贞_繁花.mp3','musicCloud/lyric/董贞_繁花_5.txt',5,'musicCloud/musicPic/董贞_繁花_5.jpg');


/* 歌单 */
insert into user_musiclist(list_id,list_name,list_uid,list_love) values(1,'我喜欢的音乐',1,1);
insert into user_musiclist(list_id,list_name,list_uid,list_love) values(2,'默认收藏',1,2);

/* 歌曲收藏表 */
insert into list_music(lid,mid) values(1,1);
insert into list_music(lid,mid) values(1,2);
insert into list_music(lid,mid) values(1,3);
insert into list_music(lid,mid) values(1,4);
insert into list_music(lid,mid) values(1,5);