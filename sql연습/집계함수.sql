-- 집계쿼리: select절에 통계함수(avg,max,min,count,sum,stddev,varance, ...)

select avg(salary) from salaries;

-- select 절에 그룹함수(통계함수)있는 경우, 어떤 컬럼도 select절에 올 수 없다
-- emp_no는 아무 의미가 없다.
-- 오류.
select emp_no, avg(salary)
from salaries;

-- quary 순서
-- 1) from: 접근 테이블 확인
-- 2) where: 조건에 맞는 row를 선택
-- 3) 집계
-- 4) projection
-- 5) group by에 참여하고 있는 컬럼은 projection이 가능하다: select절에 올 수 있다.
-- 6) having
-- 	  집계 결과(결과 테이블)에서 row를 선택해야 하는 경우
--    이미 where절은 실행이 되었기 때문에 having절 에서 조건을 주어야 한다.
-- 7) order by
--    order by는 항상 맨 마지막 출력전에 한다.
-- 주의)
-- 예제: 사번이 10060인 사원의 사번, 급여평균, 급여총합 을 출력하세요.
-- 문법적 오류 의미적으론 맞음
select emp_no, avg(salary), sum(salary)
from salaries
where emp_no = '10060';

-- group으로 묶어주는게 맞음.
-- 이렇게 쓰도록 하기,,,
select emp_no, avg(salary), sum(salary)
from salaries
group by emp_no having emp_no = 10060;

-- 예제: 사번이 10060인 사원이 받은 평균 월급은?
select emp_no, avg(salary)
from salaries
where emp_no = '10060';

-- 예제: 사원별 평균 월급은?
select emp_no,avg(salary)
from salaries
group by emp_no;

-- 예제: 평균 월급이 60,000 달러 이상인 사원의 사번과 평균 월급을 출력하세요.
select emp_no,avg(salary) as savg
from salaries
group by emp_no having avg(salary) >= 60000
order by savg;

-- 서브쿼리?를 통해 평균월급을 조건에 넣어줌 그룹바이로 묶은 emp_no당 평균임 avg(salary) 는
select emp_no,avg(salary) as savg
from salaries
group by emp_no having savg >= (select avg(salary) from salaries)
order by savg;


