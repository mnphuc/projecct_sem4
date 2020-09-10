package com.project.sem4.model;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AttributeSet {
    Integer id;
    @NotEmpty(message = "tên thuộc tính không được để trống")
    String name;
    String description;

}
