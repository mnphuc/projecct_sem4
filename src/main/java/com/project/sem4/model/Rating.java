package com.project.sem4.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
@Data
public class Rating {
    Integer id;
    Integer starRating;
    String comment;
    Long userId;
    Long productId;
    Date createAt;
}
