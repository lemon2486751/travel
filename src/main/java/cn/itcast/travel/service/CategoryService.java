package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/1 16:57
 */
public interface CategoryService {

    /**
     * 查询分类列表
     * @return
     */
    List<Category> findAll();
}
