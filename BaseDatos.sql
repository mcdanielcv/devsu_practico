CREATE DATABASE microservicios_db;

use microservicios_db;

CREATE TABLE IF NOT EXISTS client (
  `state` varchar(10) NOT NULL,
  `client_id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `UKbfgjs3fem0hmjhvih80158x29` (`email`),
  CONSTRAINT `FKcxli0sgm0c24a09lfqwoi9wmt` FOREIGN KEY (`client_id`) REFERENCES `person` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `person` (
  `age` int NOT NULL,
  `client_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `card_id` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `UKccxlhn4kvfl9rcx4pprpd47w3` (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS account (
  `available_balance` double NOT NULL,
  `initial_balance` double NOT NULL,
  `account_number` bigint NOT NULL,
  `client_id` bigint NOT NULL,
  `account_type` varchar(255) NOT NULL,
  `state` varchar(10) NOT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS transaction  (
  `balance` double NOT NULL,
  `transaction_date` date NOT NULL,
  `value` double NOT NULL,
  `account_number` bigint NOT NULL,
  `num_transaccion` bigint NOT NULL AUTO_INCREMENT,
  `type_movement` varchar(255) NOT NULL,
  PRIMARY KEY (`num_transaccion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `person` (`name`,`gender`,`card_id`,`age`,`address`,`phone`) VALUES ('Jose Lema',NULL,'1721521613',21,'Otavalo sn y principal ','098254785');
INSERT INTO `client` (`client_id`,`password`,`email`,`state`) VALUES ((select max(client_id) from person),'$2a$10$lNSNZFsbTytv5XfA1nb1z.6sASFr8pMbG7Jg0DuXQxuVexJ9uT262', 'jose@mail.com','true');

INSERT INTO `person` (`name`,`gender`,`card_id`,`age`,`address`,`phone`) VALUES ('Marianela Montalvo',NULL,'1721521614',25,'Amazonas y  NNUU','097548965');
INSERT INTO `client` (`client_id`,`password`,`email`,`state`) VALUES ((select max(client_id) from person),'$2a$10$zEh2OCdLFd5nvITfbqA9T.KW.1o7WJ2JCyvndX8s51XSHhs/P3ZG6', 'marianela@mail.com','true');

INSERT INTO `person` (`name`,`gender`,`card_id`,`age`,`address`,`phone`) VALUES ('Juan Osorio',NULL,'1721521615',29,'13 junio y Equinoccial','098874587');
INSERT INTO `client` (`client_id`,`password`,`email`,`state`) VALUES ((select max(client_id) from person),'$2a$10$CzBVoFPiRErxBgXfoaS8fO1J2upddo1vlp2deEmV/UibhCouEwpK2', 'juan@mail.com','true');


INSERT INTO `account` (`account_number`,`client_id`,`initial_balance`,`account_type`,`available_balance`,`state`) VALUES (478758,1,2000,'Ahorros',2000,'true');
INSERT INTO `account` (`account_number`,`client_id`,`initial_balance`,`account_type`,`available_balance`,`state`) VALUES (225487,2,100,'Corriente',100,'true');
INSERT INTO `account` (`account_number`,`client_id`,`initial_balance`,`account_type`,`available_balance`,`state`) VALUES (495878,3,0,'Ahorros',0,'true');
INSERT INTO `account` (`account_number`,`client_id`,`initial_balance`,`account_type`,`available_balance`,`state`) VALUES (496825,2,540,'Ahorros',540,'true');
INSERT INTO `account` (`account_number`,`client_id`,`initial_balance`,`account_type`,`available_balance`,`state`) VALUES (585545,1,1000,'Corriente',1000,'true');

INSERT INTO `transaction` (`account_number`,`type_movement`,`transaction_date`,`value`,`balance`) VALUES (478758,'Retiro','2025-03-22 00:00:00.000000',-575,1425);
INSERT INTO `transaction` (`account_number`,`type_movement`,`transaction_date`,`value`,`balance`) VALUES (225487,'Deposito','2025-03-22 00:00:00.000000',600,700);
INSERT INTO `transaction` (`account_number`,`type_movement`,`transaction_date`,`value`,`balance`) VALUES (495878,'Deposito','2025-03-23 00:00:00.000000',150,150);
INSERT INTO `transaction` (`account_number`,`type_movement`,`transaction_date`,`value`,`balance`) VALUES (496825,'Retiro','2025-03-23 00:00:00.000000',-540,0);

