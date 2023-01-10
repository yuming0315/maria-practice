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

