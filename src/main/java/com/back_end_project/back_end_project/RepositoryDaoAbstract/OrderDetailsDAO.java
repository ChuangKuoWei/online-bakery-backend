package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO;
import com.back_end_project.back_end_project.database.OrderDetails;

import java.util.List;
import java.util.Optional;

/**
 * OrderDetailsDAO 介面，用於操作 OrderDetails 表的數據。
 */
@Repository
public interface OrderDetailsDAO {

    @Query("SELECT new com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO( " +
            "od.orderDetailsId, p.productName, od.quantity, od.unitPrice) " +
            "FROM OrderDetails od JOIN od.product p WHERE od.order.ordersId = :orderId")
    List<OrderDetailDTO> findOrderDetailsByOrderId(@Param("orderId") Integer orderId);

    /**
     * 保存或更新訂單明細資料。
     * 如果訂單明細 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param orderDetails 要保存或更新的訂單明細物件
     * @return 保存或更新後的訂單明細物件
     */
    OrderDetails save(OrderDetails orderDetails);

    /**
     * 根據訂單明細 ID 查詢訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     * @return 包含訂單明細資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<OrderDetails> findById(Integer orderDetailsId);

    /**
     * 查詢所有訂單明細資料。
     *
     * @return 訂單明細資料的列表
     */
    List<OrderDetails> findAll();

    /**
     * 根據訂單 ID 查詢該訂單的所有明細。
     *
     * @param ordersId 訂單 ID
     * @return 該訂單的明細列表
     */
    // List<OrderDetails> findByOrderId(Integer ordersId);
    List<OrderDetailDTO> findByOrderId(Integer ordersId);

    /**
     * 根據產品 ID 查詢該產品的所有訂單明細。
     *
     * @param productsId 產品 ID
     * @return 該產品的訂單明細列表
     */
    List<OrderDetails> findByProductsId(Integer productsId);

    /**
     * 根據訂單明細 ID 刪除訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     */
    void deleteById(Integer orderDetailsId);

    /**
     * 根據訂單 ID 刪除該訂單的所有明細資料。
     *
     * @param ordersId 訂單 ID
     */
    void deleteByOrderId(Integer ordersId);

    /**
     * 刪除所有訂單明細資料。
     */
    void deleteAll();
}
