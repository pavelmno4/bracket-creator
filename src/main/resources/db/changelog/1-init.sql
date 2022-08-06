--liquibase formatted sql
--changeset pkozlov:1-init.sql

-- tournament
create sequence if not exists tournament_seq
    minvalue 1
    increment 1;

create table if not exists tournament
(
    id    bigint not null
        primary key,
    date  timestamp,
    name  varchar(255),
    place varchar(255)
);

-- category
create sequence if not exists category_seq
    minvalue 1
    increment 1;

create table if not exists category
(
    id                         bigint not null
        primary key,
    lower_birth_date_threshold date,
    upper_birth_date_threshold date,
    weight                     numeric(19, 2)
);

-- team
create sequence if not exists team_seq
    minvalue 1
    increment 1;

create table if not exists team
(
    id   bigint not null
        primary key,
    city varchar(255),
    name varchar(255)
);

-- participant
create sequence if not exists participant_seq
    minvalue 1
    increment 1;

create table if not exists participant
(
    id            bigint not null
        primary key,
    birth_date    date,
    first_name    varchar(255),
    gender        integer,
    last_name     varchar(255),
    weight        numeric(19, 2),
    category_id   bigint
        constraint participant_category_id_fk
            references category,
    team_id       bigint
        constraint participant_team_id_fk
            references team,
    tournament_id bigint
        constraint participant_tournament_id_fk
            references tournament
);

create index participant_team_id_idx
    on participant (team_id);

create index participant_category_id_idx
    on participant (category_id);

create index participant_tournament_id_idx
    on participant (tournament_id);



