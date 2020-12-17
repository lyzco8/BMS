package com.lsf.entity;

import lombok.Data;

/**
 * @author 刘愿
 * @date 2020/11/26 14:30
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Comment {
    private Integer id;
    private Integer uid;
    private Integer bid;
    private String title;
    private String content;
}
