package com.project.sem4.model.view;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class InsertUser {
    private Long userID;
    @NotEmpty(message = "Họ Tên Không Được Để Trống")
    private String fullName;
    @NotNull(message = "Sinh Nhật Không Được Để Trống")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date birthday;
    private String phoneNumber;
    @NotEmpty(message = "email không được để trống")
    private String email;
    private String address;
    @NotEmpty(message = "Mật Khẩu không được để trống")
    private String password;
    private Boolean locked ;
    private Boolean enabled;
    private Integer[] RoleId;

}
