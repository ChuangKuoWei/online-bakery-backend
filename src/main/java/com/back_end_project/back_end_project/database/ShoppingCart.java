package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * ShoppingCart 實體類，對應資料庫中的 ShoppingCart 表。
 */
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartId; // 購物車ID (主鍵)

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer; // 關聯到 Customer 表 (多對一)

    @ManyToOne
    @JoinColumn(name = "productsId", nullable = false)
    private Products product; // 關聯到 Products 表 (多對一)

    @Column(nullable = false)
    private Integer quantity = 1; // 購買數量

    @Column
    private LocalDateTime dateAdded = LocalDateTime.now(); // 加入時間

    @Column
    private Boolean accountPaid = false; // 是否已經付款結帳,false代表未付款

    @Column(length = 50, nullable = true)
    private String notes; // 備註

    // Getter 和 Setter

    /**
     * 獲取購物車ID。
     * 
     * @return 購物車ID
     */
    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    /**
     * 設置購物車ID。
     * 
     * @param shoppingCartId 購物車ID
     */
    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    /**
     * 獲取關聯的客戶。
     * 
     * @return 關聯的客戶
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 設置關聯的客戶。
     * 
     * @param customer 關聯的客戶
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * 獲取關聯的產品。
     * 
     * @return 關聯的產品
     */
    public Products getProduct() {
        return product;
    }

    /**
     * 設置關聯的產品。
     * 
     * @param product 關聯的產品
     */
    public void setProduct(Products product) {
        this.product = product;
    }

    /**
     * 獲取購買數量。
     * 
     * @return 購買數量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * 設置購買數量。
     * 
     * @param quantity 購買數量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 獲取加入時間。
     * 
     * @return 加入時間
     */
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    /**
     * 設置加入時間。
     * 
     * @param dateAdded 加入時間
     */
    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean getAccountPaid() {
        return accountPaid;
    }

    public void setAccountPaid(Boolean accountPaid) {
        this.accountPaid = accountPaid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
