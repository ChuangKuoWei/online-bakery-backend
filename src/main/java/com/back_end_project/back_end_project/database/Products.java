package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Products 實體類，對應資料庫中的 Products 表。
 */
@Entity
@Table(name = "Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productsId; // 產品ID (主鍵)

    @ManyToOne
    @JoinColumn(name = "categoriesId", nullable = false)
    private Categories category; // 所屬分類 (多對一)

    @Column(nullable = false, length = 255)
    private String productName; // 產品名稱

    @Column(unique = true, nullable = false, length = 50)
    private String sku; // 產品編號 (唯一)

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price; // 價格

    @Column
    private Float discountRate = 0.0f; // 折扣率

    @Column
    private Integer quantityInStock = 0; // 庫存數量

    @Column
    private LocalDateTime lastRestocked; // 最近補貨時間

    @Column
    private Integer thresholdLevel = 10; // 補貨警戒值

    @Column(length = 255)
    private String warehouseLocation; // 庫存所在位置

    @Column(columnDefinition = "TEXT")
    private String description; // 產品描述

    @Column
    private Boolean isFeatured = false; // 是否為特色商品

    @Column
    private LocalDateTime createdDate = LocalDateTime.now(); // 創建時間

    @Column
    private LocalDateTime updatedDate = LocalDateTime.now(); // 更新時間

    @Column(columnDefinition = "VARBINARY(MAX)")
    private byte[] image; // 產品圖片

    @Column
    private Float rating = 0.0f; // 評分

    @Column
    private Integer reviewsCount = 0; // 評價數量

    // Getter 和 Setter

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Float discountRate) {
        this.discountRate = discountRate;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public LocalDateTime getLastRestocked() {
        return lastRestocked;
    }

    public void setLastRestocked(LocalDateTime lastRestocked) {
        this.lastRestocked = lastRestocked;
    }

    public Integer getThresholdLevel() {
        return thresholdLevel;
    }

    public void setThresholdLevel(Integer thresholdLevel) {
        this.thresholdLevel = thresholdLevel;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }
}
