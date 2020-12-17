package com.lsf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 刘愿
 * @date 2020/11/17 13:50
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Penalty {
    private Integer userId;
    private Integer bookId;
    private Date date;
    private Integer type;
    private Float amount;
}
