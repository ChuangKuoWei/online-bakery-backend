package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import java.util.List;
import java.util.Optional;

import com.back_end_project.back_end_project.database.ShoppingCart;

/**
 * ShoppingCartDAO 介面，用於操作 ShoppingCart 表的數據。
 */
public interface ShoppingCartDAO {

    /**
     * 保存或更新購物車項目。
     *
     * @param shoppingCart 要保存或更新的購物車物件
     * @return 保存或更新後的購物車物件
     */
    ShoppingCart save(ShoppingCart shoppingCart);

    /**
     * 根據購物車 ID 查詢購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     * @return 包含購物車資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<ShoppingCart> findById(Integer shoppingCartId);

    /**
     * 根據客戶 ID 查詢該客戶的購物車項目。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的購物車項目列表
     */
    List<ShoppingCart> findByCustomerId(Integer customerId);

    /**
     * 根據產品 ID 查詢該產品的購物車項目。
     *
     * @param productsId 產品 ID
     * @return 該產品的購物車項目列表
     */
    List<ShoppingCart> findByProductsId(Integer productsId);

    /**
     * 查詢所有購物車項目。
     *
     * @return 所有購物車項目的列表
     */
    List<ShoppingCart> findAll();

    /**
     * 根據購物車 ID 刪除購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     */
    void deleteById(Integer shoppingCartId);

    /**
     * 根據客戶 ID 刪除該客戶的所有購物車項目。
     *
     * @param customerId 客戶 ID
     */
    void deleteByCustomerId(Integer customerId);

    /**
     * 根據產品 ID 刪除該產品的所有購物車項目。
     *
     * @param productsId 產品 ID
     */
    void deleteByProductsId(Integer productsId);

    /**
     * 刪除所有購物車項目。
     */
    void deleteAll();
}
