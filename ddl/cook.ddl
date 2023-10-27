-- auto-generated definition
create table cook
(
    COOK_id        bigint auto_increment
        primary key,
    COOK_expertise varchar(255) null,
    COOK_name      varchar(255) not null,
    COOK_rank      int          not null
);

