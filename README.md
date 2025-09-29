# GEEK-Up-Project


request -> controller (controller convert to DTO and then transit for service) -> before process at service layer (convert DTO -> Entity) -> process Entity at Service


Đối tượng tham gia:
    User: Admin, Customer (cần có permission)
    Product: thông tin của item được chia theo category
    Category: 
    Order: Cho biết user nào order, id của order 
    OrderDetail: thông tin order của từng item của user
    Cart: Để cho biết tài khoản user đang dùng có bao nhiêu item trong giỏ hàng
    CartDetail: show ra từng item chi tiết trong giỏ hàng
    image: ảnh của item trong shop
    Shop: cho biết tên shop, địa chỉ nào bán item gì




USE `E-commerce-DB`;

-- cau b
set @user_id = (select id from users where email = 'gu@gmail.com');
set @total = (select price * 5 from products where id = 1);

insert into orders (date_ordered, total_amount, user_id)
values (current_date(), @total, @user_id);

set @order_id = last_insert_id();

insert into order_detail (quantity, unit_price, order_id, shop_id, producct_id)
select 5, price, @order_id, 2, id
from products
where id = 1;

select* from orders;

select* from order_detail;

select* from users where users.email="gu@gmail.com";

-- cau b
select
year(order_sums.date_ordered) as order_year,
month(order_sums.date_ordered) as order_month,
avg(order_sums.order_total) as avg_order_value
from (
select
o.id as order_id,
o.date_ordered,
sum(od.quantity * od.unit_price) as order_total
from orders o
join order_detail od on o.id = od.order_id
where year(o.date_ordered) = year(current_date)
group by o.id, o.date_ordered
) as order_sums
group by order_year, order_month;

-- cau d
with
active_last_6m as (
select distinct o.user_id
from orders o
where o.date_ordered >= current_date - interval 6 month
),

active_prev_6m as (
select distinct o.user_id
from orders o
where o.date_ordered >= current_date - interval 12 month
and o.date_ordered < current_date - interval 6 month
)

select
count(distinct ap.user_id) as customers_in_prev_6m,
count(distinct ap.user_id) - count(distinct al.user_id) as churned_customers,
(count(distinct ap.user_id) - count(distinct al.user_id)) * 100.0 / count(distinct ap.user_id) as churn_rate_percent
from active_prev_6m ap
left join active_last_6m al on ap.user_id = al.user_id;





