package com.project.sem4.model.view;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
public class InsertProductModel {
    private Long id;
    @NotEmpty(message = "Tên Sản Phẩm Không Được Để Trống")
    private String productName;
    @NotNull(message = "Giá Không Được Để Trống")
    private Double price;
    @NotEmpty(message = "Ảnh Không Được Để Trống")
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
    @NotEmpty(message = "Phải Chọn Ít Nhất 1 Danh Mục")
    private String[] categories;
    @NotEmpty(message = "Phải Chọn Ít Nhất 1 Thuộc Tính")
    private String[] productAttributes;

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

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(String[] productAttributes) {
        this.productAttributes = productAttributes;
    }
}
