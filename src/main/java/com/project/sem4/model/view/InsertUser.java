package com.project.sem4.model.view;

import com.project.sem4.validator.UniqueEmailUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
@Data
public class InsertUser {
    private Long userID;
    @NotEmpty(message = "Họ Tên Không Được Để Trống")
    private String fullName;
    @NotNull(message = "Sinh Nhật Không Được Để Trống")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date birthday;
    @Pattern(regexp = "(09|01|08|03|07[2|6|8|9])+([0-9]{8})")
    private String phoneNumber;
    @NotEmpty(message = "email không được để trống")
    @Email(message = "Email Không Đúng Định Dạng")
    @UniqueEmailUser
    private String email;
    @NotNull(message = "Địa Chỉ Không Được Để Trống")
    private String address;
    @NotEmpty(message = "Mật Khẩu không được để trống")
    @Min(value = 6, message = "Mật Khẩu Phải Lớn Hơn 6 Ký Tự")
    private String password;
    private Boolean locked ;
    private Boolean enabled;
    private Integer[] RoleId;

}
