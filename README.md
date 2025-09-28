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
    

