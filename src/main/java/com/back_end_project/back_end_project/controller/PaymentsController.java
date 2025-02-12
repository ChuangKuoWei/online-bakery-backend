package com.back_end_project.back_end_project.controller;


import com.back_end_project.back_end_project.database.Payments;
import com.back_end_project.back_end_project.service.PaymentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PaymentsController 類，用於處理與支付交易相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService; // 注入 PaymentsService，負責處理業務邏輯

    /**
     * 根據交易 ID 更新支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @param updatedPayment 更新後的支付交易資料
     * @return 如果更新成功則返回更新後的支付交易物件，否則返回 404。
     */
    @PutMapping("/{paymentsId}")
    public ResponseEntity<Payments> updatePayment(@PathVariable Integer paymentsId, @RequestBody Payments updatedPayment) {
        // 根據交易 ID 查詢是否存在
        Payments existingPayment = paymentsService.findPaymentById(paymentsId);

        if (existingPayment != null) {
            // 更新支付交易的屬性
            existingPayment.setOrder(updatedPayment.getOrder());
            existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
            existingPayment.setTransactionDate(updatedPayment.getTransactionDate());
            existingPayment.setTransactionAmount(updatedPayment.getTransactionAmount());
            existingPayment.setTransactionStatus(updatedPayment.getTransactionStatus());
            existingPayment.setGatewayResponse(updatedPayment.getGatewayResponse());

            // 保存更新後的支付交易
            Payments savedPayment = paymentsService.savePayment(existingPayment);

            // 返回更新後的支付交易資料
            return ResponseEntity.ok(savedPayment);
        } else {
            // 如果找不到交易，返回 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * 新增或更新支付交易資料。
     *
     * @param payment 支付交易物件
     * @return 新增或更新後的支付交易物件
     */
    @PostMapping
    public ResponseEntity<Payments> savePayment(@RequestBody Payments payment) {
        Payments savedPayment = paymentsService.savePayment(payment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    /**
     * 根據交易 ID 查詢支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @return 如果找到則返回支付交易物件，否則返回 404。
     */
    @GetMapping("/{paymentsId}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Integer paymentsId) {
        Payments payment = paymentsService.findPaymentById(paymentsId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 查詢所有支付交易資料。
     *
     * @return 支付交易資料的列表
     */
    @GetMapping
    public ResponseEntity<List<Payments>> getAllPayments() {
        List<Payments> paymentsList = paymentsService.findAllPayments();
        return ResponseEntity.ok(paymentsList);
    }

    /**
     * 根據訂單 ID 查詢支付交易資料。
     *
     * @param ordersId 訂單 ID
     * @return 如果找到則返回支付交易物件，否則返回 404。
     */
    @GetMapping("/order/{ordersId}")
    public ResponseEntity<Payments> getPaymentByOrderId(@PathVariable Integer ordersId) {
        Payments payment = paymentsService.findPaymentByOrderId(ordersId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據交易 ID 刪除支付交易資料。
     *
     * @param paymentsId 交易 ID
     * @return 如果刪除成功則返回 204，否則返回 404。
     */
    @DeleteMapping("/{paymentsId}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable Integer paymentsId) {
        boolean isDeleted = paymentsService.deletePaymentById(paymentsId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據訂單 ID 刪除該訂單的支付交易資料。
     *
     * @param ordersId 訂單 ID
     * @return 返回 204 無內容。
     */
    @DeleteMapping("/order/{ordersId}")
    public ResponseEntity<Void> deletePaymentByOrderId(@PathVariable Integer ordersId) {
        paymentsService.deletePaymentByOrderId(ordersId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 刪除所有支付交易資料。
     *
     * @return 返回 204 無內容。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllPayments() {
        paymentsService.deleteAllPayments();
        return ResponseEntity.noContent().build();
    }
}
