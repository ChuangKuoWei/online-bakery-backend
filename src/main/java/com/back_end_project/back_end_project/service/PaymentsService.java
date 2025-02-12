package com.back_end_project.back_end_project.service;


import com.back_end_project.back_end_project.database.Payments;
import com.back_end_project.back_end_project.RepositoryDaoAbstract.PaymentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * PaymentsService 類，用於處理與支付交易相關的業務邏輯。
 */
@Service
public class PaymentsService {

    @Autowired
    private PaymentsDAO paymentsDAO; // 注入 PaymentsDAO，負責與資料庫交互

    /**
     * 保存或更新支付交易資料。
     *
     * @param payment 支付交易物件
     * @return 保存或更新後的支付交易物件
     */
    @Transactional
    public Payments savePayment(Payments payment) {
        return paymentsDAO.save(payment);
    }

    /**
     * 根據交易 ID 查詢支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @return 如果找到則返回支付交易物件，否則返回 null。
     */
    public Payments findPaymentById(Integer paymentsId) {
        Optional<Payments> optionalPayment = paymentsDAO.findById(paymentsId);
        return optionalPayment.orElse(null);
    }

    /**
     * 查詢所有支付交易資料。
     *
     * @return 支付交易資料的列表
     */
    public List<Payments> findAllPayments() {
        return paymentsDAO.findAll();
    }

    /**
     * 根據訂單 ID 查詢支付交易資料。
     *
     * @param ordersId 訂單 ID
     * @return 如果找到則返回支付交易物件，否則返回 null。
     */
    public Payments findPaymentByOrderId(Integer ordersId) {
        Optional<Payments> optionalPayment = paymentsDAO.findByOrderId(ordersId);
        return optionalPayment.orElse(null);
    }

    /**
     * 根據交易 ID 刪除支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deletePaymentById(Integer paymentsId) {
        Optional<Payments> optionalPayment = paymentsDAO.findById(paymentsId);
        if (optionalPayment.isPresent()) {
            paymentsDAO.deleteById(paymentsId);
            return true;
        }
        return false;
    }

    /**
     * 根據訂單 ID 刪除該訂單的支付交易資料。
     *
     * @param ordersId 訂單 ID
     */
    @Transactional
    public void deletePaymentByOrderId(Integer ordersId) {
        paymentsDAO.deleteByOrderId(ordersId);
    }

    /**
     * 刪除所有支付交易資料。
     */
    @Transactional
    public void deleteAllPayments() {
        paymentsDAO.deleteAll();
    }
}
