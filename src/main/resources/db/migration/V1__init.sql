DROP TABLE IF EXISTS `testcase`;
create table testcase(
	id int not null auto_increment,
	case_name varchar(200) not null default '',
	typecase int not null default 0,
	remote varchar(200) not null default '',
	type_browser int not null default 2, 
	script_id int not null default 0,
	primary key(id)
) ENGINE=INNODB;

create table browser_driver(
	browser_id int not null auto_increment,
	browser_name varchar(200),
	browser_type int not null default 0,
	remote_url varchar(200),
	primary key(browser_id)

);
create table result2testcase(
	id int not null auto_increment,
	test_case_id int not null default 0,
	reff_id  varchar(200) not null default '',
	primary key(id)
)ENGINE=INNODB;


create table resulttestcase(
	id int not null auto_increment,
	reff_id varchar(200) not null default '',
  case_name varchar(200) not null default '',
	photo_id varchar(200) not null default '',
	description text,
	pass int not null default 0,
	status varchar(200) not null default '',
  prefix varchar(200) not null default '',
   created_on DATETIME NOT NULL DEFAULT NOW(),
	primary key(id)
)ENGINE=INNODB;

create table scripts_testcase(
		script_id int not null auto_increment,
		script_name varchar(200) not null default '',
		scripts text,
		description text not null,
		primary key(script_id)
)ENGINE=INNODB;

create table parameters(
	parameter_id int not null auto_increment,
	parameter_name varchar(200) not null default '',
	parameter_type int not null default 0, /** 0 normal, 1 : random, 2 date dd-mm-yyyy **/
	parameter_value varchar(200) not null default '',
    param_cat_id int not null default 0,
	primary key(parameter_id)
	
);

create table param_cat_id(
  cat_id int not null auto_increment,
  cat_name varchar(200) not null default '',
  primary key(cat_id)
);
/* 
 * type_browser = 1  ie
 * type_browser = 2  chrome
 * type_browser = 3  firefox
 *
 * 
 * */




insert into scripts_testcase(
	scripts_text,
	description_text
)values('
System.out.println(driver);
driver.manage().timeouts().implicitlyWait(30,
	  TimeUnit.SECONDS);
driver.get("http://www.google.com");
driver.quit();','')

insert into browser_driver(browser_type,remote_url)values()

insert into testcase(case_name,script,typecase,remote,type_browser)
values ('helloword','
System.out.println(driver);
driver.manage().timeouts().implicitlyWait(30,
	  TimeUnit.SECONDS);
driver.get("http://www.google.com");
driver.quit();
',0,'http://localhost:4545/wd/hub',1);