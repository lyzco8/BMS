package com.lsf.entity;

import lombok.Data;

/**
 * @author 刘愿
 * @date 2020/11/19 14:32
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Order {
    private Integer oid;
    private Integer uid;
    private String address;
    private Float summary;
}
