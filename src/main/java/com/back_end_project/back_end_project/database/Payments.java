package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payments 實體類，對應資料庫中的 Payments 表。
 */
@Entity
@Table(name = "Payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentsId; // 交易ID (主鍵)

    @OneToOne
    @JoinColumn(name = "ordersId", nullable = false, unique = true)
    private Orders order; // 關聯到 Orders 表 (一對一)

    @Column(length = 50)
    private String paymentMethod; // 支付方式

    @Column
    private LocalDateTime transactionDate = LocalDateTime.now(); // 交易時間

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal transactionAmount; // 交易金額

    @Column(nullable = false, length = 50)
    private String transactionStatus; // 交易狀態

    @Column(columnDefinition = "TEXT")
    private String gatewayResponse; // 支付網關回應

    // Getter 和 Setter

    public Integer getPaymentsId() {
        return paymentsId;
    }

    public void setPaymentsId(Integer paymentsId) {
        this.paymentsId = paymentsId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getGatewayResponse() {
        return gatewayResponse;
    }

    public void setGatewayResponse(String gatewayResponse) {
        this.gatewayResponse = gatewayResponse;
    }
}
