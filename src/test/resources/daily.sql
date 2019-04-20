drop table if exists user;
create table if not exists user
(
    id       bigint       not null auto_increment,
    username varchar(20)  not null,
    password varchar(100) not null,
    name     varchar(50) default null,
    email    varchar(50) default null,
    role     tinyint     default 0,
    img_url  text        default null,
    primary key (id)
) engine = InnoDB
  default charset = utf8;
insert into user(username, password, role)
values ('narata', '$2a$10$FckY1NC6Pi6STtH5KCbJ3uH5CaabdZCFNljxVweyVCxvn5TyOgyHi', 1);
insert into user(username, password) values ('user', '$2a$10$FckY1NC6Pi6STtH5KCbJ3uH5CaabdZCFNljxVweyVCxvn5TyOgyHi');
insert into user(username, password) VALUES ('user1', '$2a$10$FckY1NC6Pi6STtH5KCbJ3uH5CaabdZCFNljxVweyVCxvn5TyOgyHi''');

drop table if exists house;
create table if not exists house
(
    id          bigint not null auto_increment primary key,
    rent        int    not null comment '租金',
    location    varchar(50)  default null comment '位置',
    size        int          default null comment '大小',
    type        varchar(20)  default null comment '户型',
    floor       int          default null comment '楼层',
    orientation varchar(20)  default null comment '朝向',
    status      int          default 0 comment '状态，0未租，1已租',
    image       varchar(200) default null comment '图片位置',
    rent_house  tinyint default 0 comment '0 房源信息，1 求租信息',
    user_id     bigint not null comment '用户id',
    is_deleted  tinyint not null default 0 comment '删除标志位'
) engine = InnoDB
  default charset = utf8;

drop table if exists collection;
create table if not exists collection
(
    id bigint not null auto_increment primary key,
    house_id bigint not null,
    user_id bigint not null,
    id_deleted tinyint not null default 0
) engine = InnoDb
default charset = utf8;

drop table if exists comment;
create table if not exists comment(
    id bigint not null auto_increment primary key ,
    house_id bigint not null ,
    user_id bigint not null ,
    `comment` text default null,
    `time` datetime default null,
    id_deleted tinyint not null default 0
) engine = InnoDb default charset = utf8;