-- 문자열 함수

-- upper
select upper('busan'), upper('BusaN'), upper('Busan') from dual;
select distinct upper(first_name), last_name from employees order by upper(first_name);

select lower('BUSan'), lcase('BusAN'), lower('Busan') from dual;

-- substring
select upper(substring('Hello World',3,2)) from dual;

-- 예제) 1989년에 입사한 사원들의 이름, 입사일을 출력하라
select first_name, hire_date
from employees
where substring(hire_date,1,4) = '1989';
-- %주의 substring 1부터 시작%

-- lpad(왼쪽 정렬) rpad(오른쪽 정렬)    left padding , right padding 
select lpad('1234',10,'-') from dual;

-- 예제) 직원들의 월급을 오른쪽 정렬(빈공간 *)
select lpad(salary,10,'*')
from salaries;

-- trim, ltrim, rtrim l과 r은 자기방향만 trim
select concat('---',trim('      hello      '),'---') from dual;
select concat('---',trim(both 'x' from 'xxxxxxxxhelloxxxxxxxx'),'---') from dual;
select concat('---',trim(leading 'x' from 'xxxxxxxxhelloxxxxxxxx'),'---') from dual;
select concat('---',trim(trailing 'x' from 'xxxxxxxxhelloxxxxxxxx'),'---') from dual;

-- length
select length('Hello World') from dual;




