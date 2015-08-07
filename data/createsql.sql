USE mpd;

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

