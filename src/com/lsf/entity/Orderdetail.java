package com.lsf.entity;

import lombok.Data;

/**
 * @author 刘愿
 * @date 2020/11/19 14:43
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class Orderdetail {
    private Integer odid;
    private Integer oid;
    private Integer bid;
    private Integer quantity;
    private Float price;

}
