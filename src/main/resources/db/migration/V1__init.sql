DROP TABLE IF EXISTS `testcase`;
create table testcase(
	id int not null auto_increment,
	case_name varchar(200) not null default '',
	script text,
	typecase int not null default 0,
	remote_ip varchar(200) not null default '',
	remote_port varchar(200) not null default '',
	primary key(id)
) ENGINE=INNODB;

insert into testcase(case_name,script,typecase,remote_ip,remote_port)
values ('helloword','
System.out.println(driver);
driver.manage().timeouts().implicitlyWait(30,
	  TimeUnit.SECONDS);
driver.get("http://www.google.com");
driver.quit();
',0,'127.0.0.1','80');