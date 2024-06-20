CREATE DATABASE todo;
use todo;

CREATE TABLE IF NOT EXISTS todos(
    id int not null AUTO_INCREMENT,
    description varchar(500),
    status varchar(500),
    startDate timestamp,
    targetDate timestamp,
    PRIMARY KEY (Id)
);

INSERT INTO todos(description, status, startDate, targetDate)
VALUES ('this is a sample todo', 'WIP', current_time, current_time);