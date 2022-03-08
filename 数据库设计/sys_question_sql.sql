/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/2/15 11:20:27                           */
/*==============================================================*/


drop table if exists ssm_question;

/*==============================================================*/
/* Table: ssm_question                                          */
/*==============================================================*/
create table ssm_question
(
   question_id          int not null auto_increment,
   question_title       varchar(255),
   question_desc        text,
   question_img         varchar(255),
   question_order       int,
   question_status      varchar(2),
   primary key (question_id)
);

