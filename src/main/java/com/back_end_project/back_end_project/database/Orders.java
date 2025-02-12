package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Orders 實體類，對應資料庫中的 Orders 表。
 */
@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersId; // 訂單ID (主鍵)

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer; // 關聯到 Customer 表 (多對一)

    @Column
    private LocalDateTime orderDate = LocalDateTime.now(); // 訂單日期

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount; // 訂單總金額

    @Column(nullable = false, length = 50)
    private String paymentStatus; // 付款狀態 是否已付款

    @Column(nullable = false, length = 50)
    private String shippingStatus; // 配送狀態

    @Column(length = 255)
    private String shippingAddress; // 配送地址

    @Column(length = 255)
    private String billingAddress; // 帳單地址

    @Column(length = 50)
    private String paymentMethod; // 支付方式 信用卡

    @Column(length = 100)
    private String trackingNumber; // 物流追蹤號碼

    @Column
    private LocalDateTime estimatedDeliveryDate; // 預計到貨時間

    @Column(columnDefinition = "TEXT")
    private String notes; // 訂單備註

    @Column
    private Boolean isCancelled = false; // 訂單是否取消

    @Column
    private LocalDateTime cancellationDate; // 取消日期

    @Column
    private LocalDateTime updatedDate = LocalDateTime.now(); // 更新時間

    // Getter 和 Setter

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDateTime cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
