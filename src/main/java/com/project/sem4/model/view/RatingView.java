package com.project.sem4.model.view;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RatingView {
    Integer id;
    Integer starRating;
    String comment;
    String nameUser;
    String productName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date createAt;
}
