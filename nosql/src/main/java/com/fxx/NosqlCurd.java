package com.fxx;

import com.fxx.sql.mongo.Shipper;

import java.util.List;

/**
 *
 * @author mgs
 * @param <T>
 */
public interface NosqlCurd<T> {

    /**
     * 新增
     * @param obj
     */
    void save(T obj);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 修改
     * @param obj
     */
    long update(Shipper obj);

    /**
     * 查询所有
     * @param
     */
    <T> List<T> queryAll();

    /**
     * 通过条件查询
     * @param
     */
    <T> List<T> queryByCondion(String id);

}
