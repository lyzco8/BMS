package com.lsf.entity;

import lombok.Data;

/**
 * 用于组织AJAX数据的类型
 * * data：数据
 * * errorCode：错误码，0表示OK
 * * message：提示信息
 *
 * @author 刘愿
 * @date 2020/12/2 13:55
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
public class JsonData<T> {
    private T data;
    private Integer errorCode = 0;
    private String message;
}
