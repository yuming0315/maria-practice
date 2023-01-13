select * from emaillist;

-- emaillist

-- insert
insert into emaillist values(null,'둘','리','dooly@gmail.com');

-- delete
delete from emaillist where email = 'dooly@gmail.com';

-- list
select no, first_name, last_name,email from emaillist order by no desc;


desc author;

select no,name from author;
select no,title,rent,author_no from book;

insert into book values(null, ?);

select * from author where name = "스테파니메이어";


select a.no, a.title, a.rent, b.name
from book a join author b on a.author_no = b.no;

update 

