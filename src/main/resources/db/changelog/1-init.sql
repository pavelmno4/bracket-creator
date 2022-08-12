--liquibase formatted sql
--changeset pkozlov:1-init.sql

-- tournament
create sequence if not exists tournament_id_seq
    minvalue 1
    increment 1;

create table if not exists tournament
(
    id    bigint       not null
        primary key,
    date  date         not null,
    name  varchar(255) not null,
    place varchar(255) not null
);

-- category
create sequence if not exists category_id_seq
    minvalue 1
    increment 1;

create table if not exists category
(
    id                         bigint         not null
        primary key,
    lower_birth_year_threshold smallint       not null,
    upper_birth_year_threshold smallint       not null,
    weight                     numeric(19, 2) not null
);

create index if not exists category_lower_birth_year_threshold_weight_idx
    on category (lower_birth_year_threshold, weight);

-- team
create sequence if not exists team_id_seq
    minvalue 1
    increment 1;

create table if not exists team
(
    id   bigint       not null
        primary key,
    city varchar(255) not null,
    name varchar(255) not null
);

-- participant
create sequence if not exists participant_id_seq
    minvalue 1
    increment 1;

create table if not exists participant
(
    id            bigint         not null
        primary key,
    birth_date    date           not null,
    first_name    varchar(255)   not null,
    gender        varchar(255)   not null,
    last_name     varchar(255)   not null,
    weight        numeric(19, 2) not null,
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



