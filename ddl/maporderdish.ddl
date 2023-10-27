-- auto-generated definition
create table maporderdish
(
    MOD_id       bigint auto_increment
        primary key,
    MOD_dish_id  bigint null,
    MOD_order_id bigint null,
    constraint maporderdish_dish_DISH_id_fk
        foreign key (MOD_dish_id) references dish (DISH_id),
    constraint maporderdish_order_ORDER_id_fk
        foreign key (MOD_order_id) references `order` (ORDER_id)
);

