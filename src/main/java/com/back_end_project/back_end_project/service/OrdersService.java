package com.back_end_project.back_end_project.service;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.OrdersDAO;
import com.back_end_project.back_end_project.RepositoryDaoAbstract.OrdersRepository;
import com.back_end_project.back_end_project.database.Orders;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * OrdersService 類，用於處理與訂單相關的業務邏輯。
 */
@Service
public class OrdersService {

    @Autowired
    private OrdersDAO ordersDAO; // 注入 OrdersDAO，負責與資料庫交互

    @Autowired
    private OrdersRepository ordersRepository;

    /**
     * 保存或更新訂單資料。
     *
     * @param order 訂單物件
     * @return 保存或更新後的訂單物件
     */
    @Transactional
    public Orders saveOrder(Orders order) {
        return ordersDAO.save(order);
    }

    /**
     * 根據訂單 ID 查詢訂單資料。
     *
     * @param ordersId 訂單 ID
     * @return 如果找到則返回訂單物件，否則返回 null。
     */
    public Orders findOrderById(Integer ordersId) {
        Optional<Orders> optionalOrder = ordersDAO.findById(ordersId);
        return optionalOrder.orElse(null);
    }

    /**
     * 查詢所有訂單資料。
     *
     * @return 訂單資料的列表
     */
    public List<Orders> findAllOrders() {
        return ordersDAO.findAll();
    }

    /**
     * 根據客戶 ID 查詢該客戶的所有訂單。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的訂單列表
     */
    public List<Orders> findOrdersByCustomerId(Integer customerId) {
        return ordersDAO.findByCustomerId(customerId);
    }

    /**
     * 根據訂單是否取消的狀態查詢訂單。
     *
     * @param isCancelled 是否取消
     * @return 符合條件的訂單列表
     */
    public List<Orders> findOrdersByIsCancelled(Boolean isCancelled) {
        return ordersDAO.findByIsCancelled(isCancelled);
    }

    /**
     * 根據訂單 ID 刪除訂單資料。
     *
     * @param ordersId 訂單 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deleteOrderById(Integer ordersId) {
        Optional<Orders> optionalOrder = ordersDAO.findById(ordersId);
        if (optionalOrder.isPresent()) {
            ordersDAO.deleteById(ordersId);
            return true;
        }
        return false;
    }

    /**
     * 刪除所有訂單資料。
     */
    @Transactional
    public void deleteAllOrders() {
        ordersDAO.deleteAll();
    }

    // 查詢最後一筆訂單
    public Orders getLastOrder() {
        return ordersRepository.findTopByOrderByOrdersIdDesc();
    }
}
