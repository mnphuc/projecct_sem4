package com.project.sem4.model;

import java.math.BigInteger;
import java.util.Date;

public class Products {
//    Id Bigint primary key identity(1,1),
//    ProductName nvarchar(255) not null,
//    CategoryId int,
//    ProductPrice float not null,
//    Image_Link varchar(255),
//    Image_List text,
//    Quantity int default(1),
//    ProductPriceSale float,
//    Note int default(1),
//    Sale_Status int default(1),
//    Description text,
//	[View] int,
//    Meta_KeyWord nvarchar(255),
//    Meta_Title nvarchar(255),
//    Meta_Description nvarchar(255),
//    seo_url varchar(255),
//    Create_at datetime default getdate(),
//    Status bit
    private Long id;
    private String productName;
    private Double price;
    private String imageLink;
    private String imageList;
    private Integer quantity;
    private Double priceSale;
    private Integer note;
    private Integer saleStatus;
    private String description;
    private Integer view;
    private String metaTitle;
    private String metaKeyWord;
    private String metaDescription;
    private String slug;
    private Date createAt;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Double priceSale) {
        this.priceSale = priceSale;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeyWord() {
        return metaKeyWord;
    }

    public void setMetaKeyWord(String metaKeyWord) {
        this.metaKeyWord = metaKeyWord;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
