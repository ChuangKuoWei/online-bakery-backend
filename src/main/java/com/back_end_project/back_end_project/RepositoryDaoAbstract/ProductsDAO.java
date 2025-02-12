package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.back_end_project.back_end_project.database.Products;

/**
 * ProductsDAO 介面，用於操作 Products 表的數據。
 */
public interface ProductsDAO {

    /**
     * 保存或更新產品資料。
     *
     * @param product 要保存或更新的產品物件
     * @return 保存或更新後的產品物件
     */
    Products save(Products product);

    /**
     * 根據產品 ID 查詢產品資料。
     *
     * @param productsId 產品 ID
     * @return 包含產品資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Products> findById(Integer productsId);

    /**
     * 根據產品名稱查詢產品資料。
     *
     * @param productName 產品名稱
     * @return 包含產品資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Products> findByName(String productName);

    /**
     * 查詢所有產品資料。
     *
     * @return 產品資料的列表
     */
    List<Products> findAll();

    /**
     * 根據分類 ID 查詢該分類下的所有產品。
     *
     * @param categoriesId 分類 ID
     * @return 符合條件的產品列表
     */
    List<Products> findByCategoryId(Integer categoriesId);

    /**
     * 根據價格範圍查詢產品資料。
     *
     * @param minPrice 最低價格
     * @param maxPrice 最高價格
     * @return 符合條件的產品列表
     */
    List<Products> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * 查詢所有特色產品資料。
     *
     * @return 特色產品的列表
     */
    List<Products> findFeaturedProducts();

    /**
     * 根據產品 ID 刪除產品資料。
     *
     * @param productsId 產品 ID
     */
    void deleteById(Integer productsId);

    /**
     * 刪除所有產品資料。
     */
    void deleteAll();
}
