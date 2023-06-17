DROP TABLE IF EXISTS hard_drive;
drop table if exists monitor;
drop table if exists note_book;
drop table if exists personal_computer;

drop sequence if exists hard_drive_seq;
drop sequence if exists monitor_seq;
drop sequence if exists note_book_seq;
drop sequence if exists personal_computer_seq;

create table hard_drive
(
    id            bigint not null,
    company       varchar(255),
    price         integer,
    quantity      integer,
    serial_number varchar(255),
    capacity      integer,
    primary key (id)
);
create table monitor
(
    id            bigint not null,
    company       varchar(255),
    price         integer,
    quantity      integer,
    serial_number varchar(255),
    diagonal      integer,
    primary key (id)
);
create table note_book
(
    id            bigint not null,
    company       varchar(255),
    price         integer,
    quantity      integer,
    serial_number varchar(255),
    size          integer,
    primary key (id)
);
create table personal_computer
(
    id            bigint not null,
    company       varchar(255),
    price         integer,
    quantity      integer,
    serial_number varchar(255),
    "form-factor" varchar(255),
    primary key (id)
);
create sequence hard_drive_seq start with 1 increment by 50;
create sequence monitor_seq start with 1 increment by 50;
create sequence note_book_seq start with 1 increment by 50;
create sequence personal_computer_seq start with 1 increment by 50;