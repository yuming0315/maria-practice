-- 날짜함수

-- curdate(), current_date
select curdate(), current_date from dual;

-- curtime(), current_time
select curtime(), current_time from dual;

-- now(), sysdate()
select now(), sysdate() from dual;

-- now() vs sysdate() now는 sleep이 안통하고 sysdate는 sleep이 통함 함수호출 시점이느냐 시스템에서 읽어오느냐
select now(), sleep(2),now() from dual;
select sysdate(), sleep(2), sysdate() from dual;

-- date_format()
-- 2023-01-09 15:19:20
select date_format(now(),'%Y-%m-%d %h:%i:%s') from dual;
select date_format(now(),'%Y년 %m월 %d일 %h시 %i분 %s초') from dual;
select date_format(now(),'%b, %y \ %h시 %i분 %s초') from dual;

-- period_diff
-- YYMM, YYYYMM
-- 예) 근무개월
select first_name,hire_date, 
period_diff(date_format(curdate(),'%y%m'), date_format(hire_date,'%y%m')) month
from employees
order by month desc;
-- 왜 'month'로 하면 정렬 못하는지 month로 하면 됨

-- date_add(=adddate), date_sub(=subdate)
-- 날짜를 date 타입의 컬럼이나 값에 type(year,month,day)의 표현식으로 더하거나 뺄 수 있다.
-- 예) 각 사원의 근속년 수가 5년이 되는 날에 휴가를 보내준다면 각 사원의 5년 근속 휴가 날짜는?
-- interval _ year 하면 _년후를 계산  _ month는 _달 후
select first_name,hire_date,date_add(hire_date, interval 5 year) from employees;

-- cast
select '12345' + 10, cast('12345' as int) + 10 from dual;

select date_format(cast('20230109' as date),'%y년 %m월 %d일') from dual;

select cast(cast(1-2 as unsigned) as signed) from dual;
select cast(cast(1-2 as unsigned) as integer) from dual;

-- type
-- 문자: varchar, char, text, CLOB(Character Large OBject)
-- 정수: medium int, int(signed,integer), usigned, big int
-- 실수: float, double
-- 시간: date, datetime
-- LOB: CLOB, BLOB(Binary Large OBject)



