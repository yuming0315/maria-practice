-- select 연습
select * from departments;
select count(*) from departments;

select dept_no,dept_name from departments;

-- as (alias, 생략 가능)
-- employees 직원 이름, 성별, 입사일을 출력
select concat(first_name," ",last_name) as 이름, gender as 성별,hire_date as 입사일
from employees
order by hire_date, 이름;

-- distinct
select distinct(title) from titles;

-- where
-- 예제1. employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일 출력
select concat(first_name," ",last_name) '이름',gender '성별', hire_date '입사일'
from employees
where hire_date < '1991-01-01'
order by hire_date;

-- 예제2. employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select concat(first_name," ",last_name) '이름', hire_date '입사일'
from employees
where hire_date < '1989-01-01' and gender = 'F'
order by hire_date;

-- 예제3. in 연산자 : dept_emp 테이블에서 부서번호가 d005 이거나 d009에 속한 사원의 사번, 부서번호 출력
select emp_no '사번',dept_no '부서번호'
from dept_emp
where dept_no in ('d005','d009');

-- 예제4. Like 검색: employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력
select concat(first_name," ",last_name) '이름', hire_date '입사일'
from employees
where year(hire_date) = 1989;

select concat(first_name," ",last_name) '이름', hire_date '입사일'
from employees
where hire_date between '1989-01-01' and '1989-12-31';

select concat(first_name," ",last_name) '이름', hire_date '입사일'
from employees
where hire_date like '1989-%';

-- order by
-- 예제1: employees 테이블에서 직원의 전체이름, 성별, 입사일을 입사일순으로 출력
select  concat(first_name," ",last_name) '이름', gender '성별',hire_date '입사일'
from employees
order by hire_date;

-- 예제2: salaries 테이블에서 2001년 월급을 가장 높은순으로 사번, 월급순으로 출력
select emp_no '사번', salary '월급'
from salaries
where from_date like '2001%' or to_date like '2001%'
order by salary desc;

-- 예제3: 남자 직원의 이름, 성별, 입사일을 입사일순(선임순)으로 출력하세요.
select first_name '이름', gender '성별', hire_date '입사일'
from employees
where gender = 'M'
order by '입사일';

-- 예제4: 직원들의 사번 월급을 사번(asc), 월급은(desc
select emp_no,salary, from_date,to_date
from salaries
order by emp_no , salary desc;




