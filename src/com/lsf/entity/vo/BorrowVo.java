package com.lsf.entity.vo;

import com.lsf.entity.Borrow;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 刘愿
 * @date 2020/11/17 13:53
 * @see [相关类/方法]
 * @since V1.00
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BorrowVo extends Borrow {
    private String userName;
    private String bookName;
}
