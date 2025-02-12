package com.back_end_project.back_end_project.RepositoryDaoImplement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.ProductsDAO;
import com.back_end_project.back_end_project.database.Products;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * ProductsDAOImpl 類，實現 ProductsDAO 介面，用於操作 Products 表的數據。
 */
@Repository
public class ProductsDAOImpl implements ProductsDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    /**
     * 保存或更新產品資料。
     * 如果產品 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param product 要保存或更新的產品物件
     * @return 保存或更新後的產品物件
     */
    @Override
    public Products save(Products product) {
        if (product.getProductsId() == null) {
            entityManager.persist(product); // 新增產品
            return product;
        } else {
            return entityManager.merge(product); // 更新產品
        }
    }

    /**
     * 根據產品 ID 查詢產品資料。
     *
     * @param productsId 產品 ID
     * @return 包含產品資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Products> findById(Integer productsId) {
        Products product = entityManager.find(Products.class, productsId);
        return Optional.ofNullable(product);
    }

    /**
     * 根據產品名稱查詢產品資料。
     *
     * @param productName 產品名稱
     * @return 包含產品資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Products> findByName(String productName) {
        String jpql = "SELECT p FROM Products p WHERE p.productName = :productName";
        try {
            Products product = entityManager.createQuery(jpql, Products.class)
                    .setParameter("productName", productName)
                    .getSingleResult();
            return Optional.of(product);
        } catch (NoResultException e) {
            return Optional.empty(); // 如果查無結果，返回空的 Optional
        }
    }

    /**
     * 查詢所有產品資料。
     *
     * @return 產品資料的列表
     */
    @Override
    public List<Products> findAll() {
        String jpql = "SELECT p FROM Products p";
        return entityManager.createQuery(jpql, Products.class).getResultList();
    }

    /**
     * 根據分類 ID 查詢該分類下的所有產品。
     *
     * @param categoriesId 分類 ID
     * @return 符合條件的產品列表
     */
    @Override
    public List<Products> findByCategoryId(Integer categoriesId) {
        String jpql = "SELECT p FROM Products p WHERE p.category.categoriesId = :categoriesId";
        return entityManager.createQuery(jpql, Products.class)
                .setParameter("categoriesId", categoriesId)
                .getResultList();
    }

    /**
     * 根據價格範圍查詢產品資料。
     *
     * @param minPrice 最低價格
     * @param maxPrice 最高價格
     * @return 符合條件的產品列表
     */
    @Override
    public List<Products> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String jpql = "SELECT p FROM Products p WHERE p.price BETWEEN :minPrice AND :maxPrice";
        return entityManager.createQuery(jpql, Products.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    /**
     * 查詢所有特色產品資料。
     *
     * @return 特色產品的列表
     */
    @Override
    public List<Products> findFeaturedProducts() {
        String jpql = "SELECT p FROM Products p WHERE p.isFeatured = true";
        return entityManager.createQuery(jpql, Products.class).getResultList();
    }

    /**
     * 根據產品 ID 刪除產品資料。
     *
     * @param productsId 產品 ID
     */
    @Override
    public void deleteById(Integer productsId) {
        Products product = entityManager.find(Products.class, productsId);
        if (product != null) {
            entityManager.remove(product); // 刪除產品
        }
    }

    /**
     * 刪除所有產品資料。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM Products";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有產品資料
    }
}
