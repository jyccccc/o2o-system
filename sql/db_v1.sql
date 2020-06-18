use orderdb
go

-- auto-generated definition
create table customer
(
    id        int auto_increment
        primary key,
    username  varchar(40)   null,
    password  varchar(40)   null,
    authority int default 1 null,
    phone     varchar(20)   null
)
    comment '顾客';

INSERT INTO orderdb.customer (id, username, password, authority, phone) VALUES (1, 'jyc', '123', 1, '666');
INSERT INTO orderdb.customer (id, username, password, authority, phone) VALUES (2, 'spc', '1222', 1, '8888');
INSERT INTO orderdb.customer (id, username, password, authority, phone) VALUES (3, 'lcj', '1223', 1, '16546');
INSERT INTO orderdb.customer (id, username, password, authority, phone) VALUES (4, 'jyccc', '666666', 1, '10086');

-- auto-generated definition
create table dish
(
    id          int auto_increment
        primary key,
    name        varchar(20)  null,
    type        varchar(20)  null,
    price       double       null,
    pic         varchar(40)  null,
    description varchar(255) null,
    state       int          null
)
    comment '菜品';


INSERT INTO orderdb.dish (id, name, type, price, pic, description, state) VALUES (1, '鱼香肉丝', '热门', 28.88, '1.png', '真的很好吃！！', 1);
INSERT INTO orderdb.dish (id, name, type, price, pic, description, state) VALUES (2, '凉拌海带丝', '热门', 2.88, '2.png', '好吃加一！！', 1);
INSERT INTO orderdb.dish (id, name, type, price, pic, description, state) VALUES (3, '土豆球', '爆款', 10.88, '3.png', '巨好吃！！', 1);
INSERT INTO orderdb.dish (id, name, type, price, pic, description, state) VALUES (4, '小签里脊肉', '爆款', 18.88, '4.png', '喜欢的不得了！！', 0);

-- auto-generated definition
create table tables
(
    id      int auto_increment
        primary key,
    place   varchar(40) null,
    state   int         null,
    qr_code varchar(40) null
)
    comment '餐桌';

INSERT INTO orderdb.tables (id, place, state, qr_code) VALUES (1, '6-10', 1, '6-10.png');

-- auto-generated definition
create table orders
(
    id          int auto_increment
        primary key,
    customer_id int          null,
    create_time datetime     not null,
    price       double       not null,
    state       int          not null,
    notes       varchar(255) null,
    table_id    int          null,
    constraint order_customer_fk
        foreign key (customer_id) references customer (id)
)
    comment '订单表';

create index order_table_fk
    on orders (table_id);


-- auto-generated definition
create table dishes
(
    id          int auto_increment
        primary key,
    order_id    int           not null,
    dish_id     int           not null,
    create_time datetime      null,
    dish_num    int default 1 null,
    price       double        null,
    name        varchar(20)   null,
    pic         varchar(20)   null,
    constraint dishes_dish_fk
        foreign key (dish_id) references dish (id),
    constraint dishes_order_fk
        foreign key (order_id) references orders (id)
)
    comment '订单选菜表';