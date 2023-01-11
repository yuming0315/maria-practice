-- 서브쿼리(SUBQUERY) SQL

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries
where to_date>=sysdate() and salary > 
(
	select avg(salary)
	from salaries
	where to_date >= sysdate()
);

-- 문제2.(x) 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 급여를 조회하세요.
-- 단 조회결과는 급여의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 급여 많은 사원의 사번, 이름과 급여를 조회하세요 
select a.emp_no 사번, a.first_name 이름, b.salary 급여
from employees a join salaries b on a.emp_no = b.emp_no
join dept_emp c on b.emp_no = c.emp_no
join(
	select a.dept_no 부서번호,avg(b.salary) 평균연봉
	from dept_emp a join salaries b on a.emp_no = b.emp_no
	where a.to_date >= sysdate() and b.to_date >= sysdate()
	group by a.dept_no
) d on c.dept_no = d.부서번호 and b.salary > d.평균연봉
where b.to_date >= sysdate() and c.to_date >= sysdate();

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no 사번, a.first_name 이름, c.매니저이름 '매니저 이름',c.부서명 '부서 이름'
from employees a join dept_emp b on a.emp_no = b.emp_no
join (
	select a.dept_no 부서번호, c.first_name 매니저이름, b.dept_name 부서명
	from dept_manager a join departments b on a.dept_no = b.dept_no
    join employees c on a.emp_no = c.emp_no
    where a.to_date >= sysdate()
    ) c on b.dept_no = c.부서번호
where b.to_date >= sysdate();

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 급여를 조회하고 급여순으로 출력하세요.
select a.emp_no 사번, a.first_name 이름, c.title 직책, b.salary 급여
from employees a join salaries b on a.emp_no = b.emp_no and b.to_date >= sysdate()
join titles c on b.emp_no = c.emp_no and c.to_date >= sysdate()
join dept_emp d on d.emp_no = c.emp_no and d.to_date >= sysdate()
join (
	select a.부서넘버 부서넘버
    from (
		select b.dept_no 부서넘버, avg(salary) 평균연봉
		from salaries a join dept_emp b on a.emp_no = b.emp_no
		where a.to_date >= sysdate() and b.to_date >= sysdate()
		group by b.dept_no
    ) a
    where 평균연봉 = (
		select max(a.평균연봉) 평균연봉
		from(
			select avg(salary) 평균연봉
			from salaries a join dept_emp b on a.emp_no = b.emp_no
			where a.to_date >= sysdate() and b.to_date >= sysdate()
			group by b.dept_no
		)a
    )
    ) e on d.dept_no = e.부서넘버;

-- 문제6.
-- 평균 급여가 가장 높은 부서는?
-- 부서이름, 평균급여 
select d.dept_name 부서이름,avg(a.salary) 평균급여
from salaries a join dept_emp b on a.emp_no = b.emp_no and a.to_date >= sysdate() and b.to_date >= sysdate()
join (select a.부서넘버 부서넘버
    from (
		select b.dept_no 부서넘버, avg(salary) 평균연봉
		from salaries a join dept_emp b on a.emp_no = b.emp_no
		where a.to_date >= sysdate() and b.to_date >= sysdate()
		group by b.dept_no
    ) a
    where 평균연봉 = (
		select max(a.평균연봉) 평균연봉
		from(
			select avg(salary) 평균연봉
			from salaries a join dept_emp b on a.emp_no = b.emp_no
			where a.to_date >= sysdate() and b.to_date >= sysdate()
			group by b.dept_no
		)a
    )
    )c on b.dept_no = c.부서넘버
    join departments d on c.부서넘버 = d.dept_no;

-- 문제7.
-- 평균 급여가 가장 높은 직책?
-- 직책, 평균급여 
select b.title 직책, avg(a.salary) 평균급여
from salaries a join titles b on a.emp_no = b.emp_no and a.to_date>=sysdate() and b.to_date >= sysdate()
group by b.title having 평균급여 = 
(
	select max(a.평균급여)
	from
	(
		select avg(salary) 평균급여
		from salaries a join titles b on a.emp_no = b.emp_no and a.to_date>=sysdate() and b.to_date >= sysdate()
		group by b.title
	) a
);

-- 문제8.
-- 현재 자신의 매니저보다 높은 급여를 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 매니저 급여순으로 출력합니다.
select c.부서명 '부서이름', a.first_name 사원이름, d.salary 연봉, c.매니저이름 '매니저 이름', c.매니저연봉 '매니저 급여'
from employees a join dept_emp b on a.emp_no = b.emp_no and b.to_date >= sysdate()
join salaries d on d.emp_no = a.emp_no and d.to_date >= sysdate()
join (
	select a.dept_no 부서번호, c.first_name 매니저이름, b.dept_name 부서명,d.salary 매니저연봉
	from dept_manager a join departments b on a.dept_no = b.dept_no and a.to_date >= sysdate()
    join employees c on a.emp_no = c.emp_no
    join salaries d on c.emp_no = d.emp_no and d.to_date >= sysdate()
    ) c on b.dept_no = c.부서번호 and d.salary > c.매니저연봉;
