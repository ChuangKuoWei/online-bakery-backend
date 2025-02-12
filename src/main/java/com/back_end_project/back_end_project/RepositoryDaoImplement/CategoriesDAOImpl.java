package com.back_end_project.back_end_project.RepositoryDaoImplement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.CategoriesDAO;
import com.back_end_project.back_end_project.database.Categories;

import java.util.List;
import java.util.Optional;

/**
 * CategoriesDAOImpl 類，實現 CategoriesDAO 介面，用於操作 Categories 表的數據。
 */
@Repository
public class CategoriesDAOImpl implements CategoriesDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    /**
     * 保存或更新分類資料。
     * 如果分類 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param category 要保存或更新的分類物件
     * @return 保存或更新後的分類物件
     */
    @Override
    public Categories save(Categories category) {
        if (category.getCategoriesId() == null) {
            entityManager.persist(category); // 新增分類
            return category;
        } else {
            return entityManager.merge(category); // 更新分類
        }
    }

    /**
     * 根據分類 ID 查詢分類資料。
     *
     * @param categoriesId 分類 ID
     * @return 包含分類資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Categories> findById(Integer categoriesId) {
        Categories category = entityManager.find(Categories.class, categoriesId);
        return Optional.ofNullable(category);
    }

    /**
     * 根據分類名稱查詢分類資料。
     *
     * @param categoriesName 分類名稱
     * @return 包含分類資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Categories> findByName(String categoriesName) {
        String jpql = "SELECT c FROM Categories c WHERE c.categoriesName = :categoriesName";
        try {
            Categories category = entityManager.createQuery(jpql, Categories.class)
                    .setParameter("categoriesName", categoriesName)
                    .getSingleResult();
            return Optional.of(category);
        } catch (NoResultException e) {
            return Optional.empty(); // 如果查無結果，返回空的 Optional
        }
    }

    /**
     * 查詢所有分類資料。
     *
     * @return 分類資料的列表
     */
    @Override
    public List<Categories> findAll() {
        String jpql = "SELECT c FROM Categories c";
        return entityManager.createQuery(jpql, Categories.class).getResultList();
    }

    /**
     * 根據是否啟用狀態查詢分類資料。
     *
     * @param isActive 是否啟用
     * @return 符合條件的分類列表
     */
    @Override
    public List<Categories> findByIsActive(Boolean isActive) {
        String jpql = "SELECT c FROM Categories c WHERE c.isActive = :isActive";
        return entityManager.createQuery(jpql, Categories.class)
                .setParameter("isActive", isActive)
                .getResultList();
    }

    /**
     * 根據分類 ID 刪除分類資料。
     *
     * @param categoriesId 分類 ID
     */
    @Override
    public void deleteById(Integer categoriesId) {
        Categories category = entityManager.find(Categories.class, categoriesId);
        if (category != null) {
            entityManager.remove(category); // 刪除分類
        }
    }

    /**
     * 刪除所有分類資料。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM Categories";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有分類資料
    }
}
