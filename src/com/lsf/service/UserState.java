package com.lsf.service;

/**
 * @author 刘愿
 * @date 2020/11/19 17:09
 * @see [相关类/方法]
 * @since V1.00
 */
public interface UserState {
    Integer DELETED=0;
    Integer NORMAL=1;
    Integer ADMIN=2;
    Integer SUPER_ADMIN=3;
}
