create database Shop_House
go
use Shop_House
go

-- bảng users lưu thông tin của khách hàng

create table tbl_users(
	UserId BigInt primary key identity(1,1),
	FullName nvarchar(255) not null,
	Birthday datetime not null,
	PhoneNumber varchar(100),
	Email varchar(150) not null unique,
	Address nvarchar(255),
	Password varchar(200),
	locked bit default(0),
	enabled bit default(0)
)
go
create table confirmation_token(
	id bigint primary key identity(1,1),
	confirmation_token varchar(255),
	created_date date,
	userId bigint,
)
go
ALTER TABLE confirmation_token ADD CONSTRAINT FK_ConfigUsers FOREIGN KEY (userId) REFERENCES tbl_users(UserId)
-- bảng role
create table tbl_role
(
  roleID   Int  primary key identity(1,1),
  roleName VARCHAR(30)
)
go
create table tbl_userRole(
	Id int primary key identity(1,1),
	UserId BigInt,
	RoleId int
)
GO


--  
-- bảng whishlist
create table tbl_WishList(
	Id int primary key identity(1,1),
	UserId BigInt,
	ProductId bigint,
	Create_At datetime default getdate()
)
GO
-- bảng banner
CREATE TABLE tbl_banner (
	Id INT PRIMARY KEY IDENTITY(1,1),
	ImageLink varchar(255) NOT NULL,
	TitleBanner nvarchar(255) not null,
	Description nvarchar(255),
	LinkRedirect varchar(255),
	CreateDate Datetime default getdate(),
	TypeBanner int,
	Status BIT
)
GO
 -- bảng categories lưu danh mục sản phẩm
 create table tbl_categories(
	Id int primary key identity(1,1),
	CategoryName nvarchar(255) not null unique,
	image nvarchar(255),
	description nvarchar(255),
	Url varchar(255) not null,
	parent_id int default 0,
	CreateDate Datetime default getdate(),
	status bit
)
go
 -- bảng products lưu thông tin của sản phẩm
create table tbl_Products(
	Id Bigint primary key identity(1,1),
	ProductName nvarchar(255) not null unique,
	Price float not null,
	ImageLink varchar(255),
	ImageList nvarchar(max),
	Quantity int default(1),
	PriceSale float,
	Note int default(1),
	SaleStatus int default(1),
	Description nvarchar(max),
	[View] int default(0),
	MetaKeyWord nvarchar(255),
	MetaTitle nvarchar(255),
	MetaDescription nvarchar(255),
	slug varchar(255) not null,
	Create_at datetime default getdate(),
	Status bit

)
go

create table tbl_productCategory(
	ProductId BigInt,
	CategoryId int,
)
go
-- bảng rating
create table tbl_rating(
	Id int primary key identity(1,1),
	StarRating int default(5),
	Comment nvarchar(255),
	UserId BigInt,
	ProductId bigint,
	CreateAt datetime default getdate()
)
Go



-- lưu kiểu thuộc tính
create table tbl_AttributeSet(
	Id int primary key identity(1,1),
	Name nvarchar(255) not null ,
	Description nvarchar(255),
	status bit default(1)
)
go
 -- bảng Attribute lưu thông tin của thuộc tính của sản phẩm
 create table tbl_Attribute(
	Id int primary key identity(1,1),
	AttributeSetId int,
	Description nvarchar(255),
	Name nvarchar(255) not null unique,
	DataType varchar(100),
	DefaultValue varchar(255)
 )
 GO
 create table tbl_ProductAttribute(
	Id int primary key identity(1,1),
	ProductId bigint,
	AttributeId int,
 )
 GO
 -- bảng orders
 create table tbl_order(
  Id int primary key identity(1,1),
  UserId BigInt,
  fullName nvarchar(255),
  Total int not null,
  totalPrice float,
  PaymentId int ,
  Phone varchar(255) not null,
  Address nvarchar(255) not null,
  Description nvarchar(255),
  CreateDate Datetime default getdate(),
  Status int default(1)
 )
 go
 create table tbl_Payment(
	Id int primary key identity(1,1),
	Name nvarchar(255),
	Description nvarchar(255),
	Title nvarchar(255),
	CreateAt datetime default getdate()
)
GO

 -- bảng orderDetail
 CREATE TABLE tbl_OrderDetail(
	Id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	OrderId	INT,
	ProductId bigInt,
	Total INT default(1) not null,
	Price FLOAT NOT NULL
)
GO
-- bảng OrderDetailAttributeProduct lưu thuộc tính của sản phẩm
create table tbl_OrderDetailAttributeProduct(
	OrderDetailId int,
	AttributetId int,
)
GO


-- Bảng CATEGORIES blog
CREATE TABLE tbl_BlogCategories(
	Id int primary key identity(1,1),
	Name nvarchar(255) not null,
	Slug varchar(255),
	Description nvarchar(255),
	CreateAt datetime default getdate()
)
GO
-- tạo bảng blog
create table tbl_blogs(
	Id int primary key identity(1,1),
	BlogCategoryId int,
	Content nvarchar(max) not null,
	Image varchar(255),
	Slug varchar(255),
	Tag nvarchar(255),
	title nvarchar(255),
	MetaTitle nvarchar(255),
	MetaContent nvarchar(255),
	MetaKeyword nvarchar(255),
	UserId BigInt,
	Create_At datetime default getdate()
)
GO

-- bảng partner đối tác
CREATE TABLE tbl_partner(
	Id int primary key identity(1,1),
	ImageLogo varchar(255) not null,
	Title nvarchar(255),
	UrlRedirect varchar(255),
	Description nvarchar(255),
	CreateAt datetime default getdate()
)
GO
-- bảng thông báo popup
CREATE TABLE tbl_popup(
	Id int primary key identity(1,1),
	Title nvarchar(255) not null,
	Content nvarchar(255) not null,
	ImageBackground varchar(255), 
	[Status] bit,
	DateStart datetime,
	DateEnd datetime,
	create_at datetime default getdate(), 
)
GO
-- bảng Discount mã giảm giá
create table tbl_Discount(
	Id int Primary key identity(1,1),
	Description nvarchar(255),
	Code_Discount varchar(255) not null unique,
	Type_Discount int default(1),
	discount varchar(100) not null,
	maxDiscount float,
	date_end datetime,
	date_start datetime,
	select_product varchar(max),
	select_user varchar(max),
	create_at datetime default getdate()
)
GO

-- Bảng AddressCities Tỉnh/TP
CREATE TABLE tbl_AddressCities(
	Id int primary key not null,
	Name nvarchar(255) not null,
	Type nvarchar(255)
)
GO

-- Bảng AddressDistrict Quận, Huyện
CREATE TABLE tbl_AddressDistrict(
	Id int primary key not null,
	Name nvarchar(255) not null,
	Type nvarchar(255),
	CitiesId int,
)
GO
-- Bảng AddressWards xã phường
CREATE TABLE tbl_AddressWards(
	Id int primary key not null,
	Name nvarchar(255) not null,
	Type nvarchar(255),
	DistrictId int
)
GO
-- Khóa ngoại của bảng tỉnh thành việt nam
ALTER TABLE tbl_AddressDistrict ADD CONSTRAINT FK_AddDis_Cities FOREIGN KEY (CitiesId) REFERENCES tbl_AddressCities(Id)
ALTER TABLE tbl_AddressWards ADD CONSTRAINT FK_Wards_District FOREIGN KEY (DistrictId) REFERENCES tbl_AddressDistrict(Id)
-- khóa ngoại danh mục sản phẩm
AlTER TABLE tbl_ProductCategory ADD CONSTRAINT FK_ProCat_Pro FOREIGN KEY (ProductId) REFERENCES tbl_Products(Id)
AlTER TABLE tbl_ProductCategory ADD CONSTRAINT FK_ProCat_Cat FOREIGN KEY (CategoryId) REFERENCES tbl_Categories(Id)
-- Khóa ngoại thuộc tính sản phẩm
alter table tbl_Attribute ADD CONSTRAINT FK_Att_AttSet FOREIGN KEY (AttributeSetId) REFERENCES tbl_AttributeSet(Id)


-- khóa ngoại ProductAttribute với Product và Attribute
ALTER TABLE tbl_ProductAttribute ADD CONSTRAINT FK_ProAtt_Pro FOREIGN KEY (ProductId) REFERENCES tbl_Products(Id)
ALTER TABLE tbl_ProductAttribute ADD CONSTRAINT FK_ProAtt_Att FOREIGN KEY (AttributeId) REFERENCES tbl_Attribute(Id)
-- khóa ngoại phân quyền
AlTER TABLE tbl_userRole ADD CONSTRAINT FK_User_UserRole FOREIGN KEY (UserId) REFERENCES tbl_users(UserId)
AlTER TABLE tbl_userRole ADD CONSTRAINT FK_Role_UserRole FOREIGN KEY (RoleId) REFERENCES tbl_role(roleId)
--AlTER TABLE tbl_user_permission ADD CONSTRAINT FK_Per_Per FOREIGN KEY (PermissionId) REFERENCES tbl_Permission(PermisionId)
--AlTER TABLE tbl_per_detaill ADD CONSTRAINT FK_Per_Detaill FOREIGN KEY (PermisionId) REFERENCES tbl_Permission(PermisionId)

-- khóa ngoại thanh toán
ALTER TABLE tbl_OrderDetail ADD CONSTRAINT FK_OrderDetail FOREIGN KEY (OrderId) REFERENCES tbl_order(Id)
ALTER TABLE tbl_OrderDetail ADD CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (ProductId) REFERENCES tbl_Products(Id)
ALTER TABLE tbl_order ADD CONSTRAINT FK_Order_User FOREIGN KEY (UserId) REFERENCES tbl_users(UserId)

-- Whish List
ALTER TABLE tbl_WishList ADD CONSTRAINT FK_WishList_User FOREIGN KEY (UserId) REFERENCES tbl_users(UserId)
ALTER TABLE tbl_WishList ADD CONSTRAINT FK_WishList_Product FOREIGN KEY (ProductId) REFERENCES tbl_Products(Id)
-- khóa ngoại rating
ALTER TABLE tbl_rating ADD CONSTRAINT FK_User_Rating FOREIGN KEY (UserId) REFERENCES tbl_users(UserId)
ALTER TABLE tbl_rating ADD CONSTRAINT FK_Product_Rating FOREIGN KEY (ProductId) REFERENCES tbl_Products(Id)

-- khóa ngoại blog
AlTER TABLE tbl_blogs ADD CONSTRAINT FK_Blog_CateBlog FOREIGN KEY (BlogCategoryId) REFERENCES tbl_BlogCategories(Id)
-- khóa ngoại cho thuốc tính sản phẩm đơn hàng
AlTER TABLE tbl_OrderDetailAttributeProduct ADD CONSTRAINT FK_OrderDetaillAttPro_OrderDetail FOREIGN KEY (OrderDetailId) REFERENCES tbl_OrderDetail(Id)
AlTER TABLE tbl_OrderDetailAttributeProduct ADD CONSTRAINT FK_OrderDetaillAttPro_AttrPro FOREIGN KEY (AttributetId) REFERENCES tbl_Attribute(Id)

--khóa ngoại của phương thức thanh toán
AlTER TABLE tbl_order ADD CONSTRAINT FK_payment_order FOREIGN KEY (PaymentId) REFERENCES tbl_Payment(Id)


-- Thủ tục
GO

CREATE PROC create_cities
	@Id int = null,
	@Name nvarchar(255) = null,
	@Type nvarchar(255)
AS
if(@Id is null)
	begin 
		Raiserror('vui lòng nhập Id', 16,1)
	end
Else
	begin
		INSERT INTO tbl_AddressCities VALUES (@Id, @Name, @Type)
	end
go
-- thêm quận huyện
CREATE PROC create_district
	@Id int, 
	@Name nvarchar(255) ,
	@Type nvarchar(255),
	@CitiesId int 

AS
	INSERT INTO tbl_AddressDistrict VALUES (@Id, @Name, @Type, @CitiesId)
GO
-- sửa table quận huyện
CREATE PROC editDistrict
	@Id int,
	@Name nvarchar(255),
	@Type nvarchar(255),
	@CitiesId int
as
update tbl_AddressDistrict set Name = @Name, Type = @Type, CitiesId = @CitiesId where Id = @Id
GO
-- tìm district theo id
create proc findDistrictById
@Id int
AS
SELECT d.Id, d.Name, d.Type, d.CitiesId, c.Name as CityName FROM tbl_AddressDistrict d inner join tbl_AddressCities c on d.CitiesId = c.Id where d.Id = @Id
Go


-- lấy dữ liệu bảng tbl_AddressCities
CREATE PROC get_all_Cities
AS
SELECT * FROM tbl_AddressCities
GO
-- Xóa Quận Huyện
Create proc deleteDistrict
@Id int
AS
DELETE FROM tbl_AddressDistrict WHERE Id = @Id
go
-- thêm dữ liệu bảng tbl_AddressWards
CREATE PROC create_Wards
	@Id int,
	@Name nvarchar(255),
	@Type nvarchar(255),
	@DistrictId int
AS 
INSERT INTO tbl_AddressWards VALUES (@Id, @Name, @Type, @DistrictId)
GO
-- tìm xã phường theo quận huyện
create proc findWardByDistrictId
@DistrictId int
as
select w.Id, w.Name, w.Type, d.Id as DistrictId, d.Name as DistrictName  from tbl_AddressWards w inner join tbl_AddressDistrict d on w.DistrictId = d.Id  where w.DistrictId = @DistrictId
go


-- lấy dữ liệu bảng tbl_AddressWards
CREATE PROC getAllWard
AS
SELECT w.Id, w.Name, w.Type, d.Id as DistrictId, d.Name as DistrictName FROM tbl_AddressWards w inner join tbl_AddressDistrict d on w.DistrictId = d.Id
go

--thêm xã phường
create proc insertWard
	@Id int,
	@Name nvarchar(255),
	@Type nvarchar(255),
	@DistrictId int
as
INSERT INTO tbl_AddressWards VALUES (@Id, @Name, @Type, @DistrictId)
go
-- tìm xã phường by id
create proc findWardById
	@Id int
as
SELECT w.Id, w.Name, w.Type, d.Id as DistrictId, d.Name as DistrictName FROM tbl_AddressWards w inner join tbl_AddressDistrict d on w.DistrictId = d.Id where w.Id = @Id
go
-- sửa xã phường
create proc updateWards
	@Id int,
	@Name nvarchar(255),
	@Type nvarchar(255),
	@DistrictId int
as
update tbl_AddressWards set Name = @Name, Type = @Type, @DistrictId = @DistrictId where Id = @Id
go
--tìm cities by id
create proc find_city_by_id
@Id int
AS
select * from tbl_AddressCities where id = @Id
GO
-- xóa cities
Create proc Delete_Cities
@Id int
as
DELETE FROM tbl_AddressCities WHERE Id=@Id
go
create proc Find_District_by_CitiesId
@CitiesId int
as
select * from tbl_AddressDistrict where CitiesId = @CitiesId
go
-- sửa cities
create proc editCities
@Id int,
@Name nvarchar(255),
@Type nvarchar(255)
as
update tbl_AddressCities set Name = @Name, Type = @Type where Id = @Id
go 

-- lấy dữ liệu bảng tbl_AddressDistrict
CREATE PROC get_all_District
AS 
SELECT d.Id, d.Name, d.Type, d.CitiesId, c.Name as CityName FROM tbl_AddressDistrict d inner join tbl_AddressCities c on d.CitiesId = c.Id
GO

-- danh mục

-- get all danh mục
create proc getAllCategories
as
select * from tbl_categories
GO
-- Thêm danh mục
create proc insertCategories
	@CategoryName nvarchar(255),
	@image varchar(255),
	@description nvarchar(255),
	@Url varchar(255),
	@parent_id int,
	@status bit
as
insert into tbl_categories (CategoryName,image, description, Url, parent_id, status) values ( @CategoryName,@image, @description,  @Url, @parent_id, @status)
go

-- tìm danh mục theo id
create proc findCategoryById
	@Id int
as
select * from tbl_categories where Id = @Id
go
-- sửa danh mục
create proc updateCategories
	@Id int,
	@CategoryName nvarchar(255),
	@image varchar(255),
	@description nvarchar(255),
	@Url varchar(255),
	@parent_id int,
	@status bit
as
update tbl_categories set CategoryName = @CategoryName,image = @image, description = @description,Url = @Url, parent_id = @parent_id, status = @status where Id = @Id
go
-- xóa danh mục
create proc deleteCategories
	@Id int
as
BEGIN
DECLARE @check_id INT
DECLARE @bl Bit
SET @check_id = (SELECT COUNT(CategoryId) from tbl_productCategory where CategoryId = @Id)
IF @check_id = 0
BEGIN
	DELETE FROM tbl_categories WHERE Id=@Id
	print 'xóa thành công'
	SET @bl = 1
END
else
	print 'xóa thất bại'
	SET @bl = 0
END
	RETURN @bl
go

-- bảng thuộc tính
-- lấy tất cả thuộc tính set
create proc getAllAttributeSet
as
select * from tbl_AttributeSet
go
-- thêm thuộc tính set
create proc createAttributeSet
	@Name nvarchar(255),
	@Description nvarchar(255)
as
insert into tbl_AttributeSet (name, description) values (@Name, @Description)
go
-- tìm thuộc tính theo id
create proc findAttributeSetById
	@Id int
as
select * from tbl_AttributeSet where Id = @Id
Go
-- sửa thuộc tính sản phẩm
create proc updateAttributeSet
	@Id int,
	@Name nvarchar(255),
	@Description nvarchar(255)
as
update tbl_AttributeSet SET  Name = @Name, Description = @Description where Id = @Id
go
-- xóa thuộc tính sản phẩm
create proc deleteAttributeSet
	@Id int
as
BEGIN
DECLARE @check_id INT
DECLARE @bl Bit
SET @check_id = (SELECT COUNT(Id) from tbl_Attribute where AttributeSetId = @Id)
IF @check_id = 0
BEGIN
	DELETE FROM tbl_AttributeSet WHERE Id=@Id
	print 'xóa thành công'
	SET @bl = 1
END
else
	print 'xóa thất bại'
	SET @bl = 0
END
	RETURN @bl
go
-- get All attribute
create proc getAllAttribute
as
select * from tbl_Attribute
go
-- tìm giá trị của thuộc tính theo thuộc tính ID
create proc findAttributeByAttributeSetId
	@attributeSetId int
as
select * from tbl_Attribute where AttributeSetId = @attributeSetId
go
-- thêm giá trị của thuộc tính
create proc createAttribute
	@AttributeSetId int,
	@Description nvarchar(255),
	@Name nvarchar(255),
	@DataType varchar(100),
	@DefaultValue varchar(255)
as
insert into tbl_Attribute (AttributeSetId, Description,Name,DataType,DefaultValue) values (@AttributeSetId, @Description, @Name, @DataType, @DefaultValue)
GO
-- tìm thuộc tính by Id
create proc findAttributeById
	@Id int
as
select * from tbl_Attribute where Id = @Id
go
-- sửa giá trị của thuộc tính
create proc updateAttribute
	@Id int,
	@AttributeSetId int,
	@Description nvarchar(255),
	@Name nvarchar(255),
	@DataType varchar(100),
	@DefaultValue varchar(255)
as
update tbl_Attribute set AttributeSetId = @AttributeSetId, Description = @Description, Name = @Name, DataType = @DataType, DefaultValue = @DefaultValue where Id = @Id
go
-- xóa giá trị của thuộc tính
create proc deleteAttribute
	@Id int
as
BEGIN
DECLARE @check_id INT
DECLARE @bl Bit
SET @check_id = (SELECT COUNT(Id) from tbl_ProductAttribute where AttributeId = @Id)
IF @check_id = 0
BEGIN
	DELETE FROM tbl_Attribute WHERE Id=@Id
	print 'xóa thành công'
	SET @bl = 1
END
else
	print 'xóa thất bại'
	SET @bl = 0
END
	RETURN @bl
go
-- lấy tất cả phương thức thanh toán
create proc getAllPayment
as
select * from tbl_Payment
go
-- thêm phương thức thanh toán
create proc createPayment
	@Name nvarchar(255),
	@Description nvarchar(255),
	@Title nvarchar(255)
as
insert into tbl_Payment (Name, Description, Title) values (@Name, @Description, @Title)
Go
-- sửa phương thức thanh toán
create proc updatePayment
	@Id int,
	@Name nvarchar(255),
	@Description nvarchar(255),
	@Title nvarchar(255)
as
update tbl_Payment set Name = @Name, Description = @Description, Title = @Title where Id = @Id
go
-- tìm phương thức thanh toán theo id
create proc findPaymentById
	@Id int
as
select * from tbl_Payment where Id = @Id
go
-- xóa phương thức thanh toán
create proc deletePayment
	@Id int
as
	BEGIN
	DECLARE @check_id INT
	DECLARE @bl Bit
	SET @check_id = (SELECT COUNT(Id) from tbl_order where PaymentId = @Id)
	IF @check_id = 0
	BEGIN
		DELETE FROM tbl_Payment WHERE Id=@Id
		print 'xóa thành công'
		SET @bl = 1
	END
	else
		print 'xóa thất bại'
		SET @bl = 0
	END
		RETURN @bl
go
-- lấy tất cả banner
create proc getAllBanner
as
select * from tbl_banner
go
-- thêm mới banner
create proc createBanner
	@ImageLink varchar(255),
	@TitleBanner nvarchar(255),
	@Description nvarchar(255),
	@LinkRedirect varchar(255),
	@TypeBanner int,
	@Status BIT
as
insert into tbl_banner (ImageLink, TitleBanner, Description, LinkRedirect,TypeBanner, Status) values (@ImageLink, @TitleBanner, @Description, @LinkRedirect,@TypeBanner, @Status)
go
-- find banner by id
create proc findBannerById
	@Id int
as
select * from tbl_banner where Id = @Id
go
-- cập nhật banner
create proc updateBanner
	@Id int,
	@ImageLink varchar(255),
	@TitleBanner nvarchar(255),
	@Description nvarchar(255),
	@LinkRedirect varchar(255),
	@TypeBanner int,
	@Status BIT
as
update tbl_banner set ImageLink = @ImageLink, TitleBanner = @TitleBanner, Description = @Description, LinkRedirect = @LinkRedirect, TypeBanner = @TypeBanner, Status = @Status where Id = @Id
go

-- xóa banner
create proc deleteBanner
 @Id int
as
delete FROM tbl_banner where Id = @Id
go
-- get all thông báo
create proc getAllPopup
as
select * from tbl_popup
go
-- thêm mới thông báo
create proc insertPopup
	@Title nvarchar(255),
	@Content nvarchar(255),
	@ImageBackground varchar(255), 
	@Status int,
	@DateStart datetime,
	@DateEnd datetime
as
insert into tbl_popup (Title, Content, ImageBackground, Status, DateStart, DateEnd) values (@Title, @Content, @ImageBackground, @Status, @DateStart, @DateEnd)
go
-- tìm popup theo id
create proc findPopupById
	@Id int
as
select * from tbl_popup where Id =  @Id 
go
-- cập nhật thông nhân
create proc updatePopup
	@Id int,
	@Title nvarchar(255),
	@Content nvarchar(255),
	@ImageBackground varchar(255), 
	@Status int,
	@DateStart datetime,
	@DateEnd datetime
as
update tbl_popup set Title = @Title, Content = @Content, ImageBackground = @ImageBackground, Status = @Status, DateStart = @DateStart, DateEnd = @DateEnd where Id = @Id
go
-- xóa thông báo
create proc deletePopup
	@Id int
as
delete from tbl_popup where Id = @Id
go
 --drop proc deleteCategories
 --SELECT COUNT(Id) from tbl_order where PaymentId = 3
 -- lấy tất cả sản phẩm
 create proc getAllProducts
 as
 SELECT * FROM tbl_Products
 go
 -- tìm sản phẩm theo id
 create proc findProductById
 @Id int
 as
 select * from tbl_Products where Id = @Id
 go

-- thêm sản phẩm
create proc insertProducts
	@JsonProduct ntext
as
BEGIN TRANSACTION;
		Insert into tbl_Products (ProductName, Price, ImageLink, ImageList, Quantity, PriceSale, Note, SaleStatus, Description, MetaKeyWord, MetaTitle, MetaDescription, slug, Status)
		select * from OPENJSON(@JsonProduct) with (productName nvarchar(255), price float, imageLink nvarchar(255), imageList varchar(max), quantity int, priceSale float, note int, saleStatus int, description nvarchar(255), metaKeyWord nvarchar(255), metaTitle nvarchar(255), metaDescription nvarchar(255), slug varchar(255), status bit)
		insert tbl_productCategory( ProductId, CategoryId) select IDENT_CURRENT('tbl_Products'), cat.* from ( select value from OPENJSON(@JsonProduct, '$.categories')) AS cat
		insert tbl_ProductAttribute (ProductId, AttributeId) select IDENT_CURRENT('tbl_Products'), attr.* from ( select value from OPENJSON(@JsonProduct, '$.productAttributes')) AS attr
COMMIT
go

-- xóa sản phẩm
create proc deleteProduct
	@Id bigint
as
BEGIN
DECLARE @idOder INT
DECLARE @idRat Int
declare @idWish int
DECLARE @bl Bit
SET @idOder = (SELECT COUNT(Id) FROM tbl_OrderDetail where ProductId = @Id)
Set @idRat = (select COUNT(Id) from tbl_rating where ProductId = @Id)
set @idWish = (select COUNT(Id) from tbl_WishList where ProductId = @Id)

IF @idOder = 0  and @idRat = 0 and @idWish = 0
BEGIN
	delete from tbl_productCategory where ProductId = @Id
	delete from tbl_ProductAttribute where ProductId = @Id
	DELETE FROM tbl_Products WHERE Id=@Id
	print 'xóa thành công'
	SET @bl = 1
END
else
	print 'xóa thất bại'
	SET @bl = 0
END
	RETURN @bl
go

-- sửa product
create proc editProduct
	@JsonEdit ntext
as
	
	BEGIN TRANSACTION;
	DECLARE @proId bigint
		UPDATE p 
		set p.productName = j.productName, p.Price = j.price, p.ImageLink = j.imageLink, p.ImageList = j.imageList, p.Quantity = j.quantity, p.PriceSale = j.priceSale, p.Note = j.note, p.SaleStatus = j.saleStatus, p.Description = j.description, p.MetaKeyWord =j.metaKeyWord, p.MetaTitle = j.metaTitle, p.MetaDescription = j.metaDescription, p.slug = j.slug, p.Status = j.status
		from tbl_Products as p	
		join OPENJSON(@JsonEdit) with (id bigint,productName nvarchar(255), price float, imageLink nvarchar(255), imageList varchar(255), quantity int, priceSale float, note int, saleStatus int, description nvarchar(255), metaKeyWord nvarchar(255), metaTitle nvarchar(255), metaDescription nvarchar(255), slug varchar(255), status bit) j
		ON j.id = p.id
		SET @proId = (select id from OPENJSON(@JsonEdit) with (id bigint))
		delete from tbl_productCategory where ProductId = @proId
		insert tbl_productCategory( ProductId, CategoryId) select @proId, cat.* from ( select value from OPENJSON(@JsonEdit, '$.categories')) AS cat
		delete from tbl_ProductAttribute where ProductId = @proId
		insert tbl_ProductAttribute (ProductId, AttributeId) select @proId, attr.* from ( select value from OPENJSON(@JsonEdit, '$.productAttributes')) AS attr
	commit

go

--tìm sản phẩm theo id
create proc findProductsById
	@Id bigint
as
select * from tbl_Products where Id = @Id
go
-- find User by Email
create proc findUserByEmail
	@Email varchar(255)
as
select * from tbl_users where Email = @Email
go

-- get role by user
create proc getRoleByUser
	@UserId bigInt
as
select * from tbl_UserRole ur inner join tbl_role r  on ur.roleId = r.roleID where ur.userId = @UserId
go
create proc getAllRole
as
select * from tbl_UserRole ur inner join tbl_role r  on ur.roleId = r.roleID
go
-- get all role
create proc getAllRoles
as
select * from tbl_role
go
-- quản lý bog
create proc getAllBlogCategory
as
select * from tbl_BlogCategories
go
-- thêm danh mục blog
create proc insertBlogCategory
	@Name nvarchar(255),
	@Description nvarchar(255),
	@Slug varchar(255)
as
insert into tbl_BlogCategories (Name, Description, Slug) values (@Name, @Description, @Slug)
go

-- tìm theo id
create proc findBlogCategoryById
	@Id int
as
select * from tbl_BlogCategories where Id = @Id
go
create proc editBlogCategory
	@Id int,
	@Name nvarchar(255),
	@Description nvarchar(255)
as
update tbl_BlogCategories set Name = @Name, Description = @Description where Id = @Id
go


--xóa danh mục blog
create proc deleteBlogCategory
	@Id int
as
	BEGIN
	DECLARE @check_id INT
	DECLARE @bl Bit
	SET @check_id = (SELECT COUNT(Id) from tbl_blogs where BlogCategoryId = @Id)
	IF @check_id = 0
	BEGIN
		DELETE FROM tbl_BlogCategories WHERE Id=@Id
		print 'xóa thành công'
		SET @bl = 1
	END
	else
		print 'xóa thất bại'
		SET @bl = 0
	END
		RETURN @bl
go
-- get All Blog
create proc getAllBlog
as
select b.*, cb.Name as CategoryBlogName from tbl_blogs b inner join tbl_BlogCategories cb on b.BlogCategoryId = cb.Id
go
-- find blog by slug
create proc getBlogBySlug
	@slug varchar(255)
as
select b.*, cb.Name as CategoryBlogName from tbl_blogs b inner join tbl_BlogCategories cb on b.BlogCategoryId = cb.Id where b.Slug = @slug
go
-- tạo blog 
create proc createBlog
	@BlogCategoryId int,
	@Content nvarchar(max) ,
	@Image varchar(255),
	@Slug varchar(255),
	@Tag nvarchar(255),
	@title nvarchar(255),
	@MetaTitle nvarchar(255),
	@MetaContent nvarchar(255),
	@MetaKeyword nvarchar(255),
	@UserId BigInt
as
insert into tbl_blogs (BlogCategoryId, Content, Image, Slug , Tag, title,MetaTitle,MetaContent, MetaKeyword, UserId) values (@BlogCategoryId, @Content, @Image,@Slug, @Tag, @title, @MetaTitle, @MetaContent, @MetaKeyword, @UserId)
go

-- lấy bài viết theo Id
create proc findBlogById
@Id int
as 
select * from tbl_blogs where Id = @Id
go
-- sửa Bài Viết
create proc editBlog
	@Id int,
	@BlogCategoryId int,
	@Content nvarchar(max) ,
	@Image varchar(255),
	@Slug varchar(255),
	@Tag nvarchar(255),
	@title nvarchar(255),
	@MetaTitle nvarchar(255),
	@MetaContent nvarchar(255),
	@MetaKeyword nvarchar(255)
as
update tbl_blogs set BlogCategoryId = @BlogCategoryId, Content = @Content, Image = @Image, Slug = @Slug, Tag = @Tag, title = @title, MetaTitle = @MetaTitle, MetaContent = @MetaContent, MetaKeyword = @MetaKeyword where Id = @Id
go
-- xóa bài viết
create proc deleteBlog
@Id int
as
delete FROM tbl_blogs where Id = @Id
go
-- get json 
go
create proc returnJson
as
DECLARE @AttributeJson NVARCHAR(MAX) = (SELECT
      c.*,
      attribute.*
   from
                      tbl_AttributeSet   as c
      Inner JOIN tbl_Attribute as attribute ON attribute.AttributeSetId = c.Id
	order by
    c.Id,
    attribute.Id
   FOR
      JSON AUTO)
select @AttributeJson as AttributeJson
go
exec returnJson
insert into tbl_users (FullName, Birthday, PhoneNumber, Email, Address, Password, enabled) values ( N'admin', '05/01/2001', 0454788445, 'admin@gmail.com', N'thái bình', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1)
insert into tbl_role (roleName) values ('ROLE_ADMIN')
insert into tbl_role (roleName) values ('ROLE_USER')
insert into tbl_userRole values (1,1)
insert into tbl_userRole values (1,2)
-- Client get Category By Product ID
go
create proc getCategoryByProId
	@ProductId bigInt
as
select DistinCt c.* from tbl_productCategory pc inner join tbl_categories c on pc.CategoryId = c.Id where pc.ProductId = @ProductId
go

-- lấy list attribute by product
create proc getAllAttributeByProId
	@proId bigint
as
	select * from tbl_ProductAttribute where ProductId = @proId
	go
-- lấy attribute group \
create proc getAttrSetByProduct
	@attrId bigint
as
select DistinCt ast.* from tbl_AttributeSet ast inner join tbl_Attribute att on ast.Id = att.AttributeSetId where att.AttributeSetId =  @attrId
go

-- lấy attribute by product
create proc getAttributeByProId
	@proId bigInt,
	@AttrId int
as
	begin
		select distinct a.* from tbl_ProductAttribute pa inner join
		tbl_Attribute a on pa.AttributeId = a.Id 
		inner join tbl_AttributeSet ast on a.AttributeSetId = ast.Id
		where pa.ProductId = @proId and a.AttributeSetId = @AttrId
	end
go


-- get attribute set by product
create proc getAttributeSetByProId
	@proId int
as
	select distinct ats.* from tbl_ProductAttribute pra 
	inner join tbl_Products p on p.Id = pra.ProductId
	inner join tbl_Attribute a on a.Id = pra.AttributeId
	inner join tbl_AttributeSet ats on ats.Id =  a.AttributeSetId
	where p.Id = @proId
go
-- lấy attribute by product and attribute set
Create proc getAllAttributeByProIdAndAttrSetId
	@proId bigint,
	@AttrSId int
as
	select distinct a.* from tbl_Attribute a 
	inner join tbl_ProductAttribute pa on pa.AttributeId = a.Id
	where pa.ProductId = @proId and a.AttributeSetId = @AttrSId
go
-- get product 
create proc getProductByCate
	@url varchar(255)
as
select p.* from tbl_Products p 
inner join tbl_productCategory pc on pc.ProductId = p.Id
inner join tbl_categories c on c.Id = pc.CategoryId
where c.Url = @url
go

-- get product by id 
create proc getProductBySlug
	@slug varchar(max)
as
select * from tbl_Products where slug = @slug
go
-- get category by product Id 




	  
create proc getAttribute
	@proId bigint
as
DECLARE @getAttributeByProId NVARCHAR(MAX) = (select ass.*, attributeList.* from tbl_ProductAttribute pc 
	  inner join tbl_Attribute attributeList on pc.AttributeId = attributeList.Id
	  inner join tbl_AttributeSet ass on attributeList.AttributeSetId = ass.Id
	  where pc.ProductId = @proId
	  order by 
		attributeList.Id, ass.Id
		for 
			JSON AUTO)
select @getAttributeByProId as getAttributeByProId
go
create proc getRelatedProduct
	@price float
as 
select * from tbl_Products where Price BETWEEN @price-50000 and @price+50000 
go
-- get all discount
create proc getAllDiscount
as
select * from tbl_Discount
go
-- insert discount
create proc insertDiscount
	@Description nvarchar(255),
	@Code_Discount varchar(255),
	@Type_Discount int,
	@discount varchar(100),
	@maxDiscount float,
	@date_end datetime,
	@date_start datetime,
	@select_product varchar,
	@select_user varchar
as
insert into tbl_Discount (Description, Code_Discount, Type_Discount, discount,maxDiscount, date_end, date_start, select_product, select_user) values (@Description, @Code_Discount, @Type_Discount,@discount,@maxDiscount, @date_end, @date_start, @select_product, @select_user)
go

-- find discount by id
create proc findDiscountById
	@Id int
as
select * from tbl_Discount where Id = @Id
go
-- edit discount
create proc editDiscount
	@Id int,
	@Description nvarchar(255),
	@Code_Discount varchar(255),
	@Type_Discount int,
	@discount varchar(100),
	@maxDiscount float,
	@date_end datetime,
	@date_start datetime,
	@select_product varchar,
	@select_user varchar
as
update tbl_Discount set Description = @Description, Code_Discount = @Code_Discount, Type_Discount = @Type_Discount, discount =@discount,maxDiscount =@maxDiscount,  date_end = @date_end, date_start = @date_start, select_product = @select_product, select_user = @select_user where Id = @Id
go
--delete discount
create proc deleteDiscount
	@Id int
as
delete FROM tbl_Discount where Id = @Id
go
-- check discount by code
create proc findDiscountByCode
	@code varchar(255)
as
select * from tbl_Discount where Code_Discount = @code
go
-- lọc product theo giá
create proc filterProductByPrice
	@minPrice float,
	@maxPrice Float
as
select * from tbl_Products where Price BETWEEN @minPrice and @maxPrice
go
-- get all user 
create proc getAllUser
as
select * from tbl_users
go

-- find user by id
create proc findUserById
	@Id bigint
as
select * from tbl_users where UserId = @Id
go
-- edit user 
create proc editUser
	@UserId bigint,
	@FullName nvarchar(255),
	@Birthday datetime,
	@PhoneNumber varchar(100),
	@Email varchar(150),
	@Address nvarchar(255),
	@Password varchar(200),
	@locked bit,
	@enabled bit
as
update tbl_users set FullName = @FullName, Birthday = @Birthday, PhoneNumber = @PhoneNumber, Email = @Email, Address = @Address, Password = @Password, locked = @locked, enabled = @enabled where UserId = @UserId
go
-- thêm user
create proc insertUser
	@FullName nvarchar(255),
	@Birthday datetime,
	@PhoneNumber varchar(100),
	@Email varchar(150),
	@Address nvarchar(255),
	@Password varchar(200),
	@locked bit,
	@enabled bit
as
insert into tbl_users ( FullName, Birthday, PhoneNumber, Email, Address, Password, locked, enabled) values (@FullName, @Birthday, @PhoneNumber, @Email, @Address, @Password, @locked, @enabled )
go
create proc insertRoleUser
	@IdUser bigInt,
	@IdRole int
as
insert into tbl_userRole ( UserId, RoleId) values (@IdUser, @IdRole )
go
create proc getUserByEmail
	@Email varchar(150)
as
select * from tbl_users where Email = @Email
go

-- add config token 
create proc addConfigToken
	@confirmation_token varchar(255),
	@created_date date,
	@userId bigint
as
insert into confirmation_token (confirmation_token, created_date, userId) values (@confirmation_token, @created_date, @userId)
go
-- xóa config token
create proc deleteToken
	@id bigint
as
delete from confirmation_token where id = @id
go
-- find token by token
create proc findTokenByToken
	@confirmation_token varchar(255)
as
select * from confirmation_token where confirmation_token = @confirmation_token
go

-- update confirm user
create proc confirmUser
	@UserId bigint,
	@enabled bit
as
update tbl_users set enabled = @enabled where UserId = @UserId
go
-- get all product pay
create proc getAllProductByOder
as
	select DISTINCT p.*  from tbl_Products p
	inner join tbl_OrderDetail odr on p.Id = odr.ProductId
	inner join tbl_order o on o.Id = odr.OrderId
GO
-- get all order
create proc getAllOder
as
	select ord.*, p.Name, u.FullName from tbl_order ord 
	inner join tbl_Payment p on ord.PaymentId = p.Id
	inner join tbl_users u on ord.UserId = u.UserId
	ORDER BY CONVERT(DateTime, ord.CreateDate ,100)  DESC
go
-- get all order by status
create proc getAllOrderByStatus
	@status int
as
	select ord.*, p.Name, u.FullName from tbl_order ord 
	inner join tbl_Payment p on ord.PaymentId = p.Id
	inner join tbl_users u on ord.UserId = u.UserId
	where ord.Status = @status
	ORDER BY CONVERT(DateTime, ord.CreateDate ,100)  DESC
go
-- get All order by user Id
create proc getOrdrByuserId
	@userId bigint
as
select ord.*, p.Name, u.FullName from tbl_order ord 
	inner join tbl_Payment p on ord.PaymentId = p.Id
	inner join tbl_users u on ord.UserId = u.UserId
	where ord.UserId = @userId
	ORDER BY CONVERT(DateTime, ord.CreateDate ,100)  DESC
go

-- get orderdetail by order
create proc getOrderDetailByOrderId
	@Id int
as
select * from tbl_OrderDetail 
where OrderId = @Id
go

-- get attribute by orderDetailId
create proc getAttributeByOrderDetailId
	@orderDetailId int
as
select a.* from tbl_Attribute a inner join tbl_OrderDetailAttributeProduct odap on a.Id = odap.AttributetId where odap.OrderDetailId = @orderDetailId
go
-- get district by city id
create proc getDistrictByCityId
	@cityId int
as
select * from tbl_AddressDistrict where CitiesId = @cityId
go
-- get ward by district Id
create proc getWardByDistrictId
	@districtId int
as
select * from tbl_AddressWards where DistrictId = @districtId
go
-- insert order check out
create proc insertOrderCheckOut
  @UserId BigInt,
  @fullName nvarchar(255),
  @Total int,
  @totalPrice float,
  @PaymentId int ,
  @Phone varchar(255) ,
  @Address nvarchar(255) ,
  @Description nvarchar(255)
as
	insert into tbl_order (UserId, fullName, Total,totalPrice, PaymentId, Phone, Address, Description ) values (@UserId, @fullName, @Total,@totalPrice, @PaymentId, @Phone, @Address, @Description)
	select IDENT_CURRENT('tbl_order') as idOrder
go

-- insert orderdetail
create proc insertOrderDetail
	@OrderId	INT,
	@ProductId bigInt,
	@Total INT,
	@Price FLOAT
as
Insert into tbl_OrderDetail (OrderId, ProductId, Total, Price) values (@OrderId, @ProductId, @Total, @Price )
select IDENT_CURRENT('tbl_OrderDetail') as idOrder
go

-- insert orderDetail Attribute
create proc insertOrderDetailAttribute
	@OrderDetailId int,
	@AttributetId int
as
insert into tbl_OrderDetailAttributeProduct (OrderDetailId, AttributetId) values (@OrderDetailId, @AttributetId)
go
-- get order by order Id
create proc getOrderById
	@orderId int
as
select * from tbl_order where Id = @orderId
go
-- update status order
create proc updateStatusOrder
	@orderId int,
	@status int
as
update tbl_order set Status = @status where Id = @orderId
go
-- add rating 
create proc addRating 
	@StarRating int ,
	@Comment nvarchar(255),
	@UserId BigInt,
	@ProductId bigint
as
insert into tbl_rating (StarRating, Comment, UserId, ProductId) values (@StarRating, @Comment, @UserId, @ProductId )
go
-- get all ratting by product id
create proc getRatingByProId
	@ProId bigint
as
select r.*, u.FullName from tbl_rating r inner join tbl_users u on u.UserId = r.UserId
where r.ProductId = @ProId
go
-- find product by name
create proc findProductByName
	@proName nvarchar(255)
as
select * from tbl_Products where ProductName = @proName
go
create proc findCategoyByName
	@name nvarchar(255)
as
select * from tbl_categories where CategoryName = @name
go

-- add partner
create proc addPartner
	@ImageLogo varchar(255),
	@Title nvarchar(255),
	@UrlRedirect varchar(255),
	@Description nvarchar(255)
as
insert into tbl_partner (ImageLogo, Title, UrlRedirect, Description) values (@ImageLogo, @Title, @UrlRedirect, @Description)
go
-- like product by name
create proc searceProductByName
	@ProductName nvarchar(255)
as
select * from tbl_Products where ProductName like '%'+@ProductName+'%'
go
create proc resetPassWord
	@Id bigint,
	@PassNew varchar(255)
as
update tbl_users set Password = @PassNew where UserId =@Id
go