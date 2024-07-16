package com.hank.springbootmall.dto;


import lombok.Data;

import java.util.Date;

@Data
public class UserLoginResponseDto {

    private Integer userId;
    private String email;
    private Date createdDate;
    private Date lastModifiedDate;
    private String token;


}