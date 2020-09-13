package com.project.sem4.model.service;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ResetPassword {
    private String token;
    @NotEmpty(message = "Mật Khẩu không được để trống")
    @Min(value = 6, message = "Phải Lớn Hơn 6 Ký Tự")
    private String passNew;
}
