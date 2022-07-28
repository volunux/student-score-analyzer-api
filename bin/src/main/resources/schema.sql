drop table if exists student cascade;
drop table if exists subject cascade;

create table if not exists student (
	id bigserial not null,
	admission_number varchar(250) not null,
	email_address varchar(50) not null,
	first_name VARCHAR(20) not null,
	last_name VARCHAR(20) not null,
	home_address VARCHAR(300) not null,
	primary key (id)
);

alter table student
	add constraint uqk_email_address unique (email_address);

create table if not exists subject (
    id bigserial not null,
    title varchar(150) not null,
    code varchar(6) not null,
    year varchar(4) not null,
    unit int not null,
    primary key(id)
);

alter table subject
    add constraint uqk_subj_code unique (code);