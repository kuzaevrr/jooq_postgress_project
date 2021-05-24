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