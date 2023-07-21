-- CREATE SCHEMA `mybnb` ;

-- CREATE USER 'fred'@'localhost' IDENTIFIED BY 'fred' ;
-- GRANT ALL ON mybnb.* TO 'fred'@'localhost';

-- use mybnb;

use railway;

create table acc_occupancy(
	acc_id	varchar(10) not null,
    vacancy	int not null,
    constraint pk_acc_id primary key (acc_id)
);

create table reservations(
	resv_id			varchar(8) not null,
    name			varchar(128) not null,
    email			varchar(128) not null,
    acc_id			varchar(10) not null,
    arrival_date	date not null,
    duration		int not null,
    constraint pk_resv_id primary key (resv_id),
    constraint fk_acc_id foreign key (acc_id) references acc_occupancy(acc_id),
    constraint chk_duration check(duration >= 0)
);

-- select acc_id from acc_occupancy;

-- select vacancy from acc_occupancy 
-- 	where acc_id = 16134812;
--     
-- insert into reservations (resv_id,name,email,acc_id,arrival_date,duration) values (?, ?, ?,?,?,?);

-- update acc_occupancy set vacancy = ? where acc_id = ?;