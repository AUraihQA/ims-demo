insert into ims.customers(first_name, surname) values('chris', 'perrins');
insert into ims.customers(first_name, surname) values('rhys', 'thompson');
insert into ims.customers(first_name, surname) values('nic', 'johnson');
insert into ims.customers(first_name, surname) values('jordon', 'harrison');
insert into ims.items(item_name, Price) values('item1', 300.00);
insert into ims.items(item_name, Price) values('item2', 500.00);
insert into ims.items(item_name, Price) values('item3', 100.00);
insert into ims.orders(order_address, order_date, customerid) values("123 road", "19th January 2020", 1);
insert into ims.orders(order_address, order_date, customerid) values("123 street", "18th January 2020", 2);
insert into ims.orders(order_address, order_date, customerid) values("123 avenue", "17th January 2020", 1);
insert into ims.order_items(orderID, itemID, quantity) values(1, 1, 2);
insert into ims.order_items(orderID, itemID, quantity) values(2, 2, 1);
insert into ims.order_items(orderID, itemID, quantity) values(3, 3, 3);




