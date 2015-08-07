USE mpd;

DROP TABLE IF EXISTS `mpd`.`parkingmovinginfractions`;
CREATE TABLE  `mpd`.`parkingmovinginfractions` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'AutoIncrement',
  `Code` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT 'Parking Or Traffic Code',
  `Description` text CHARACTER SET latin1 NOT NULL,
  `ShortDescription` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT 'Short Description of Violation',
  `Fine` decimal(7,2) unsigned NOT NULL COMMENT 'Dollar Fine',
  `Display` tinyint(1) NOT NULL COMMENT 'Should this row be displayed',
  PRIMARY KEY (`ID`,`Code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5801 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Metropolitan Police Departmen';


CREATE TABLE  RequestLog (
	Id int(10) unsigned NOT NULL AUTO_INCREMENT,
	IpAddress varchar(45) NOT NULL,
	RequestURI text NOT NULL,
	AuthUser varchar(45) default 'not authenticated',
	DateTimeOfRequest datetime NOT NULL,
	ParameterNames text,
	referer text,
	PRIMARY KEY  (Id)
);

