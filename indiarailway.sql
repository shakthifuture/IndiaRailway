-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.3-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table indianrailway.cancelled_schedule
CREATE TABLE IF NOT EXISTS `cancelled_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_CANCEDLLED_SCHEDULE` (`schedule_id`),
  CONSTRAINT `FK1_CANCEDLLED_SCHEDULE` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.cancelled_schedule: ~0 rows (approximately)
/*!40000 ALTER TABLE `cancelled_schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancelled_schedule` ENABLE KEYS */;

-- Dumping structure for table indianrailway.databasechangelog
CREATE TABLE IF NOT EXISTS `databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.databasechangelog: ~1 rows (approximately)
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
	('1.0.0 inital data', 'sathia', 'classpath:/db/changelog/init/_changelog-master.yaml', '2020-06-28 00:33:21', 1, 'EXECUTED', '8:3e96d605f8b7809a9da6b188bc324382', 'sqlFile', '', NULL, '3.8.9', NULL, NULL, '3284601740');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;

-- Dumping structure for table indianrailway.databasechangeloglock
CREATE TABLE IF NOT EXISTS `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.databasechangeloglock: ~1 rows (approximately)
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
	(1, b'0', NULL, NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;

-- Dumping structure for table indianrailway.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'USER'),
	(2, 'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table indianrailway.schedule
CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `train_id` int(11) NOT NULL,
  `source_station_id` int(11) NOT NULL,
  `destination_station_id` int(11) NOT NULL,
  `arrival_time` time NOT NULL,
  `depature_time` time NOT NULL,
  `sunday` bit(1) NOT NULL DEFAULT b'0',
  `monday` bit(1) NOT NULL DEFAULT b'0',
  `tuesday` bit(1) NOT NULL DEFAULT b'0',
  `wednesday` bit(1) NOT NULL DEFAULT b'0',
  `thursday` bit(1) NOT NULL DEFAULT b'0',
  `friday` bit(1) NOT NULL DEFAULT b'0',
  `saturday` bit(1) NOT NULL DEFAULT b'0',
  `is_suspended` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK1_SCHEDULE_TRAIN_ID` (`train_id`),
  KEY `FK2_SCHEDULE_SOURCE_STATION_ID` (`source_station_id`),
  KEY `FK3_SCHEDULE_DESIGN_STATION_ID` (`destination_station_id`),
  CONSTRAINT `FK1_SCHEDULE_TRAIN_ID` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`),
  CONSTRAINT `FK2_SCHEDULE_SOURCE_STATION_ID` FOREIGN KEY (`source_station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `FK3_SCHEDULE_DESIGN_STATION_ID` FOREIGN KEY (`destination_station_id`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.schedule: ~4 rows (approximately)
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` (`id`, `train_id`, `source_station_id`, `destination_station_id`, `arrival_time`, `depature_time`, `sunday`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`, `saturday`, `is_suspended`) VALUES
	(1, 1, 1, 2, '06:22:42', '18:22:45', b'0', b'1', b'0', b'0', b'0', b'0', b'1', b'0'),
	(2, 1, 2, 1, '07:22:42', '18:22:45', b'1', b'1', b'1', b'1', b'1', b'0', b'1', b'0'),
	(3, 7, 1, 2, '23:05:00', '23:07:00', b'0', b'1', b'1', b'1', b'1', b'1', b'0', b'0'),
	(4, 23, 1, 2, '14:36:00', '02:36:00', b'1', b'1', b'1', b'1', b'1', b'1', b'1', b'0');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

-- Dumping structure for table indianrailway.station
CREATE TABLE IF NOT EXISTS `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `station_code` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `station_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `longitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `latitude` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `station_code` (`station_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.station: ~6 rows (approximately)
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` (`id`, `station_code`, `station_name`, `longitude`, `latitude`) VALUES
	(1, 'MS', 'CHENNAI EGMORE', '14.077848', '80.260363'),
	(2, 'MSF', 'CHENNAI FORT', '13.08324', '80.282636'),
	(3, 'AAR', 'Adesar', '85.0005', '45.0000'),
	(4, 'AJP', 'Ajjampur', '78.000', '56.000'),
	(5, 'ANTU', 'Antu', '', ''),
	(6, 'TBM', 'Tambaram', '', '');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;

-- Dumping structure for table indianrailway.train
CREATE TABLE IF NOT EXISTS `train` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `train_number` int(11) NOT NULL,
  `train_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `train_number` (`train_number`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.train: ~15 rows (approximately)
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` (`id`, `train_number`, `train_name`) VALUES
	(1, 2841, 'SRC MAS SPL'),
	(2, 15120, 'MUV-RMM  EXPRESS'),
	(3, 588, 'Kovai Exp'),
	(5, 988, 'Pandiyan Exp'),
	(7, 58719, 'Abhanpur Rajim NG Passenger'),
	(9, 54703, 'Abohar Jodhpur Passenger'),
	(11, 68102, 'Adra Kharagpur Memu'),
	(13, 71306, 'Wadi Solapur DEMU'),
	(15, 16628, 'West Coast Express'),
	(17, 18516, 'Visakhapatnam Tatanagar Express'),
	(18, 76845, 'Vriddhachalam Tiruchchirappalli DEMU'),
	(19, 6055, 'Chennai Mangaluru Junction Summer Specialfare Special'),
	(20, 16042, 'Chennai Express'),
	(21, 43423, 'Chennai Moore Market - Arakkonam Junction Passenger'),
	(23, 11063, 'MS SALEM EXP');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;

-- Dumping structure for table indianrailway.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `first_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `activated` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6efs5vmce86ymf5q7lmvn2uuf` (`username`),
  UNIQUE KEY `email_address` (`email_address`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.users: ~4 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `first_name`, `last_name`, `email_address`, `activated`) VALUES
	(1, 'sathia', '$2a$10$VaL3PfeJmsV.vpru48Koo.49NBdS.w7nkqOrzfnLLZk/j3ktdTjMS', 'Sathia', 'M', 'shakthifuture@gmail.com', b'1'),
	(2, 'Pugazha', '$2a$10$SaBxcZ9s7FHZflAGlgBek.cd7iDKjbxdUYxL379WJstApKAW89Rxu', 'Pugazha', 'Pugazha', 'Pugazha', b'0'),
	(3, 'admin', '$2a$10$RM4tGuh0rsyF6LOWw2bkLOhfspiW4dWkWtjJu.ylFQXD/cTHThYaa', 'Admin', 'Admin', 'admin@gmail.com', b'1'),
	(4, 'test', '$2a$10$zUaGSf7wuiJzghZGNFQxsuZeEKhtnz5c6V95MbfH8WszmhTFnvOMO', 'test', 'test', 'test@test.com', b'1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table indianrailway.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK1_USER_ROLES_USER_ID` (`user_id`),
  KEY `FK2_USER_ROLES_ROLE_ID` (`role_id`),
  CONSTRAINT `FK1_USER_ROLES_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK2_USER_ROLES_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table indianrailway.users_roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
	(3, 2),
	(1, 1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
