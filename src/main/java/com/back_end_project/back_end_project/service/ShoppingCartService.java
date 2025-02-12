package com.back_end_project.back_end_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.ShoppingCartDAO;
import com.back_end_project.back_end_project.database.ShoppingCart;

import java.util.List;

/**
 * ShoppingCartService 類，用於處理與購物車相關的業務邏輯。
 */
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartDAO shoppingCartDAO; // 注入 ShoppingCartDAO，負責與資料庫交互

    /**
     * 保存或更新購物車項目。
     *
     * @param shoppingCart 購物車物件
     * @return 保存或更新後的購物車物件
     */
    @Transactional
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartDAO.save(shoppingCart);
    }

    /**
     * 根據購物車 ID 查詢購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     * @return 如果找到則返回購物車物件，否則返回 null。
     */
    public ShoppingCart findShoppingCartById(Integer shoppingCartId) {
        return shoppingCartDAO.findById(shoppingCartId).orElse(null);
    }

    /**
     * 根據客戶 ID 查詢該客戶的購物車項目。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的購物車項目列表
     */
    public List<ShoppingCart> findShoppingCartsByCustomerId(Integer customerId) {
        return shoppingCartDAO.findByCustomerId(customerId);
    }

    /**
     * 根據產品 ID 查詢該產品的購物車項目。
     *
     * @param productsId 產品 ID
     * @return 該產品的購物車項目列表
     */
    public List<ShoppingCart> findShoppingCartsByProductsId(Integer productsId) {
        return shoppingCartDAO.findByProductsId(productsId);
    }

    /**
     * 查詢所有購物車項目。
     *
     * @return 所有購物車項目的列表
     */
    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartDAO.findAll();
    }

    /**
     * 根據購物車 ID 刪除購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deleteShoppingCartById(Integer shoppingCartId) {
        if (shoppingCartDAO.findById(shoppingCartId).isPresent()) {
            shoppingCartDAO.deleteById(shoppingCartId);
            return true;
        }
        return false;
    }

    /**
     * 根據客戶 ID 刪除該客戶的所有購物車項目。
     *
     * @param customerId 客戶 ID
     */
    @Transactional
    public void deleteShoppingCartsByCustomerId(Integer customerId) {
        shoppingCartDAO.deleteByCustomerId(customerId);
    }

    /**
     * 根據產品 ID 刪除該產品的所有購物車項目。
     *
     * @param productsId 產品 ID
     */
    @Transactional
    public void deleteShoppingCartsByProductsId(Integer productsId) {
        shoppingCartDAO.deleteByProductsId(productsId);
    }

    /**
     * 刪除所有購物車項目。
     */
    @Transactional
    public void deleteAllShoppingCarts() {
        shoppingCartDAO.deleteAll();
    }
}
