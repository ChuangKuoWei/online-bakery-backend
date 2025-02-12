package com.back_end_project.back_end_project.RepositoryDaoImplement;

import com.back_end_project.back_end_project.database.Payments;
import com.back_end_project.back_end_project.RepositoryDaoAbstract.PaymentsDAO;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * PaymentsDAOImpl 類，實現 PaymentsDAO 介面，用於操作 Payments 表的數據。
 */
@Repository
public class PaymentsDAOImpl implements PaymentsDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    /**
     * 保存或更新支付交易資料。
     * 如果交易 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param payment 要保存或更新的支付交易物件
     * @return 保存或更新後的支付交易物件
     */
    @Override
    public Payments save(Payments payment) {
        if (payment.getPaymentsId() == null) {
            entityManager.persist(payment); // 新增支付交易
            return payment;
        } else {
            return entityManager.merge(payment); // 更新支付交易
        }
    }

    /**
     * 根據交易 ID 查詢支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @return 包含支付交易資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Payments> findById(Integer paymentsId) {
        Payments payment = entityManager.find(Payments.class, paymentsId);
        return Optional.ofNullable(payment);
    }

    /**
     * 查詢所有支付交易資料。
     *
     * @return 支付交易資料的列表
     */
    @Override
    public List<Payments> findAll() {
        String jpql = "SELECT p FROM Payments p";
        TypedQuery<Payments> query = entityManager.createQuery(jpql, Payments.class);
        return query.getResultList();
    }

    /**
     * 根據訂單 ID 查詢支付交易資料。
     *
     * @param ordersId 訂單 ID
     * @return 包含支付交易資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Payments> findByOrderId(Integer ordersId) {
        String jpql = "SELECT p FROM Payments p WHERE p.order.ordersId = :ordersId";
        TypedQuery<Payments> query = entityManager.createQuery(jpql, Payments.class);
        query.setParameter("ordersId", ordersId);
        List<Payments> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    /**
     * 根據交易 ID 刪除支付交易資料。
     *
     * @param paymentsId 交易 ID
     */
    @Override
    public void deleteById(Integer paymentsId) {
        Payments payment = entityManager.find(Payments.class, paymentsId);
        if (payment != null) {
            entityManager.remove(payment); // 刪除支付交易
        }
    }

    /**
     * 根據訂單 ID 刪除該訂單的支付交易資料。
     *
     * @param ordersId 訂單 ID
     */
    @Override
    public void deleteByOrderId(Integer ordersId) {
        String jpql = "DELETE FROM Payments p WHERE p.order.ordersId = :ordersId";
        entityManager.createQuery(jpql)
                     .setParameter("ordersId", ordersId)
                     .executeUpdate(); // 刪除該訂單的支付交易
    }

    /**
     * 刪除所有支付交易資料。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM Payments";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有支付交易資料
    }
}
