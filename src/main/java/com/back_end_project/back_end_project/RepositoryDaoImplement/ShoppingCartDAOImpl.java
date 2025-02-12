package com.back_end_project.back_end_project.RepositoryDaoImplement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.ShoppingCartDAO;
import com.back_end_project.back_end_project.database.ShoppingCart;

import java.util.List;
import java.util.Optional;

/**
 * ShoppingCartDAOImpl 類，實現 ShoppingCartDAO 介面，用於操作 ShoppingCart 表的數據。
 */
@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    /**
     * 保存或更新購物車項目。
     * 如果購物車 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param shoppingCart 要保存或更新的購物車物件
     * @return 保存或更新後的購物車物件
     */
    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        if (shoppingCart.getShoppingCartId() == null) {
            entityManager.persist(shoppingCart); // 新增購物車項目
            return shoppingCart;
        } else {
            return entityManager.merge(shoppingCart); // 更新購物車項目
        }
    }

    /**
     * 根據購物車 ID 查詢購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     * @return 包含購物車資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<ShoppingCart> findById(Integer shoppingCartId) {
        ShoppingCart shoppingCart = entityManager.find(ShoppingCart.class, shoppingCartId);
        return Optional.ofNullable(shoppingCart);
    }

    /**
     * 根據客戶 ID 查詢該客戶的購物車項目。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的購物車項目列表
     */
    @Override
    public List<ShoppingCart> findByCustomerId(Integer customerId) {
        String jpql = "SELECT sc FROM ShoppingCart sc WHERE sc.customer.customerId = :customerId";
        return entityManager.createQuery(jpql, ShoppingCart.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    /**
     * 根據產品 ID 查詢該產品的購物車項目。
     *
     * @param productsId 產品 ID
     * @return 該產品的購物車項目列表
     */
    @Override
    public List<ShoppingCart> findByProductsId(Integer productsId) {
        String jpql = "SELECT sc FROM ShoppingCart sc WHERE sc.product.productsId = :productsId";
        return entityManager.createQuery(jpql, ShoppingCart.class)
                .setParameter("productsId", productsId)
                .getResultList();
    }

    /**
     * 查詢所有購物車項目。
     *
     * @return 所有購物車項目的列表
     */
    @Override
    public List<ShoppingCart> findAll() {
        String jpql = "SELECT sc FROM ShoppingCart sc";
        return entityManager.createQuery(jpql, ShoppingCart.class).getResultList();
    }

    /**
     * 根據購物車 ID 刪除購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     */
    @Override
    public void deleteById(Integer shoppingCartId) {
        ShoppingCart shoppingCart = entityManager.find(ShoppingCart.class, shoppingCartId);
        if (shoppingCart != null) {
            entityManager.remove(shoppingCart); // 刪除購物車項目
        }
    }

    /**
     * 根據客戶 ID 刪除該客戶的所有購物車項目。
     *
     * @param customerId 客戶 ID
     */
    @Override
    public void deleteByCustomerId(Integer customerId) {
        String jpql = "DELETE FROM ShoppingCart sc WHERE sc.customer.customerId = :customerId";
        entityManager.createQuery(jpql)
                .setParameter("customerId", customerId)
                .executeUpdate(); // 刪除該客戶的所有購物車項目
    }

    /**
     * 根據產品 ID 刪除該產品的所有購物車項目。
     *
     * @param productsId 產品 ID
     */
    @Override
    public void deleteByProductsId(Integer productsId) {
        String jpql = "DELETE FROM ShoppingCart sc WHERE sc.product.productsId = :productsId";
        entityManager.createQuery(jpql)
                .setParameter("productsId", productsId)
                .executeUpdate(); // 刪除該產品的所有購物車項目
    }

    /**
     * 刪除所有購物車項目。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM ShoppingCart";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有購物車項目
    }
}
