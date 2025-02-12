package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.database.Orders;

import java.util.List;
import java.util.Optional;

/**
 * OrdersDAO 介面，用於操作 Orders 表的數據。
 */
@Repository
public interface OrdersDAO {

    /**
     * 保存或更新訂單資料。
     * 如果訂單 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param order 要保存或更新的訂單物件
     * @return 保存或更新後的訂單物件
     */
    Orders save(Orders order);

    /**
     * 根據訂單 ID 查詢訂單資料。
     *
     * @param ordersId 訂單 ID
     * @return 包含訂單資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Orders> findById(Integer ordersId);

    /**
     * 查詢所有訂單資料。
     *
     * @return 訂單資料的列表
     */
    List<Orders> findAll();

    /**
     * 根據客戶 ID 查詢該客戶的所有訂單。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的訂單列表
     */
    List<Orders> findByCustomerId(Integer customerId);

    /**
     * 根據訂單是否取消的狀態查詢訂單。
     *
     * @param isCancelled 是否取消
     * @return 符合條件的訂單列表
     */
    List<Orders> findByIsCancelled(Boolean isCancelled);

    /**
     * 根據訂單 ID 刪除訂單資料。
     *
     * @param ordersId 訂單 ID
     */
    void deleteById(Integer ordersId);

    /**
     * 刪除所有訂單資料。
     */
    void deleteAll();
}
