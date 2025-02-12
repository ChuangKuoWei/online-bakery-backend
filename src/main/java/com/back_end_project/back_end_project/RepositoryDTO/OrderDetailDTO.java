package com.back_end_project.back_end_project.RepositoryDTO;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private Integer orderDetailId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderDetailDTO(Integer orderDetailId, String productName, Integer quantity, BigDecimal unitPrice) {
        this.orderDetailId = orderDetailId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
