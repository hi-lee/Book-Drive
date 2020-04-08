show tables;
desc member;
desc bookInfo;
desc rentalIdv;
desc rental;
desc board;
desc cart;
desc board;
select * from bookInfo  where bookNum = 15508;
select * from rentalIdv 
where memIndex = 12

select * from library;
select * from member;
select * from bookInfo;
select * from cart;
select count(*) from cart;

update bookInfo set bookState = 1 where bookNum = 15508
update rentalIdv set renIdvDelFlag = 1 where bookNum = 33376

start transaction;
update rentalIdv set renIdvDelFlag = 1 where renIdvNum = 60
rollback;

select * from rentalIdv left join bookInfo on rentalIdv.bookNum = bookInfo.bookNum where rentalIdv.bookNum = 2839

select * from rentalIdv where bookNum = 51575 and renFlag = 'outrev' and renIdvDelFlag = 0

update rentalIdv set renIdvDelFlag = 0 where bookNum = 51615 and renFlag = 'outrev'

delete from cart where bookNum = 51616

insert into rentalIdv (renIdvNum, memIndex, bookNum, libCode, renIdvDelFlag, renFlag,  renBrwDate, renBrwInvDate)
values (0, 12, 51567, 'LIB002', 0, 'brw', now(), now()+INTERVAL 14 DAY);


select * from bookInfo order by rand() limit 200;


update bookInfo set bookState = 3 where bookNum in (select * from (select a.bookNum from bookInfo as a order by rand() limit 200) a);
select count(*) from bookInfo where bookState = 3;

update rentalIdv set renIdvDelFlag=1 where renIdvNum = 74

insert into rentalIdv (renIdvNum, memIndex, bookNum, libCode, renIdvDelFlag, renFlag, renBrwDate, renBrwInvDate)
values (0, 7, 51575, 'LIB002', 0, 'outbrw', now(), (now()+INTERVAL 14 DAY))

update member set memCarNum = '01구3333' where memIndex = 3;

select * from cart left join bookInfo on cart.bookNum = bookInfo.bookNum where memIndex = 7
alter table cart modify cartNum int(11) not null auto_increment;

delete from cart where memIndex = 7 and cartNum = 6


insert into member values(7, 'java', 1111, '테스트', 19900101, 41516, '대구광역시 북구 산격2동 검단로 97', 100, 01000000000, 'test@test.com', '01구0000', 'N')

rollback;
select * from bookInfo where bookNum = 51653

select * from cart left join bookInfo on cart.bookNum = bookInfo.bookNum where memIndex = 7;

update rentalIdv set renFlag = 'rev' where renIdvNum = 49
update rentalIdv set renReturnDate = now(), renIdvDelFlag = 1 where bookNum = 2


insert into rentalIdv (renIdvNum, renNum, memIndex, bookNum, libCode, renFlag, renBrwDate, renBrwInvDate) values(0, 5, 10, 51615, 'LIB002', 'brw', now(), (now()+INTERVAL 7 DAY));
select * from rentalIdv join bookInfo as a on a.bookNum = rentalIdv.bookNum where memIndex = 7;

insert into rentalIdv (renIdvNum, memIndex, bookNum, libCode, renIdvDelFlag, renFlag,  renBrwDate, renBrwInvDate)

select * from rentalIdv left join bookInfo on rentalIdv.bookNum = bookInfo.bookNum where memIndex = 7;
select * from rentalIdv left join bookInfo on rentalIdv.bookNum = bookInfo.bookNum where memIndex = 7 and renIdvDelFlag = 0

delete from rentalIdv where renIdvNum = 55

select memId, memName from member where memName = '김자바' and memBirth = '19990101'

alter table cart change keepDelFlag cartDelFlag varchar(5)
drop table cart;
create table cart (cartNum int(11) not null auto_increment primary key, bookNum int(11), libCode varchar(10), memIndex varchar(5), cartDelFlag varchar(5))

select count(*) from board where boardSubject like '%'

alter table rentalIdv modify renFlag varchar(10);

select * from cart left join bookInfo on cart.bookNum = bookInfo.bookNum where memIndex = 12
