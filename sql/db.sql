create database orderdb
go

use orderdb
go

create table customer
(
	id int auto_increment,
	username varchar(40) null,
	password varchar(40) null,
	authority int default 1 null,
	phone varchar(20) null,
	constraint customer_pk
		primary key (id)
)
comment '顾客';

