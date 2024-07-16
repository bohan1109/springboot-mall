package com.hank.springbootmall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class UserRegisterResponseDto {
    @JsonIgnore
    private Integer userId;
    private String email;
    private Date createdDate;
    private Date lastModifiedDate;

}