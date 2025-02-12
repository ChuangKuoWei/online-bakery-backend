package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import com.back_end_project.back_end_project.database.Payments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * PaymentsDAO 介面，用於操作 Payments 表的數據。
 */
@Repository
public interface PaymentsDAO {

    /**
     * 保存或更新支付交易資料。
     * 如果交易 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param payment 要保存或更新的支付交易物件
     * @return 保存或更新後的支付交易物件
     */
    Payments save(Payments payment);

    /**
     * 根據交易 ID 查詢支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @return 包含支付交易資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Payments> findById(Integer paymentsId);

    /**
     * 查詢所有支付交易資料。
     *
     * @return 支付交易資料的列表
     */
    List<Payments> findAll();

    /**
     * 根據訂單 ID 查詢支付交易資料。
     *
     * @param ordersId 訂單 ID
     * @return 包含支付交易資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Payments> findByOrderId(Integer ordersId);

    /**
     * 根據交易 ID 刪除支付交易資料。
     *
     * @param paymentsId 交易 ID
     */
    void deleteById(Integer paymentsId);

    /**
     * 根據訂單 ID 刪除該訂單的支付交易資料。
     *
     * @param ordersId 訂單 ID
     */
    void deleteByOrderId(Integer ordersId);

    /**
     * 刪除所有支付交易資料。
     */
    void deleteAll();
}
