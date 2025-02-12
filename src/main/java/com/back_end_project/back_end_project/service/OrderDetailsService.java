package com.back_end_project.back_end_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO;
import com.back_end_project.back_end_project.RepositoryDaoAbstract.OrderDetailsDAO;
import com.back_end_project.back_end_project.database.OrderDetails;

import java.util.List;
import java.util.Optional;

/**
 * OrderDetailsService 類，用於處理與訂單明細相關的業務邏輯。
 */
@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsDAO orderDetailsDAO; // 注入 OrderDetailsDAO，負責與資料庫交互

    /**
     * 保存或更新訂單明細資料。
     *
     * @param orderDetails 訂單明細物件
     * @return 保存或更新後的訂單明細物件
     */
    @Transactional
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsDAO.save(orderDetails);
    }

    /**
     * 根據訂單明細 ID 查詢訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     * @return 如果找到則返回訂單明細物件，否則返回 null。
     */
    public OrderDetails findOrderDetailsById(Integer orderDetailsId) {
        Optional<OrderDetails> optionalOrderDetails = orderDetailsDAO.findById(orderDetailsId);
        return optionalOrderDetails.orElse(null);
    }

    /**
     * 查詢所有訂單明細資料。
     *
     * @return 訂單明細資料的列表
     */
    public List<OrderDetails> findAllOrderDetails() {
        return orderDetailsDAO.findAll();
    }

    /**
     * 根據訂單 ID 查詢該訂單的所有明細。
     *
     * @param ordersId 訂單 ID
     * @return 該訂單的明細列表
     */

    public List<OrderDetailDTO> findOrderDetailsByOrderId(Integer ordersId) {
        return orderDetailsDAO.findByOrderId(ordersId);
    }

    /**
     * 根據產品 ID 查詢該產品的所有訂單明細。
     *
     * @param productsId 產品 ID
     * @return 該產品的訂單明細列表
     */
    public List<OrderDetails> findOrderDetailsByProductsId(Integer productsId) {
        return orderDetailsDAO.findByProductsId(productsId);
    }

    /**
     * 根據訂單明細 ID 刪除訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deleteOrderDetailsById(Integer orderDetailsId) {
        Optional<OrderDetails> optionalOrderDetails = orderDetailsDAO.findById(orderDetailsId);
        if (optionalOrderDetails.isPresent()) {
            orderDetailsDAO.deleteById(orderDetailsId);
            return true;
        }
        return false;
    }

    /**
     * 根據訂單 ID 刪除該訂單的所有明細資料。
     *
     * @param ordersId 訂單 ID
     */
    @Transactional
    public void deleteOrderDetailsByOrderId(Integer ordersId) {
        orderDetailsDAO.deleteByOrderId(ordersId);
    }

    /**
     * 刪除所有訂單明細資料。
     */
    @Transactional
    public void deleteAllOrderDetails() {
        orderDetailsDAO.deleteAll();
    }
}
