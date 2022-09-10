--liquibase formatted sql
--changeset pkozlov:4 logicalFilePath:db/changelog/4-user-data.sql

-- user
create sequence if not exists usr_id_seq
    minvalue 1
    increment 1;

create table if not exists usr
(
    id       bigint       not null,
    username varchar(255) not null,
    password varchar(255) not null,
    deleted  boolean      not null,
    primary key (id)
);

create unique index if not exists usr_username_idx on usr (username);

insert into usr (id, username, password, deleted)
values (nextval('usr_id_seq'), 'admin', '$2a$10$CMK1jw6CwlIcVrB/RW/s6e0uTbNnWWZn1SGJyJEjXvVZ2Elv.DEoK', false);

-- user_role
create table user_role
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role)
);

create unique index if not exists user_role_user_id_idx on user_role (user_id);

insert into user_role (user_id, role)
values (currval('usr_id_seq'), 'ADMIN');