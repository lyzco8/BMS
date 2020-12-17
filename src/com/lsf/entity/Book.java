package com.lsf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 刘愿
 * @date 2020/11/13 14:06
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String pubComp;
    private Date pubDate;
    private Integer count;
    private Float price;

}
