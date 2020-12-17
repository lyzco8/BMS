package com.lsf.entity;

import lombok.Data;

/**
 * @author 刘愿
 * @date 2020/11/13 14:06
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String gender;
    private Integer age;
    private Integer state;
    private String phone;
    private Integer integral;

}
