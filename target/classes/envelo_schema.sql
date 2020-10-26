CREATE TABLE IF NOT EXISTS `rates` (
	`rate_id` INT(11) NOT NULL AUTO_INCREMENT,
	`currency` varchar(100) NOT NULL,
	`code` varchar(3) NOT NULL,
	`bid` FLOAT NOT NULL,
    `ask` FLOAT NOT NULL,
	`trading_date` varchar(10) NOT NULL,
	`effective_date` varchar(10) NOT NULL,
	PRIMARY KEY (`rate_id`)
);

CREATE TABLE IF NOT EXISTS `currencies` (
	`currency_id` INT(11) NOT NULL AUTO_INCREMENT,
	`currency` varchar(50) NOT NULL,
	`code` varchar(3) NOT NULL,
	PRIMARY KEY (`currency_id`)
);

CREATE TABLE IF NOT EXISTS `actions` (
	`action_id` INT(11) NOT NULL AUTO_INCREMENT,
	`action_name` varchar(50) DEFAULT 'null',
	`registration_time` varchar(50) NOT NULL,
	PRIMARY KEY (`action_id`)
);

INSERT INTO `currencies` VALUES ( 1, 'dolar amerykański', 'USD');
INSERT INTO `currencies` VALUES ( 2, 'euro', 'EUR');
INSERT INTO `currencies` VALUES ( 3, 'frank szwajcarski', 'CHF');
INSERT INTO `currencies` VALUES ( 4, 'funt szterling', 'GBP');
INSERT INTO `currencies` VALUES ( 5, 'forint (Węgry)', 'HUF');
