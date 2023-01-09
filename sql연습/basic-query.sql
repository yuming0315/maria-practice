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

update pet set death = null where name != 'Bowser';

select name, species, gender
from pet
where species = 'dog' and gender = 'f';


select name, species, gender
from pet
where species in('snake','bird');

-- 아리스타처럼 꼭 명시해야하는 것은
-- orderby는 필수 . 데이터를 삭제하고 입력하고를 반복하다보면 순서가 꼬임 ( 2번자리에 5번째 저장됨)

select name, birth
from pet
order by birth desc;

select name, birth,death
from pet
where death is not null;

select name
from pet
where name like 'b%';

select name
from pet
where name like '%fy';


select name
from pet
where name like '%w%';
-- %는 문자수에 제한 x  _는 한문자


select name
from pet
where name like '_____';
-- b로 시작하는 5글자 'b____'

select count(*)
from pet;

-- 널이 아닌 칼럼의 수
select count(death)
from pet;
-- 위의 문장보다 아래처럼
select count(*)
from pet
where death is null;





