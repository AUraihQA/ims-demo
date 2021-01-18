create database if not exists ims;
drop table if exists ims.customers;
drop table if exists ims.items;
create table ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table ims.items(id int primary key auto_increment, item_name varchar(40), Price double);
