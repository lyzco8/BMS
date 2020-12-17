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
public class Borrow {
    private Integer rid;
    private Integer bid;
    private Date lendDate;
    private Date willDate;
    private Date returnDate;

}
