package com.back_end_project.back_end_project.RepositoryDaoImplement;

import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.OrdersDAO;
import com.back_end_project.back_end_project.database.Orders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * OrdersDAOImpl 類，實現 OrdersDAO 介面，用於操作 Orders 表的數據。
 */
@Repository
public class OrdersDAOImpl implements OrdersDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    /**
     * 保存或更新訂單資料。
     * 如果訂單 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param order 要保存或更新的訂單物件
     * @return 保存或更新後的訂單物件
     */
    @Override
    public Orders save(Orders order) {
        if (order.getOrdersId() == null) {
            entityManager.persist(order); // 新增訂單
            return order;
        } else {
            return entityManager.merge(order); // 更新訂單
        }
    }

    /**
     * 根據訂單 ID 查詢訂單資料。
     *
     * @param ordersId 訂單 ID
     * @return 包含訂單資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Orders> findById(Integer ordersId) {
        Orders order = entityManager.find(Orders.class, ordersId);
        return Optional.ofNullable(order);
    }

    /**
     * 查詢所有訂單資料。
     *
     * @return 訂單資料的列表
     */
    @Override
    public List<Orders> findAll() {
        String jpql = "SELECT o FROM Orders o";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        return query.getResultList();
    }

    /**
     * 根據客戶 ID 查詢該客戶的所有訂單。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的訂單列表
     */
    @Override
    public List<Orders> findByCustomerId(Integer customerId) {
        String jpql = "SELECT o FROM Orders o WHERE o.customer.customerId = :customerId";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    /**
     * 根據訂單是否取消的狀態查詢訂單。
     *
     * @param isCancelled 是否取消
     * @return 符合條件的訂單列表
     */
    @Override
    public List<Orders> findByIsCancelled(Boolean isCancelled) {
        String jpql = "SELECT o FROM Orders o WHERE o.isCancelled = :isCancelled";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        query.setParameter("isCancelled", isCancelled);
        return query.getResultList();
    }

    /**
     * 根據訂單 ID 刪除訂單資料。
     *
     * @param ordersId 訂單 ID
     */
    @Override
    public void deleteById(Integer ordersId) {
        Orders order = entityManager.find(Orders.class, ordersId);
        if (order != null) {
            entityManager.remove(order); // 刪除訂單
        }
    }

    /**
     * 刪除所有訂單資料。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM Orders";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有訂單
    }
}
