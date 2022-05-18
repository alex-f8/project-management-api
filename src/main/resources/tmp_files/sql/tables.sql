create database project_management_af;

create table usr(
id serial primary key,
name varchar(30) not null,
surname varchar(30) not null,
password varchar(40),
role varchar(30));

create table project(
id serial primary key,
name varchar(50) not null,
date_start date,
date_finish date);

create table project_usr(
    id serial primary key,
	project_id bigint,
	usr_id bigint,
	work_day date,
	unique(project_id, usr_id, work_day),
	foreign key(project_id) references project(id) on delete cascade,
	foreign key(usr_id) references usr(id) on delete cascade
);

create table usr_activity(
    id serial primary key,
    usr_id bigint not null,
    date_time timestamp not null,
	start_stop boolean not null,
    foreign key(usr_id) references usr(id) on delete cascade
);