select * from members;

insert into members values(no,'허유미','gjdbal111@naver.com',password('1234'),null);

insert into members values(no,'안지민','dkswlals22@naver.com',password('1234'),null);


select * from members where pw = password('1234');

select name,email,phonenum from members;

desc category;

insert into categorys values(no, "컴퓨터/IT");

desc book;

select category from categorys;
select no from book where class = ?;

select a.no, a.title, b.category, a.price
from book a join categorys b on a.category_no = b.no;

select * from categorys;

desc cart;

select b.title, b.price, a.amount
from cart a join book b on a.book_no = b.no
where a.members_no = 1;

insert into cart values(no,1,1,1);

desc order_book;

select max(no)
from orders
where members_no = 1;

select b.title,b.price,a.amount
from order_book a join books b on a.book_no = b.no
where a.orders_no = ?;



