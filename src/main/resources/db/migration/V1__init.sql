DROP TABLE IF EXISTS `testcase`;
create table testcase(
	id int not null auto_increment,
	case_name varchar(200) not null default '',
	script text,
	typecase int not null default 0,
	remote varchar(200) not null default '',
	type_browser int not null default 2, 
	primary key(id)
) ENGINE=INNODB;


create table result2testcase(
	id int not null auto_increment,
	test_case_id int not null default 0,
	reff_id  varchar(200) not null default '',
	primary key(id)
)


create table resulttestcase(
	id int not null auto_increment,
	reff_id varchar(200) not null default '',
	photo_id varchar(200) not null default '',
	description text,
	status varchar(200) not null default '',
	primary key(id)
);

/* 
 * type_browser = 1  ie
 * type_browser = 2  chrome
 * type_browser = 3  firefox
 *
 * 
 * */






insert into testcase(case_name,script,typecase,remote,type_browser)
values ('helloword','
System.out.println(driver);
driver.manage().timeouts().implicitlyWait(30,
	  TimeUnit.SECONDS);
driver.get("http://www.google.com");
driver.quit();
',0,'http://localhost:4545/wd/hub',1);