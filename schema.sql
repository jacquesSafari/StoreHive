DROP DATABASE IF EXISTS storehive;
CREATE DATABASE storehive;
use storehive;

create table storelocation
(
 id int PRIMARY KEY AUTO_INCREMENT,
 latitude varchar(255) NOT NULL,
 longitude varchar(255) NOT NULL
);
create table storeowner
(
 id int PRIMARY KEY AUTO_INCREMENT,
 fullname varchar(255) NOT NULL,
 email varchar(255) NOT NULL UNIQUE,
 password varchar(255) NOT NULL,
 registered_date date NOT NULL,
 device_id varchar(255) NOT NULL
);
create table store
(
 id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
 owner_email varchar(255) NOT NULL, 
 shop_name varchar(255) NOT NULL,
 description varchar(255) NOT NULL,
 last_opened_date date,
 is_open varchar(255) NOT NULL,
 l_id int,
 so_id int,
 FOREIGN KEY (l_id) REFERENCES storelocation(id),
 FOREIGN KEY (so_id) REFERENCES storeowner(id)
);
create table category
(
 id int PRIMARY KEY AUTO_INCREMENT,
 category_name varchar(255),
 category_description varchar(255)
);
create table product
(
 id int PRIMARY KEY AUTO_INCREMENT,
 product_name varchar(255),
 product_description varchar(255),
 product_price int,
 c_id int,
 s_id int,
 FOREIGN KEY (c_id) REFERENCES category(id),
 FOREIGN KEY (s_id) REFERENCES store(id)
);
create table shoppinglist
(
 id int PRIMARY KEY AUTO_INCREMENT,
 list_name varchar(255) 
);
create table shoppinglistitem
(
 id int PRIMARY KEY AUTO_INCREMENT,
 item_name varchar(255),
 c_id int, 
 li_id int,
 FOREIGN KEY (li_id) REFERENCES shoppinglist(id)
);
create table transaction
(
 id int PRIMARY KEY AUTO_INCREMENT,
 time_stamp DATETIME,
 amount_due int, 
 s_id int,
 FOREIGN KEY (s_id) REFERENCES store(id)
);
create table transactiongoods
(
 id int PRIMARY KEY AUTO_INCREMENT,
 quantity int,
 total int, 
 p_id int,
 t_id int,
 FOREIGN KEY (p_id) REFERENCES product(id),
 FOREIGN KEY (t_id) REFERENCES transaction(id)
);

