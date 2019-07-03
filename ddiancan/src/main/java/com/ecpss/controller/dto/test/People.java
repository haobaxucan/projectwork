package com.ecpss.controller.dto.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by xc on 2019/6/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {
    @NotEmpty(message = "姓名")
    private String name;
    private String addr;
    private Integer age;
    
}
