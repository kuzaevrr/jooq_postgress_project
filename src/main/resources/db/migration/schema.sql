DROP TABLE IF EXISTS INCIDENTS;

CREATE TABLE INCIDENTS (
  id_incident varchar(25) NOT NULL,
  status_value varchar(25),
  priority int,
  group_tp3 varchar(25),
  responsible_tp3 varchar(25),
  time_appointment DATETIME,
  time_work DATETIME,
  time_done DATETIME,
   deadline DATETIME,
    overdue boolean,
     code_completion varchar(25),
  PRIMARY KEY (id_incident)
);

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
     username VARCHAR(10) NOT NULL,
     password VARCHAR(32) NOT NULL,
     enabled BOOLEAN NOT NULL,
     PRIMARY KEY (username)
);

DROP TABLE IF EXISTS authorities;

CREATE TABLE IF NOT EXISTS authorities (
     username VARCHAR(10) NOT NULL,
     authority VARCHAR(10) NOT NULL,
     FOREIGN KEY (username) REFERENCES users(username)
);

DROP TABLE IF EXISTS dayOff;

CREATE TABLE IF NOT EXISTS dayOff (
     dayOff DATETIME NOT NULL,
    PRIMARY KEY (dayOff)
);

