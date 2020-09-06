package com.project.sem4.model;



import lombok.Data;


import javax.validation.constraints.NotEmpty;

import java.util.Date;
@Data
public class Banner {
    Integer id;
    @NotEmpty(message = "Ảnh Không Được Bỏ Trống" )
    String imageLink;
    @NotEmpty(message = "Tiêu đề không đưuọc bỏ trống")
    String titleBanner;
    String description;
    String linkRedirect;
    Date createDate;
    Integer typeBanner;
    Boolean status;


}
