package com.back_end_project.back_end_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.ProductsDAO;
import com.back_end_project.back_end_project.database.Products;

import java.math.BigDecimal;
import java.util.List;

/**
 * ProductsService 類，用於處理與產品相關的業務邏輯。
 */
@Service
public class ProductsService {

    @Autowired
    private ProductsDAO productsDAO; // 注入 ProductsDAO，負責與資料庫交互

    /**
     * 保存或更新產品資料。
     *
     * @param product 產品物件
     * @return 保存或更新後的產品物件
     */
    @Transactional
    public Products saveProduct(Products product) {
        return productsDAO.save(product);
    }

    /**
     * 根據產品 ID 查詢產品資料。
     *
     * @param productsId 產品 ID
     * @return 如果找到則返回產品物件，否則返回 null。
     */
    public Products findProductById(Integer productsId) {
        return productsDAO.findById(productsId).orElse(null);
    }

    /**
     * 根據產品名稱查詢產品資料。
     *
     * @param productName 產品名稱
     * @return 如果找到則返回產品物件，否則返回 null。
     */
    public Products findProductByName(String productName) {
        return productsDAO.findByName(productName).orElse(null);
    }

    /**
     * 查詢所有產品資料。
     *
     * @return 產品資料的列表
     */
    public List<Products> findAllProducts() {
        return productsDAO.findAll();
    }

    /**
     * 根據分類 ID 查詢該分類下的所有產品。
     *
     * @param categoriesId 分類 ID
     * @return 符合條件的產品列表
     */
    public List<Products> findProductsByCategoryId(Integer categoriesId) {
        return productsDAO.findByCategoryId(categoriesId);
    }

    /**
     * 根據價格範圍查詢產品資料。
     *
     * @param minPrice 最低價格
     * @param maxPrice 最高價格
     * @return 符合條件的產品列表
     */
    public List<Products> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productsDAO.findByPriceRange(minPrice, maxPrice);
    }

    /**
     * 查詢所有特色產品資料。
     *
     * @return 特色產品的列表
     */
    public List<Products> findFeaturedProducts() {
        return productsDAO.findFeaturedProducts();
    }

    /**
     * 根據產品 ID 刪除產品資料。
     *
     * @param productsId 產品 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deleteProductById(Integer productsId) {
        if (productsDAO.findById(productsId).isPresent()) {
            productsDAO.deleteById(productsId);
            return true;
        }
        return false;
    }

    /**
     * 刪除所有產品資料。
     */
    @Transactional
    public void deleteAllProducts() {
        productsDAO.deleteAll();
    }
}
