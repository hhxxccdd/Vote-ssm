/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/2/10 18:34:12                           */
/*==============================================================*/


drop table if exists sys_user;

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   user_id              int not null auto_increment comment '”√ªßid',
   username             varchar(64),
   password             varchar(128),
   phone                varchar(13),
   email                varchar(36),
   status               tinyint,
   is_admain            tinyint,
   primary key (user_id)
);

