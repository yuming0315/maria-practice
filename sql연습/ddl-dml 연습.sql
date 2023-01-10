-- member테이블 지우고 만들기
drop table member;
create table member(
	no int not null auto_increment,
    email varchar(100) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    
    primary key(no)
);

-- member 테이블 확인
desc member;

-- 주민번호 칼럼 추가
alter table member add column juminbunho char(13) not null;

-- 주민번호 칼럼 삭제
alter table member drop juminbunho;
desc member;

alter table member add column juminbunho char(13) not null after email;
desc member;

--                                          이름까지도 바꿀 수 있음 덮어씌우기 개념인듯?
alter table member change column department dept varchar(200) not null after juminbunho;
desc member;

alter table member add self_intro text;
alter table member drop juminbunho;
desc member;

-- insert
insert into member values(null,'gjdbal111@naver.com', password('1234'),'허유미','개발팀',null );
select * from member;

insert into member(no,email,name,dept,password) values(null,'gjdbal0315@naver.com', '허유미','개발팀',password('1234'));
select * from member;

-- update
update member set dept = '개발팀', password = password('1234') where email='gjdbal111@naver.com';

-- delete
delete from member where no = 1;
select * from member;

-- transaction
select @@autocommit;
set autocommit=0;
-- 자동으로 commit 안됨

insert into member(no,email,name,dept,password) values(null,'gjdbal5@naver.com', '허유미','개발팀',password('1234'));
select * from member;
commit;
