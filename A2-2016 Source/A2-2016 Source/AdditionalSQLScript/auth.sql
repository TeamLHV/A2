CREATE DATABASE `auth` /*!40100 DEFAULT CHARACTER SET latin1 */;

use auth;

CREATE TABLE `loginlog` (
  `username` varchar(45) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `activity` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `department` varchar(45) NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `u_id_UNIQUE` (`u_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

CREATE TABLE `sessions` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `upid` varchar(45) NOT NULL,
  `u_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`s_id`),
  UNIQUE KEY `s_id_UNIQUE` (`s_id`),
  UNIQUE KEY `key_UNIQUE` (`upid`),
  UNIQUE KEY `u_id_UNIQUE` (`u_id`),
  CONSTRAINT `SESSIONS_UID_USERS_UID` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

insert into users (username, password, firstname, lastname, department) values ('admin','55bbeb5fefa388fc850000a090efc0eb','first','last','CMU');
select u_id from users where username = 'admin' into @admin_id ;
insert into sessions (upid, u_id, timestamp) values ('adminsessionmock', @admin_id, '1970-01-01 00:24:14');



