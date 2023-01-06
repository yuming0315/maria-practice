select version(),current_date,now()
from dual;
-- 주석 사칙연산 가능
select sin(pi() / 4),1+2+3+4/5 as 사칙연산 from dual;

-- 대소문자 구분 안함, 칼럼이름도 대소문자 구분안함!
select version(), CURRENT_DATE, NOW() FROM DUAL;

show databases;

use test;
show tables;

-- table 생성: DML
create table pet (
	name varchar(100),
	owner varchar(20),
	species varchar(20),
	gender char(1),
	birth date,
	death date
);

-- schema 확인
desc pet;
describe pet;

-- table 삭제
drop table pet;
show tables;

-- insert: DML(C)
insert into pet values('성탄이','안대혁','dog','m','2007-12-25',null);

select * from pet;

-- delete: DML(D)
delete from pet where name = '성타니';

-- update: DML(U)
update pet 
set name='성타니' 
where name="성탄이";

-- load data
load data local infile 'd:\pet.txt' into table pet;

-- select
select * from pet where name = 'Bowser';




