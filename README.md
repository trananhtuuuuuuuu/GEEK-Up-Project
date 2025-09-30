
# 🛒 E-Commerce Backend – Technical Assessment




8. Mô tả các bảng (Entities)

Note: Xem EER Diagram ở file schema.png  

1. users
- Lưu thông tin người dùng đăng ký trên hệ thống.
- Trường chính: id.
- Các trường khác: name, email, phone, gender, province, district, commune, address, housing_type, password (mật khẩu đã băm), refresh_token, role_id (khoá ngoại tới roles).

2. roles
- Lưu vai trò của user.
- Trường chính: id.
- Các trường khác: name (tên role), description (mô tả quyền), active (trạng thái).
- Một role có thể gán nhiều permission thông qua bảng permission_role.

3. permissions
- Lưu định nghĩa quyền truy cập API.
- Trường chính: id.
- Các trường khác: name (tên quyền), api_path (đường dẫn API), method (phương thức HTTP), module (module chức năng).
- Quan hệ nhiều-nhiều với roles thông qua permission_role.

4. permission_role
- Bảng trung gian nối roles và permissions (quan hệ N:N).
- Gồm role_id và permission_id.

5. categories
- Nhóm sản phẩm (ví dụ: shoes, pant, shirt, hat).
- Trường chính: id.
- Trường khác: name.

6. products
- Lưu thông tin sản phẩm.
- Trường chính: id.
- Các trường khác: name, brand, color, description, size, price, inventory, category_id (khoá ngoại tới categories).

7. shop
- Cửa hàng phân phối sản phẩm.
- Trường chính: id.
- Các trường khác: shop_name, shop_address, date_original (ngày thành lập).

8. product_shop
- Bảng trung gian quản lý tồn kho sản phẩm tại từng shop (quan hệ N:N giữa products và shop).
- Trường chính: id.
- Các trường khác: product_id, shop_id (khoá ngoại), quantity (số lượng tồn kho tại shop).

9. orders
- Lưu thông tin đơn hàng của user.
- Trường chính: id.
- Các trường khác: date_ordered (ngày đặt hàng), total_amount (tổng tiền), user_id (khoá ngoại tới users).

10. order_detail
- Chi tiết sản phẩm trong một đơn hàng.
- Trường chính: id.
- Các trường khác: order_id (khoá ngoại tới orders), producct_id (khoá ngoại tới products), shop_id (khoá ngoại tới shop), quantity, unit_price.

11. carts
- Lưu giỏ hàng tạm của user.
- Trường chính: id.
- Các trường khác: user_id (1 user có 1 cart), date_placed, total_amount.

12. cart_detail
- Chi tiết sản phẩm trong cart, tương tự order_detail.
- Trường chính: id.
- Các trường khác: cart_id (khoá ngoại tới carts), product_id (khoá ngoại), shop_id (khoá ngoại), quantity, unit_price, subtotal.

13. discounts
- Lưu khuyến mãi của sản phẩm.
- Trường chính: id.
- Các trường khác: percentage (phần trăm giảm), product_id (khoá ngoại tới products).

14. image
- Lưu file ảnh sản phẩm.
- Trường chính: id.
- Các trường khác: file_name, file_type, download_url, image (blob), product_id (khoá ngoại tới products).

Quan hệ tổng thể:
- Một User -> một Role -> nhiều Permission.
- Một Category -> nhiều Product.
- Một Product <-> nhiều Shop (qua ProductShop).
- Một User -> nhiều Order -> nhiều OrderDetail.
- Một User có 1 Cart -> nhiều CartDetail.
- Một Product -> nhiều Discount & nhiều Image.





## run project với Docker

- File SQL khởi tạo nằm trong `initDB/ecom-db.sql`.

sudo docker compose up -d --build 
sudo docker ps          


import file GEEK Up.technical_assessment.json vào postman để test API 

trừ /login và /registration endpoint thì tất cả API khác phải cần token để có thể call 







