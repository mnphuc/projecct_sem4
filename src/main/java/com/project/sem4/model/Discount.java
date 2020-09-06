package com.project.sem4.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import sun.rmi.runtime.Log;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class Discount {

    private Integer id;
    private String description;
    @NotEmpty(message = "Mã Giảm Giá Không Được Để Trống")
    private String codeDiscount;
    private Integer typeDiscount;
    private String discount;
    private Double maxDiscount;
    private Date dateStart;
    private Date dateEnd;
    private String selectProduct;
    private String selectUser;
    private Date createAt;
}
