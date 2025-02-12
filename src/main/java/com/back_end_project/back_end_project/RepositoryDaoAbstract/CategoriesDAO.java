package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import java.util.List;
import java.util.Optional;

import com.back_end_project.back_end_project.database.Categories;

/**
 * CategoriesDAO 介面，用於操作 Categories 表的數據。
 */
public interface CategoriesDAO {

    /**
     * 保存或更新分類資料。
     *
     * @param category 要保存或更新的分類物件
     * @return 保存或更新後的分類物件
     */
    Categories save(Categories category);

    /**
     * 根據分類 ID 查詢分類資料。
     *
     * @param categoriesId 分類 ID
     * @return 包含分類資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Categories> findById(Integer categoriesId);

    /**
     * 根據分類名稱查詢分類資料。
     *
     * @param categoriesName 分類名稱
     * @return 包含分類資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Categories> findByName(String categoriesName);

    /**
     * 查詢所有分類資料。
     *
     * @return 分類資料的列表
     */
    List<Categories> findAll();

    /**
     * 根據是否啟用狀態查詢分類資料。
     *
     * @param isActive 是否啟用
     * @return 符合條件的分類列表
     */
    List<Categories> findByIsActive(Boolean isActive);

    /**
     * 根據分類 ID 刪除分類資料。
     *
     * @param categoriesId 分類 ID
     */
    void deleteById(Integer categoriesId);

    /**
     * 刪除所有分類資料。
     */
    void deleteAll();
}
