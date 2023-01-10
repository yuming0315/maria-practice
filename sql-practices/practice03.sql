-- 테이블간 조인(JOIN)

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no '사번',a.first_name 이름, b.salary 연봉
from employees a join salaries b on a.emp_no = b.emp_no
where b.to_date <= sysdate()
order by 연봉 desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select a.emp_no 사번, a.first_name 이름, b.title '현재 직책'
from employees a join titles b
on a.emp_no = b.emp_no
where b.to_date >= sysdate()
order by 이름;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select a.emp_no 사번, a.first_name 이름,c.dept_name '현재 부서'
from employees a join dept_emp b join departments c
on a.emp_no = b.emp_no and b.dept_no = c.dept_no
where b.to_date >= sysdate()
order by 이름;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no 사번, a.first_name 이름, b.salary 연봉, f.title 직책,d.dept_name 부서
from employees a join salaries b join dept_emp c join departments d join titles f
on a.emp_no = b.emp_no and b.emp_no = c.emp_no and c.dept_no = d.dept_no and a.emp_no = f.emp_no
group by a.emp_no;

-- 문제5.
-- 'Technique Leader'의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 
-- 이름은 first_name과 last_name을 합쳐 출력 합니다.
select a.emp_no 사번,concat(b.first_name,' ',b.last_name) 이름
from titles a join employees b on a.emp_no = b.emp_no
where a.title = 'Technique Leader' and a.to_date < sysdate();

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select a.first_name 이름, d.dept_name 부서명, b.title 직책
from employees a join titles b join dept_emp c join departments d
on a.emp_no = b.emp_no and b.emp_no = c.emp_no and c.dept_no = d.dept_no
where a.first_name like "S%";

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select a.emp_no 사번, avg(b.salary) 급여
from titles a join salaries b
on a.emp_no = b.emp_no
where a.title = 'engineer' and a.to_date >= sysdate() and b.to_date >= sysdate()
group by a.emp_no having 급여 >= 40000
order by 급여 desc;

-- 문제8.
-- 현재 평균급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오
select a.title 직책,avg(b.salary) 급여
from titles a join salaries b
on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by 직책 having 급여>50000
order by 급여 desc;

-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select c.dept_name 부서명, avg(a.salary) 평균연봉
from salaries a join dept_emp b join departments c
on a.emp_no = b.emp_no and b.dept_no = c.dept_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by 부서명
order by 평균연봉 desc;

-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select a.title 직책, avg(b.salary) '평균 연봉'
from titles a join salaries b
on a.emp_no = b.emp_no
where a.to_date >= sysdate() and b.to_date >= sysdate()
group by 직책
order by `평균 연봉` desc;
-- `으로 구분

