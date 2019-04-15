drop database if exists daily;
create database if not exists daily DEFAULT CHARACTER SET utf8;

drop table if exists user;
create table if not exists user
(
    id       bigint       not null auto_increment,
    username varchar(20)  not null,
    password varchar(100) not null,
    name     varchar(50) default null,
    img_url  text        default null,
    primary key (id)
) engine = InnoDB
  default charset = utf8;
insert into user(username, password)
values ('narata', '$2a$10$FckY1NC6Pi6STtH5KCbJ3uH5CaabdZCFNljxVweyVCxvn5TyOgyHi');

drop table if exists daily;
create table daily
(
    id          bigint       not null auto_increment,
    title       varchar(100) not null,
    data        text         not null,
    update_time VARCHAR(20),
    user_id     bigint       not null,
    is_deleted  tinyint      not null DEFAULT 0,
    primary key (id)
) engine = InnoDb
  default charset = utf8;