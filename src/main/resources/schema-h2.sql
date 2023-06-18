DROP TABLE IF EXISTS hard_drive;
DROP TABLE IF EXISTS monitor;
DROP TABLE IF EXISTS note_book;
DROP TABLE IF EXISTS personal_computer;

DROP SEQUENCE  IF EXISTS hard_drive_seq;
DROP SEQUENCE  IF EXISTS monitor_seq;
DROP SEQUENCE  IF EXISTS note_book_seq;
DROP SEQUENCE  IF EXISTS personal_computer_seq;

CREATE TABLE hard_drive
(
    id            BIGINT NOT NULL ,
    company       VARCHAR(255),
    price         INTEGER,
    quantity      INTEGER,
    serial_number VARCHAR(255),
    capacity      INTEGER,
    PRIMARY KEY (id)
);
CREATE TABLE monitor
(
    id            BIGINT NOT NULL ,
    company       VARCHAR(255),
    price         INTEGER,
    quantity      INTEGER,
    serial_number VARCHAR(255),
    diagonal      INTEGER,
    PRIMARY KEY (id)
);
CREATE TABLE note_book
(
    id            BIGINT NOT NULL ,
    company       VARCHAR(255),
    price         INTEGER,
    quantity      INTEGER,
    serial_number VARCHAR(255),
    size          INTEGER,
    PRIMARY KEY (id)
);
CREATE TABLE personal_computer
(
    id            BIGINT NOT NULL ,
    company       VARCHAR(255),
    price         INTEGER,
    quantity      INTEGER,
    serial_number VARCHAR(255),
    "form-factor" VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE SEQUENCE hard_drive_seq START WITH 1 INCREMENT BY  50;
CREATE SEQUENCE monitor_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE note_book_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE personal_computer_seq START WITH 1 INCREMENT BY 50;