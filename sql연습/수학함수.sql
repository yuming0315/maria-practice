-- 수학 함수

-- abs (절대값)
select abs(-1), abs(1000), abs(-1000) from dual;

-- ceil  (가까운 정수로 올림)
select ceil(3.14), ceiling(3.15) from dual;

-- floor (가까운 정수로 내림)
select floor(3.14) from dual;

-- mod (나머지)
select mod(10,3), 10%3 from dual;

-- round(x) x에 가장 근접한 정수 , 반올림.
select round(1.498),round(1.77) from dual;

-- round(x,d) x를 소수점 d까지 반올림해서 표기
select round(1.498,2),round(1.77,3) from dual;

-- power(x,y) x의 y승
select power(2,10), pow(2,10) from dual;

-- sign(x) 양수면 1 음수면 -1 0이면 0
select sign(20), sign(-100),sign(0) from dual;

-- greatest(x,y,...), least(x,y, ...) 큰 값, 작은 값 문자는 아스키코드값상 값
select greatest(1,2,3,4,5) , least(1,2,3,4,5) from dual;
select greatest('b','A','C','하'), least('b','A','C','D') from dual;

-- max min은 테이블 넣어야 작동하는듯?
select max(salary), min(salary) from salaries;


