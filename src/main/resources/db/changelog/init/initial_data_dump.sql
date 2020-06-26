CREATE TABLE `users` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`password` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	`username` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`first_name` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`last_name` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`email_address` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`activated` BIT(1) NOT NULL DEFAULT b'0',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `UK_6efs5vmce86ymf5q7lmvn2uuf` (`username`),
	UNIQUE INDEX `email_address` (`email_address`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;

CREATE TABLE `train` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`train_number` INT(11) NOT NULL,
	`train_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `train_number` (`train_number`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;

CREATE TABLE `station` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`station_code` VARCHAR(5) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`station_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`longitude` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	`latitude` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `station_code` (`station_code`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;
CREATE TABLE `schedule` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`train_id` INT(11) NOT NULL,
	`source_station_id` INT(11) NOT NULL,
	`desig_station_id` INT(11) NOT NULL,
	`arrival_time` TIME NOT NULL,
	`depature_time` TIME NOT NULL,
	`sunday` BIT(1) NOT NULL DEFAULT b'0',
	`monday` BIT(1) NOT NULL DEFAULT b'0',
	`tuesday` BIT(1) NOT NULL DEFAULT b'0',
	`wednesday` BIT(1) NOT NULL DEFAULT b'0',
	`thursday` BIT(1) NOT NULL DEFAULT b'0',
	`friday` BIT(1) NOT NULL DEFAULT b'0',
	`saturday` BIT(1) NOT NULL DEFAULT b'0',
	`is_suspended` BIT(1) NOT NULL DEFAULT b'0',
	PRIMARY KEY (`id`),
	INDEX `FK1_SCHEDULE_TRAIN_ID` (`train_id`),
	INDEX `FK2_SCHEDULE_SOURCE_STATION_ID` (`source_station_id`),
	INDEX `FK3_SCHEDULE_DESIGN_STATION_ID` (`desig_station_id`),
	CONSTRAINT `FK1_SCHEDULE_TRAIN_ID` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`),
	CONSTRAINT `FK2_SCHEDULE_SOURCE_STATION_ID` FOREIGN KEY (`source_station_id`) REFERENCES `station` (`id`),
	CONSTRAINT `FK3_SCHEDULE_DESIGN_STATION_ID` FOREIGN KEY (`desig_station_id`) REFERENCES `station` (`id`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;

CREATE TABLE `cancelled_schedule` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`schedule_id` INT(11) NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`reason` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`),
	INDEX `FK1_CANCEDLLED_SCHEDULE` (`schedule_id`),
	CONSTRAINT `FK1_CANCEDLLED_SCHEDULE` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;

INSERT INTO `users` (`password`, `username`, `first_name`, `last_name`, `email_address`, `activated`) VALUES ('$2a$10$VaL3PfeJmsV.vpru48Koo.49NBdS.w7nkqOrzfnLLZk/j3ktdTjMS', 'sathia', 'Sathia', 'M', 'shakthifuture@gmail.com', b'1');
INSERT INTO `users` (`password`, `username`, `first_name`, `last_name`, `email_address`, `activated`) VALUES ('$2a$10$SaBxcZ9s7FHZflAGlgBek.cd7iDKjbxdUYxL379WJstApKAW89Rxu', 'Pugazha', 'Pugazha', 'Pugazha', 'Pugazha', b'0');

INSERT INTO `train` (`train_number`, `train_name`) VALUES (2841, 'SRC MAS SPL');
INSERT INTO `train` (`train_number`, `train_name`) VALUES (15120, 'MUV-RMM  EXPRESS');

INSERT INTO `station` (`station_code`, `station_name`, `longitude`, `latitude`) VALUES ('MS', 'CHENNAI EGMORE', '14.077848', '80.260363');
INSERT INTO `station` (`station_code`, `station_name`, `longitude`, `latitude`) VALUES ('MSF', 'CHENNAI FORT', '13.08324', '80.282636');

INSERT INTO `schedule` (`train_id`, `source_station_id`, `desig_station_id`, `arrival_time`, `depature_time`, `sunday`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `is_suspended`) VALUES (1, 1, 2, '06:22:42', '18:22:45', b'0', b'1', b'0', b'0', b'0', b'0', b'1', b'0');
INSERT INTO `schedule` (`train_id`, `source_station_id`, `desig_station_id`, `arrival_time`, `depature_time`, `sunday`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `is_suspended`) VALUES (1, 2, 1, '07:22:42', '18:22:45', b'1', b'1', b'1', b'1', b'1', b'0', b'1', b'0');


