package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author lemon
 * @date 2021/1/5 21:48
 */
public interface SellerDao {
    /**
     * 根据sid查询商家id
     * @param sid
     * @return
     */
    Seller findBySid(int sid);
}
