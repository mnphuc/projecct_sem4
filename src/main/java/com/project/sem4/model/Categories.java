package com.project.sem4.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class Categories {
    private Integer id;
    @NotEmpty(message = "Tên Danh Mục không được để trống")
    private String categoryName;
    private String image;
    private String description;
    private String url;
    private Integer parentId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    private Boolean status;

}
