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
select b.title, avg(a.salary) 평균연봉
from salaries a join titles b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by b.title
order by 평균연봉 desc;
-- 위의 것 정렬 제대로 안 됨 => order by는 컬럼명으로 '평균연봉'으로 하면 정렬 안 됨
select b.title, avg(a.salary) '평균연봉'
from salaries a join titles b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by b.title
order by avg(a.salary) desc;



-- 실습문제 1
-- 현재, 직원별 근무 부서를 사번, 직원 이름, 부서명으로 출력해보세요.
-- 사번, 직원 이름, 부서명
select a.emp_no 사번,a.first_name '직원 이름',c.dept_name 부서명
from employees a join dept_emp b join departments c
on a.emp_no = b.emp_no and b.dept_no = c.dept_no
where b.to_date >= sysdate();

-- 실습문제 2
-- 현재, 지급되고 있는 급여를 출력 해보세요.
-- 사번, 이름, 급여 순으로 출력
select a.emp_no 사번, a.first_name 이름, b.salary 급여
from employees a join salaries b on a.emp_no = b.emp_no
where b.to_date >= sysdate();

-- 실습문제 3
-- 현재, 직책별 평균연봉, 직원수를 구하되 직책별 직원수가 100명 이상인 직책만 출력하세요.
-- 직책, 평균연봉, 직원수 순으로 출력
select a.title 직책, avg(b.salary) 평균연봉, count(a.emp_no) 직원수
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.title having count(a.emp_no) >= 100;
-- 매니저 제외로 9명 빠짐

-- 실습문제 4
-- 현재, 부서별로 직책이 engineer인 직원들에 대해서만 평균 급여를 구하세요.
-- 부서이름, 평균급여 순으로 출력하세요.
select c.dept_name 부서이름, avg(d.salary) 평균급여, count(a.emp_no)
from titles a join dept_emp b join departments c join salaries d
on a.emp_no = b.emp_no and b.dept_no = c.dept_no and a.emp_no = d.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate() and d.to_date >= sysdate() and a.title = 'engineer'
group by c.dept_name;
