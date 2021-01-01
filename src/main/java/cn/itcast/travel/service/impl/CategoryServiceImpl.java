package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lemon
 * @date 2021/1/1 16:57
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        Jedis jedis = JedisUtil.getJedis();
        List<Category> categoryList = new ArrayList<>();
        //查询redis中是否有数据
        Set<Tuple> categorys = jedis.zrangeByScoreWithScores("category", 0, -1);
        if (categorys == null || categorys.size() == 0) {
            //没有先查询数据库
            categoryList = dao.findAll();
            //将数据存入redis
            for (Category category : categoryList) {
                jedis.zadd("category", category.getCid(), category.getCname());
            }
        } else {
            Category cg = new Category();
            for (Tuple tuple : categorys) {
                cg.setCid((int) tuple.getScore());
                cg.setCname(tuple.getElement());
                categoryList.add(cg);
            }
        }
        return categoryList;
    }
}
