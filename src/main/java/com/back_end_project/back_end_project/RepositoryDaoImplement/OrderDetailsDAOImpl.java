package com.back_end_project.back_end_project.RepositoryDaoImplement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO;
import com.back_end_project.back_end_project.RepositoryDaoAbstract.OrderDetailsDAO;
import com.back_end_project.back_end_project.database.OrderDetails;

import java.util.List;
import java.util.Optional;

/**
 * OrderDetailsDAOImpl 類，實現 OrderDetailsDAO 介面，用於操作 OrderDetails 表的數據。
 */
@Repository
public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    @Override
    public List<OrderDetailDTO> findOrderDetailsByOrderId(Integer orderId) {
        String jpql = "SELECT new com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO( " +
                "od.orderDetailsId, p.productName, od.quantity, od.unitPrice) " +
                "FROM OrderDetails od JOIN od.product p WHERE od.order.ordersId = :orderId";

        TypedQuery<OrderDetailDTO> query = entityManager.createQuery(jpql, OrderDetailDTO.class);
        query.setParameter("orderId", orderId);

        return query.getResultList();
    }

    /**
     * 保存或更新訂單明細資料。
     * 如果訂單明細 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param orderDetails 要保存或更新的訂單明細物件
     * @return 保存或更新後的訂單明細物件
     */
    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        if (orderDetails.getOrderDetailsId() == null) {
            entityManager.persist(orderDetails); // 新增訂單明細
            return orderDetails;
        } else {
            return entityManager.merge(orderDetails); // 更新訂單明細
        }
    }

    /**
     * 根據訂單明細 ID 查詢訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     * @return 包含訂單明細資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<OrderDetails> findById(Integer orderDetailsId) {
        OrderDetails orderDetails = entityManager.find(OrderDetails.class, orderDetailsId);
        return Optional.ofNullable(orderDetails);
    }

    /**
     * 查詢所有訂單明細資料。
     *
     * @return 訂單明細資料的列表
     */
    @Override
    public List<OrderDetails> findAll() {
        String jpql = "SELECT od FROM OrderDetails od";
        TypedQuery<OrderDetails> query = entityManager.createQuery(jpql, OrderDetails.class);
        return query.getResultList();
    }

    /**
     * 根據訂單 ID 查詢該訂單的所有明細。
     *
     * @param ordersId 訂單 ID
     * @return 該訂單的明細列表
     */
    @Override
    public List<OrderDetailDTO> findByOrderId(Integer ordersId) {
        String jpql = "SELECT new com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO( " +
                "od.orderDetailsId, od.product.productName, od.quantity, od.unitPrice) " +
                "FROM OrderDetails od JOIN od.product p WHERE od.order.ordersId = :ordersId";

        TypedQuery<OrderDetailDTO> query = entityManager.createQuery(jpql, OrderDetailDTO.class);
        query.setParameter("ordersId", ordersId);
        return query.getResultList();
    }

    /**
     * 根據產品 ID 查詢該產品的所有訂單明細。
     *
     * @param productsId 產品 ID
     * @return 該產品的訂單明細列表
     */
    @Override
    public List<OrderDetails> findByProductsId(Integer productsId) {
        String jpql = "SELECT od FROM OrderDetails od WHERE od.product.productsId = :productsId";
        TypedQuery<OrderDetails> query = entityManager.createQuery(jpql, OrderDetails.class);
        query.setParameter("productsId", productsId);
        return query.getResultList();
    }

    /**
     * 根據訂單明細 ID 刪除訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     */
    @Override
    public void deleteById(Integer orderDetailsId) {
        OrderDetails orderDetails = entityManager.find(OrderDetails.class, orderDetailsId);
        if (orderDetails != null) {
            entityManager.remove(orderDetails); // 刪除指定訂單明細
        }
    }

    /**
     * 根據訂單 ID 刪除該訂單的所有明細資料。
     *
     * @param ordersId 訂單 ID
     */
    @Override
    public void deleteByOrderId(Integer ordersId) {
        String jpql = "DELETE FROM OrderDetails od WHERE od.order.ordersId = :ordersId";
        entityManager.createQuery(jpql)
                .setParameter("ordersId", ordersId)
                .executeUpdate(); // 刪除該訂單的所有明細
    }

    /**
     * 刪除所有訂單明細資料。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM OrderDetails";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有訂單明細
    }
}
