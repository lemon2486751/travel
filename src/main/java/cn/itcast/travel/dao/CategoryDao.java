package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/1 16:58
 */
public interface CategoryDao {
    /**
     * 查询分类列表
     * @return
     */
    List<Category> findAll();
}
