package com.lsf.dao.impl;

import com.lsf.dao.CityDao;
import com.lsf.entity.City;
import com.ly.util.jdbc.JdbcTemplate;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/2 13:46
 * @see [相关类/方法]
 * @since V1.00
 */
public class CityDaoImpl implements CityDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public List<City> selectByUp(Integer up) {
        String sql = "select id,name from city where up=?";
        return template.queryList(sql, City.class, up);
    }

    @Override
    public List<City> selectByEname(String ename) {
        String sql = "select distinct name from city " +
                "where ename like concat(?,'%') " +
                "limit 5";
        return template.queryList(sql, City.class, ename);

    }
}
