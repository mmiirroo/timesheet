CREATE TABLE `employee` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`department` VARCHAR(255) NULL DEFAULT NULL,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
CHARSET='utf8'
ENGINE=InnoDB;

CREATE TABLE `account` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `age` int NOT NULL DEFAULT 0,
    `sex` VARCHAR(4) NOT NULL DEFAULT '',
    `name` VARCHAR(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
)
CHARSET='utf8'
ENGINE=InnoDB;


CREATE TABLE `manager` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
CHARSET='utf8'
ENGINE=InnoDB;

CREATE TABLE `task` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`completed` TINYINT(1) NOT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`manager_id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_8jytulgtmbll9te71yrajubgi` (`manager_id`),
	CONSTRAINT `FK_8jytulgtmbll9te71yrajubgi` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
)
CHARSET='utf8'
ENGINE=InnoDB;

CREATE TABLE `task_employee` (
	`task_id` BIGINT(20) NOT NULL,
	`employee_id` BIGINT(20) NOT NULL,
	INDEX `FK_o7lqwgk4mh65ncqjdijcm6n4t` (`employee_id`),
	INDEX `FK_k7heera834i68f92pt51s4c2h` (`task_id`),
	CONSTRAINT `FK_k7heera834i68f92pt51s4c2h` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
	CONSTRAINT `FK_o7lqwgk4mh65ncqjdijcm6n4t` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
)
CHARSET='utf8'
ENGINE=InnoDB;

CREATE TABLE `timesheet` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`hours` INT(11) NULL DEFAULT NULL,
	`task_id` BIGINT(20) NULL DEFAULT NULL,
	`employee_Id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_2610xxgc31rkj98ln6kjwr7pd` (`task_id`),
	INDEX `FK_aeeb918vogp1wmkuu63ong13t` (`employee_Id`),
	CONSTRAINT `FK_2610xxgc31rkj98ln6kjwr7pd` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
	CONSTRAINT `FK_aeeb918vogp1wmkuu63ong13t` FOREIGN KEY (`employee_Id`) REFERENCES `employee` (`id`)
)
CHARSET='utf8'
ENGINE=InnoDB;
