-- inner join

-- 예제1) 현재, 근무하고 있는 직원의 이름과 직책을 모두 출력하세요.
select concat(a.first_name ,' ', a.last_name) '이름', b.title
from employees a,titles b
where a.emp_no = b.emp_no and b.to_date >= sysdate();

select concat(a.first_name ,' ', a.last_name) '이름', b.title
from employees a join titles b on a.emp_no = b.emp_no
where b.to_date >= sysdate();

-- 예제2) 현재, 근무하고 있는 직원의 이름과 직책을 출력하되 여성 엔지니어(Engineer)만 출력하세요.
select concat(a.first_name ,' ', a.last_name) '이름', b.title, a.gender
from employees a join titles b on a.emp_no = b.emp_no
where a.gender = "F" and b.title = 'Engineer';

-- ANSI/ISO SQL1999 JOIN 표준 문법

-- 1) natural join
-- 조인 대상이 되는 두 테이블에 이름이 같은 공통 컬럼이 있으면 조인 조건을 
-- 명시하지 않고 암묵적으로 조인이 된다.
select a.first_name, b.title
from employees a natural join titles b
where b.to_date >= sysdate();

-- 2) join ~ using
-- natural join의 문제점
select *
from salaries a natural join titles b
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01';

select count(*)
from salaries a join titles b using(emp_no)
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01';
-- 위의 결과와 다르게 나옴

-- 3) join ~ on 중요
-- 예제) 현재, 직책별 평균 연봉을 큰 순서대로 출력하세요.
select b.title, avg(a.salary) '평균연봉'
from salaries a join titles b on a.emp_no = b.emp_no
group by b.title
order by 평균연봉 desc;
-- 위의 것 정렬 제대로 안 됨 => order by는 컬럼명으로 '평균연봉'으로 하면 정렬 안 됨
select b.title, avg(a.salary) '평균연봉'
from salaries a join titles b on a.emp_no = b.emp_no
group by b.title
order by avg(a.salary) desc;
