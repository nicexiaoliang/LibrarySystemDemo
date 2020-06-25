CREATE DATABASE mylibrary

CREATE TABLE manager(
manager_id INT(11) NOT NULL AUTO_INCREMENT,
pass_wd VARCHAR(45) NOT NULL COLLATE utf8_unicode_ci,
mname VARCHAR(45) NOT NULL COLLATE utf8_unicode_ci,
PRIMARY KEY(manager_id),
UNIQUE KEY mid_index (manager_id)
)

CREATE TABLE booklist(
 `ISBN` VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL,
  `bname` VARCHAR(45) COLLATE utf8_unicode_ci NOT NULL,
  `publisher` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `writer` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ptime` DATE DEFAULT NULL,
  `number` INT(11) NOT NULL DEFAULT '0',
  `operator` INT(11) NOT NULL,
  `is_popular` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ISBN`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  KEY `op_idx` (`operator`),
  CONSTRAINT `op` FOREIGN KEY (`operator`) REFERENCES `manager` (`manager_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)


CREATE TABLE book(
book_id INT(11) NOT NULL AUTO_INCREMENT,
ISBN VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL,
location VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
state INT(11) NOT NULL,
operator INT(11) NOT NULL,
  PRIMARY KEY (`book_id`),
   UNIQUE KEY `BID_UNIQUE` (`book_id`),
  INDEX `op2_idx` (`operator`),
  INDEX `bo_idx` (`ISBN`),
  CONSTRAINT `bo` FOREIGN KEY (`ISBN`) REFERENCES `booklist` (`ISBN`) ON DELETE CASCADE ON UPDATE NO ACTION
)



CREATE TABLE reader(
 `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `pass_wd` VARCHAR(45) COLLATE utf8_unicode_ci NOT NULL,
  `rname` VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `phone` VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` VARCHAR(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `RID_UNIQUE` (`user_id`)
) 

CREATE TABLE `borrow` (
  `borrow_id` INT(11) NOT NULL AUTO_INCREMENT,
  `book_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `btime` DATETIME NOT NULL,
  `deadline` DATETIME NOT NULL,
  `rtime` DATETIME DEFAULT NULL,
  `operator` INT(11) DEFAULT NULL,
  PRIMARY KEY (`borrow_id`),
  UNIQUE KEY `BWID_UNIQUE` (`borrow_id`),
  KEY `boo_idx` (`book_id`),
  KEY `re_idx` (`user_id`),
  KEY `op3_idx` (`operator`),
  CONSTRAINT `boo` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `op3` FOREIGN KEY (`operator`) REFERENCES `manager` (`manager_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `re` FOREIGN KEY (`user_id`) REFERENCES `reader` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE reservation(
  `reservation_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `book_id` INT(11) NOT NULL,
  `deadline` DATETIME DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  UNIQUE KEY `REID_UNIQUE` (`reservation_id`),
  UNIQUE KEY `BID_UNIQUE` (`book_id`),
  KEY `re2_idx` (`user_id`),
  KEY `book_idx` (`book_id`),
  CONSTRAINT `book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `re2` FOREIGN KEY (`user_id`) REFERENCES `reader` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)


CREATE TABLE reservation_arrived(
reservation_arrived_id INT(11) NOT NULL AUTO_INCREMENT,
reservation_id INT(11) NOT NULL,
PRIMARY KEY(reservation_id),
KEY res_id_fk_idx (reservation_arrived_id),
 CONSTRAINT res_id_fk FOREIGN KEY (reservation_id) REFERENCES `reservation` (reservation_id) ON DELETE CASCADE ON UPDATE NO ACTION
)



#添加数据,管理员
INSERT INTO manager (pass_wd,mname) VALUES ('123456','admit')


#添加数据,用户
INSERT INTO reader (user_name,pass_wd,rname,phone,email) VALUES ('lucy','654321','lucy','13627788494','574459751@qq.com')


#添加数据,用户
INSERT INTO booklist (ISBN,bname,publisher,writer,ptime,number,operator,is_popular)
VALUES ('A785699857','红楼梦','曹雪芹','曹雪芹','1066-01-01 00:00:00',5,3,2) 


#添加数据，book
INSERT INTO book(ISBN,location,state,operator)
VALUES ('A785699857','流通室',3,2)



DELIMITER //
CREATE PROCEDURE addReservation(IN isbm VARCHAR(20),IN user_i INT(11),OUT res INT(3))
BEGIN
DECLARE param1 INT;
DECLARE number INT;
DECLARE book_i INT;
SELECT COUNT(*) INTO param1 FROM reservation WHERE user_id=user_i;
SELECT number INTO number FROM booklist WHERE BINARY ISBN=isbm;
SELECT book_id INTO book_i FROM book WHERE BINARY ISBN=isbm;
INSERT INTO reservation (user_id,book_id,deadline) VALUES(user_i,book_i,'2020-04-16');
SELECT 1 INTO res;
END//


SET @res=0;
CALL addReservation('A785699857',2,@res);
SELECT @res;


DELIMITER //
CREATE PROCEDURE addRes(IN user_i INT(20),IN isb VARCHAR(20),OUT res INT)
BEGIN
DECLARE rescount INT;
DECLARE stat INT;
SELECT COUNT(*) INTO rescount FROM reservation WHERE user_id=user_i;
SELECT state INTO stat FROM book WHERE BINARY isbn=isb;
IF rescount<10 AND (stat=0 OR stat=2) THEN
CALL addReservation(isb,user_i,res);
END IF;
END//

SET @res=0;   CALL addRes(4,'A785699856',@res);SELECT @res;



#创建添加图书存储过程
DELIMITER //
CREATE PROCEDURE addBook(IN isbn1 VARCHAR(30),IN bname1 VARCHAR(20),IN publisher1 VARCHAR(20),IN
writer1 VARCHAR(20),IN ptime1 DATETIME,IN number1 INT(20),IN operator1 INT(20),IN location1 VARCHAR(20),IN state1 INT(20))
BEGIN 
INSERT INTO booklist VALUES (isbn1,bname1,publisher1,writer1,ptime1,number1,operator1,1);
INSERT INTO book (ISBN,location,state,operator) VALUES (isbn1,location1,state1,operator1);
END//

CALL addBook('7895266985','java从入门到精通','清华大学出版社','明日科技','2008-8-7',10,3,'阅览室A',2);



#创建视图

CREATE VIEW reservation_detail AS 
SELECT reservation_id,bname,book.book_Id,deadline,reader.user_Name FROM reservation JOIN
reader ON reservation.`user_id`=reader.`user_id` JOIN book ON book.`book_id`=reservation.`book_id` JOIN
booklist ON book.`ISBN`=booklist.`ISBN`;




DELIMITER //
CREATE PROCEDURE insertBorrow(IN rid INT(20),IN operator1 INT(20))
BEGIN
DECLARE b_id INT(20); 
DECLARE u_id INT(20);
DECLARE d_line DATETIME;
SELECT book_id,user_id,deadline INTO b_id,u_id,d_line FROM reservation WHERE reservation_id=rid;
INSERT INTO borrow (book_id,user_id,btime,deadline,rtime,operator) VALUES(b_id,u_id,'2015-12-13',d_line,'2015-12-13',2);
DELETE FROM reservation WHERE reservation_id=rid;
END//

CALL insertBorrow(43,2)



CREATE VIEW borrow_detail AS
SELECT borrow_id,bname,user_name,btime,deadline,rtime FROM borrow
JOIN reader ON borrow.`user_id`=reader.`user_id` JOIN book ON 
book.`book_id`= borrow.book_id JOIN booklist ON book.`ISBN`=booklist.`ISBN`


DELIMITER //
CREATE PROCEDURE returnBook(IN Borid INT(20))
BEGIN
DECLARE cur_time DATETIME;
SELECT DATE_FORMAT(NOW(),'%Y-%m-%d') INTO cur_time;
UPDATE borrow SET rtime=cur_time WHERE borrow_id=Borid;
END//

CALL returnBook(94)










