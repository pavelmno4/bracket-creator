--liquibase formatted sql
--changeset pkozlov:2 logicalFilePath:db/changelog/2-category-function.sql

drop function if exists create_category;

create or replace function create_category(
    lower_age integer,
    upper_age integer,
    category_weight numeric(19, 2)
) returns void as
$$
begin
    insert into category (id, lower_birth_year_threshold, upper_birth_year_threshold, weight)
    values (nextval('category_id_seq'),
            date_part('year', current_date) - upper_age,
            date_part('year', current_date) - lower_age,
            category_weight);
end;
$$ language plpgsql;
