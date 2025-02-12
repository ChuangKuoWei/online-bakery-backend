package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Categories 實體類，對應資料庫中的 Categories 表。
 */
@Entity
@Table(name = "Categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoriesId; // 分類ID (主鍵)

    @Column(nullable = false, length = 255)
    private String categoriesName; // 分類名稱

    @Column(length = 500)
    private String description; // 分類描述

    @Column
    private LocalDateTime createdDate = LocalDateTime.now(); // 創建時間

    @Column
    private LocalDateTime updatedDate = LocalDateTime.now(); // 更新時間

    @Column
    private Boolean isActive = true; // 是否啟用

    // Getter 和 Setter

    /**
     * 獲取分類ID。
     * 
     * @return 分類ID
     */
    public Integer getCategoriesId() {
        return categoriesId;
    }

    /**
     * 設置分類ID。
     * 
     * @param categoriesId 分類ID
     */
    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    /**
     * 獲取分類名稱。
     * 
     * @return 分類名稱
     */
    public String getCategoriesName() {
        return categoriesName;
    }

    /**
     * 設置分類名稱。
     * 
     * @param categoriesName 分類名稱
     */
    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    /**
     * 獲取分類描述。
     * 
     * @return 分類描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 設置分類描述。
     * 
     * @param description 分類描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 獲取創建時間。
     * 
     * @return 創建時間
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * 設置創建時間。
     * 
     * @param createdDate 創建時間
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 獲取更新時間。
     * 
     * @return 更新時間
     */
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    /**
     * 設置更新時間。
     * 
     * @param updatedDate 更新時間
     */
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * 獲取是否啟用狀態。
     * 
     * @return 是否啟用
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 設置是否啟用狀態。
     * 
     * @param isActive 是否啟用
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
