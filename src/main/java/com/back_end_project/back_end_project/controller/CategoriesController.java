package com.back_end_project.back_end_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_end_project.back_end_project.database.Categories;
import  com.back_end_project.back_end_project.service.CategoriesService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CategoriesController 類，用於處理與分類相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService; // 注入 CategoriesService，處理業務邏輯

    /**
     * 根據分類 ID 更新分類資料。
     *
     * @param categoriesId 分類 ID
     * @param category     包含更新數據的分類物件
     * @return 更新後的分類物件
     */
    @PutMapping("/{categoriesId}")
    public ResponseEntity<Categories> updateCategory(
            @PathVariable Integer categoriesId,
            @RequestBody Categories category) {

        // 查詢是否存在該分類
        Categories existingCategory = categoriesService.findCategoryById(categoriesId);
        if (existingCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果分類不存在，返回 404
        }

        // 更新分類資料
        existingCategory.setCategoriesName(category.getCategoriesName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setIsActive(category.getIsActive());
        existingCategory.setUpdatedDate(LocalDateTime.now()); // 更新時間為當前時間

        // 保存更新後的分類
        Categories updatedCategory = categoriesService.saveCategory(existingCategory);

        return ResponseEntity.ok(updatedCategory); // 返回更新後的分類
    }

    /**
     * 新增或更新分類資料。
     *
     * @param category 分類物件
     * @return 新增或更新後的分類物件
     */
    @PostMapping
    public ResponseEntity<Categories> saveCategory(@RequestBody Categories category) {
        Categories savedCategory = categoriesService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    /**
     * 根據分類 ID 查詢分類資料。
     *
     * @param categoriesId 分類 ID
     * @return 如果找到則返回分類物件，否則返回 404 錯誤。
     */
    @GetMapping("/{categoriesId}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Integer categoriesId) {
        Categories category = categoriesService.findCategoryById(categoriesId);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據分類名稱查詢分類資料。
     *
     * @param categoriesName 分類名稱
     * @return 如果找到則返回分類物件，否則返回 404 錯誤。
     */
    @GetMapping("/name/{categoriesName}")
    public ResponseEntity<Categories> getCategoryByName(@PathVariable String categoriesName) {
        Categories category = categoriesService.findCategoryByName(categoriesName);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 查詢所有分類資料。
     *
     * @return 所有分類的列表。
     */
    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> categories = categoriesService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * 根據是否啟用狀態查詢分類資料。
     *
     * @param isActive 是否啟用
     * @return 符合條件的分類列表。
     */
    @GetMapping("/status/{isActive}")
    public ResponseEntity<List<Categories>> getCategoriesByStatus(@PathVariable Boolean isActive) {
        List<Categories> categories = categoriesService.findCategoriesByIsActive(isActive);
        return ResponseEntity.ok(categories);
    }

    /**
     * 根據分類 ID 刪除分類資料。
     *
     * @param categoriesId 分類 ID
     * @return 如果刪除成功則返回 204，否則返回 404 錯誤。
     */
    @DeleteMapping("/{categoriesId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer categoriesId) {
        boolean isDeleted = categoriesService.deleteCategoryById(categoriesId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 刪除所有分類資料。
     *
     * @return 刪除成功返回 204。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllCategories() {
        categoriesService.deleteAllCategories();
        return ResponseEntity.noContent().build();
    }
}
