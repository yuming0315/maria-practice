-- subquery

--
-- 1) select절, insert values(....)의 서브쿼리
--
-- 2) from절
select now() as n, sysdate() as s, 3+1 as r from dual;

select a.n, a.r
from ( select now() as n, sysdate() as s, 3+1 as r from dual) a;

--
-- 3) where절의 서브쿼리

-- 예제) 현재, Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번, 이름을 출력 하세요.
select a.emp_no 사번,a.first_name 이름
from employees a join dept_emp b on a.emp_no = b.emp_no
where b.dept_no in (
select b.dept_no
from employees a join dept_emp b on a.emp_no = b.emp_no
where b.to_date >= sysdate() and concat(a.first_name,' ',a.last_name) = 'Fai Bale'
);

-- 3-1) 단일행 연산자 = > < >= <= <> !=
-- 실습문제1:
-- 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력하세요
select a.first_name 이름, b.salary 급여
from employees a join salaries b on a.emp_no = b.emp_no
where b.to_date >= sysdate() and b.salary < (
select avg(salary)
from salaries
where to_date >= sysdate()
);

select avg(salary)
from salaries
where to_date >= sysdate();

-- 실습문제2:
-- 현재, 가장 적은 평균 급여의 직책과 그 평균급여를 출력해보세요
select a.title 직책,avg(b.salary) 평균급여
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.title
order by 평균급여
limit 0,1;

select a.직책 직책, min(a.평균급여) 평균급여
from 
(
select a.title 직책,avg(b.salary) 평균급여
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.title 
) a;

select a.title 직책, avg(b.salary) 평균급여
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by 직책 having 평균급여 = (
select min(a.평균급여) 평균급여
from 
(
select a.title 직책,avg(b.salary) 평균급여
from titles a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.title 
) a
);

-- 3-2) 복수행 연산자 in, not in, 비교연산자 any, 비교연산자 all

-- any 사용법
-- 1. =any: in
-- 2. >any, >=any: 최소값
-- 3. <any, <=any: 최대값
-- 4. <>any, !=any: not in

-- all 사용법
-- 1. =all: (x)
-- 2. >all, >=all: 최대값
-- 3. <all, <=all: 최소값
-- 4. <>all, !=all

-- 실습문제3
-- 현재 급여가 50000 이상인 직원의 이름과 급여를 출력하세요.
-- 둘리 6000
-- 또치 8000

-- sol1)
select a.first_name, b.salary
from employees a join salaries b on a.emp_no = b.emp_no
where b.to_date >= sysdate() and b.salary > 50000
order by b.salary;

-- sol2) subquery
select emp_no, salary
from salaries
where to_date >= sysdate() and salary > 50000;

select a.first_name, b.salary
from employees a join salaries b on a.emp_no = b.emp_no
where b.to_date >= sysdate() and (a.emp_no, b.salary) in   -- any도 가능
(
select emp_no, salary
from salaries
where to_date >= sysdate() and salary > 50000
)order by b.salary;

-- 실습문제4: 현재, 각 부서별로 최고 급여를 받고 있는 직원의 이름과 월급을 출력하세요
-- 총무 둘리 4000
-- 영업 또치 5000

-- sol1: where절 subquery (in)
select a.dept_no,max(b.salary)
from dept_emp a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.dept_no;

-- sol2: from절 subquery & join
select a.dept_name, c.first_name, d.salary
from departments a join dept_emp b on a.dept_no = b.dept_no
join employees c on b.emp_no = c.emp_no
join salaries d on c.emp_no = d.emp_no
where b.to_date >= sysdate() and d.to_date >= sysdate() and (a.dept_no,d.salary) in 
(
select a.dept_no,max(b.salary)
from dept_emp a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.dept_no
);

select a.dept_name, c.first_name, d.salary
from departments a join dept_emp b on a.dept_no = b.dept_no
join employees c on b.emp_no = c.emp_no
join salaries d on c.emp_no = d.emp_no
join 
(select a.dept_no 부서,max(b.salary) 최대연봉
from dept_emp a join salaries b on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.dept_no) e on a.dept_no = e.부서 and d.salary = e.최대연봉
where b.to_date >= sysdate() and d.to_date >= sysdate();



