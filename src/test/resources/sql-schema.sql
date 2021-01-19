create database if not exists ims_test;
drop table if exists ims_test.customers;
drop table if exists ims_test.items;
drop table if exists ims_test.orders;
drop table if exists ims_test.order_items;
create table ims_test.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table ims_test.items(id int primary key auto_increment, item_name varchar(40), Price double);
create table ims_test.orders(id int primary key auto_increment, order_address varchar(40), order_date varchar(20), customerid int, FOREIGN KEY (customerid) REFERENCES customers(id));
create table ims_test.order_items(id int primary key auto_increment, orderID int, itemID int, quantity int, FOREIGN KEY (orderID) REFERENCES orders(id), FOREIGN KEY (itemID) REFERENCES items(id))