package com.lsf.service.impl;

import com.lsf.dao.CityDao;
import com.lsf.dao.impl.CityDaoImpl;
import com.lsf.entity.City;
import com.lsf.service.CityService;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/2 13:49
 * @see [相关类/方法]
 * @since V1.00
 */
public class CityServiceImpl implements CityService {
    private CityDao dao = new CityDaoImpl();

    @Override
    public List<City> getByUp(Integer up) {
        return dao.selectByUp(up);
    }

    @Override
    public List<City> getByEname(String ename) {
        return dao.selectByEname(ename);
    }
}
