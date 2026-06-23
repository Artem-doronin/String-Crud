drop table if exists person;
create table if not exists  person(
    id serial primary key ,
    name varchar(30) not null ,
    age integer not null
);