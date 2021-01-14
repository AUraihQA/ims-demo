create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(id int primary key auto_increment, item_name varchar(40), Price double);
create table if not exists ims.orders(id int primary key auto_increment, order_address varchar(40), order_date varchar(40), customerid int, FOREIGN KEY (customerid) REFERENCES customers(id));
create table if not exists ims.orders_items(id int primary key auto_increment, orderID int, itemsID int, FOREIGN KEY (orderID) REFERENCES orders(id), FOREIGN KEY (itemsID) REFERENCES items(id))