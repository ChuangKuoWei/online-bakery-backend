package com.back_end_project.back_end_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.CategoriesDAO;
import com.back_end_project.back_end_project.database.Categories;

import java.util.List;
import java.util.Optional;

/**
 * CategoriesService 類，用於處理與分類相關的業務邏輯。
 */
@Service
public class CategoriesService {

    @Autowired
    private CategoriesDAO categoriesDAO; // 注入 CategoriesDAO，負責與資料庫交互

    /**
     * 保存或更新分類資料。
     *
     * @param category 分類物件
     * @return 保存或更新後的分類物件
     */
    @Transactional
    public Categories saveCategory(Categories category) {
        return categoriesDAO.save(category);
    }

    /**
     * 根據分類 ID 查詢分類資料。
     *
     * @param categoriesId 分類 ID
     * @return 如果找到則返回分類物件，否則返回 null。
     */
    public Categories findCategoryById(Integer categoriesId) {
        return categoriesDAO.findById(categoriesId).orElse(null);
    }

    /**
     * 根據分類名稱查詢分類資料。
     *
     * @param categoriesName 分類名稱
     * @return 如果找到則返回分類物件，否則返回 null。
     */
    public Categories findCategoryByName(String categoriesName) {
        return categoriesDAO.findByName(categoriesName).orElse(null);
    }

    /**
     * 查詢所有分類資料。
     *
     * @return 分類資料的列表
     */
    public List<Categories> findAllCategories() {
        return categoriesDAO.findAll();
    }

    /**
     * 根據是否啟用狀態查詢分類資料。
     *
     * @param isActive 是否啟用
     * @return 符合條件的分類列表
     */
    public List<Categories> findCategoriesByIsActive(Boolean isActive) {
        return categoriesDAO.findByIsActive(isActive);
    }

    /**
     * 根據分類 ID 刪除分類資料。
     *
     * @param categoriesId 分類 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deleteCategoryById(Integer categoriesId) {
        Optional<Categories> category = categoriesDAO.findById(categoriesId);
        if (category.isPresent()) {
            categoriesDAO.deleteById(categoriesId);
            return true;
        }
        return false;
    }

    /**
     * 刪除所有分類資料。
     */
    @Transactional
    public void deleteAllCategories() {
        categoriesDAO.deleteAll();
    }
}
