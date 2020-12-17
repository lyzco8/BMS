package com.lsf.dao;

import com.lsf.entity.Penalty;

/**
 * @author 刘愿
 * @date 2020/11/17 13:48
 * @see [相关类/方法]
 * @since V1.00
 */
public interface PenaltyDao {
    /**
     * 添加罚金记录
     * @param penalty
     * @return
     */
    int insert(Penalty penalty);
}
