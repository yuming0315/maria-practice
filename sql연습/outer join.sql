-- Outer Join

desc emp;
insert into dept value(null,'총무');
insert into dept value(null,'개발');
insert into dept value(null,'영업');
insert into dept value(null,'기획');

select * from dept;

insert into emp values(null, '둘리',1);
insert into emp values(null, '마이콜',2);
insert into emp values(null, '또치',3);
insert into emp values(null, '길동',null);

select * from emp;

-- left join
select a.name 이름, ifnull(b.name,'없음') '부서'
from emp a left join dept b on a.dept_no = b.no;

-- right join
select ifnull(a.name, '없음') 사원, b.name '부서'
from emp a right join dept b on a.dept_no = b.no;


