package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * OrderDetails 實體類，對應資料庫中的 OrderDetails 表。
 */
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailsId; // 訂單明細ID (主鍵)

    @ManyToOne
    @JoinColumn(name = "ordersId", nullable = false)
    private Orders order; // 關聯到 Orders 表 (多對一)

    @ManyToOne
    @JoinColumn(name = "productsId", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Products product; // 關聯到 Products 表 (多對一)

    @Column(nullable = false)
    private Integer quantity; // 購買數量

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice; // 單價

    @Column(precision = 18, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO; // 折扣金額

    @Transient
    private BigDecimal subTotal; // 小計 (計算屬性，不存儲於資料庫)

    // Getter 和 Setter

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 計算小計 (不存儲於資料庫)。
     *
     * @return 小計
     */
    public BigDecimal getSubTotal() {
        return unitPrice.subtract(discount).multiply(BigDecimal.valueOf(quantity));
    }
}
