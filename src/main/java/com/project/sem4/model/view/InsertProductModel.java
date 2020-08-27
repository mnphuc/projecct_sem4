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
}
