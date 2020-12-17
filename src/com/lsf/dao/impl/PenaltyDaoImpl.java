package com.lsf.dao.impl;

import com.lsf.dao.PenaltyDao;
import com.lsf.entity.Penalty;
import com.ly.util.jdbc.JdbcTemplate;

/**
 * @author 刘愿
 * @date 2020/11/17 13:49
 * @see [相关类/方法]
 * @since V1.00
 */
public class PenaltyDaoImpl implements PenaltyDao {
    private JdbcTemplate template=new JdbcTemplate();
    @Override
    public int insert(Penalty penalty) {
        String sql="insert into penalty values(?,?,now(),?,?)";
        return template.update(sql,false,
                penalty.getUserId(),
                penalty.getBookId(),
                penalty.getType(),
                penalty.getAmount());
    }
}
