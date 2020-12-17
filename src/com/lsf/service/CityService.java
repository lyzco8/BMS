package com.lsf.service;

import com.lsf.entity.City;

import java.util.List;

/**
 * @author 刘愿
 * @date 2020/12/2 13:48
 * @see [相关类/方法]
 * @since V1.00
 */
public interface CityService {
    /**
     * 根据上级id查找行政区划
     * @param up
     * @return
     */
    List<City> getByUp(Integer up);

    /**
     * 根据英文名模糊查询
     * @param ename
     * @return
     */
    List<City> getByEname(String ename);
}
