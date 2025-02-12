package com.back_end_project.back_end_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_end_project.back_end_project.RepositoryDTO.OrderDetailDTO;
import com.back_end_project.back_end_project.database.OrderDetails;
import com.back_end_project.back_end_project.service.OrderDetailsService;

import java.util.List;

/**
 * OrderDetailsController 類，用於處理與訂單明細相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/order-details")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService; // 注入 OrderDetailsService，負責處理業務邏輯

    /**
     * 根據訂單明細 ID 更新訂單明細資料。
     *
     * @param orderDetailsId      訂單明細 ID
     * @param updatedOrderDetails 更新後的訂單明細資料
     * @return 如果更新成功則返回更新後的訂單明細物件，否則返回 404。
     */
    @PutMapping("/{orderDetailsId}")
    public ResponseEntity<OrderDetails> updateOrderDetails(
            @PathVariable Integer orderDetailsId,
            @RequestBody OrderDetails updatedOrderDetails) {

        // 根據訂單明細 ID 查詢是否存在
        OrderDetails existingOrderDetails = orderDetailsService.findOrderDetailsById(orderDetailsId);

        if (existingOrderDetails != null) {
            // 更新訂單明細的屬性
            existingOrderDetails.setOrder(updatedOrderDetails.getOrder());
            existingOrderDetails.setProduct(updatedOrderDetails.getProduct());
            existingOrderDetails.setQuantity(updatedOrderDetails.getQuantity());
            existingOrderDetails.setUnitPrice(updatedOrderDetails.getUnitPrice());
            existingOrderDetails.setDiscount(updatedOrderDetails.getDiscount());

            // 保存更新後的訂單明細
            OrderDetails savedOrderDetails = orderDetailsService.saveOrderDetails(existingOrderDetails);

            // 返回更新後的訂單明細資料
            return ResponseEntity.ok(savedOrderDetails);
        } else {
            // 如果找不到訂單明細，返回 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 新增或更新訂單明細資料。
     *
     * @param orderDetails 訂單明細物件
     * @return 新增或更新後的訂單明細物件
     */
    @PostMapping
    public ResponseEntity<OrderDetails> saveOrderDetails(@RequestBody OrderDetails orderDetails) {
        OrderDetails savedOrderDetails = orderDetailsService.saveOrderDetails(orderDetails);
        return new ResponseEntity<>(savedOrderDetails, HttpStatus.CREATED);
    }

    /**
     * 根據訂單明細 ID 查詢訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     * @return 如果找到則返回訂單明細物件，否則返回 404。
     */
    @GetMapping("/{orderDetailsId}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Integer orderDetailsId) {
        OrderDetails orderDetails = orderDetailsService.findOrderDetailsById(orderDetailsId);
        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 查詢所有訂單明細資料。
     *
     * @return 訂單明細資料的列表
     */
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsService.findAllOrderDetails();
        return ResponseEntity.ok(orderDetailsList);
    }

    /**
     * 根據訂單 ID 查詢該訂單的所有明細。
     *
     * @param ordersId 訂單 ID
     * @return 該訂單的明細列表
     */
    @GetMapping("/order/{ordersId}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable Integer ordersId) {
        List<OrderDetailDTO> orderDetailsList = orderDetailsService.findOrderDetailsByOrderId(ordersId);
        return ResponseEntity.ok(orderDetailsList);
    }

    /**
     * 根據產品 ID 查詢該產品的所有訂單明細。
     *
     * @param productsId 產品 ID
     * @return 該產品的訂單明細列表
     */
    @GetMapping("/product/{productsId}")
    public ResponseEntity<List<OrderDetails>> getOrderDetailsByProductsId(@PathVariable Integer productsId) {
        List<OrderDetails> orderDetailsList = orderDetailsService.findOrderDetailsByProductsId(productsId);
        return ResponseEntity.ok(orderDetailsList);
    }

    /**
     * 根據訂單明細 ID 刪除訂單明細資料。
     *
     * @param orderDetailsId 訂單明細 ID
     * @return 如果刪除成功則返回 204，否則返回 404。
     */
    @DeleteMapping("/{orderDetailsId}")
    public ResponseEntity<Void> deleteOrderDetailsById(@PathVariable Integer orderDetailsId) {
        boolean isDeleted = orderDetailsService.deleteOrderDetailsById(orderDetailsId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據訂單 ID 刪除該訂單的所有明細資料。
     *
     * @param ordersId 訂單 ID
     * @return 返回 204 無內容。
     */
    @DeleteMapping("/order/{ordersId}")
    public ResponseEntity<Void> deleteOrderDetailsByOrderId(@PathVariable Integer ordersId) {
        orderDetailsService.deleteOrderDetailsByOrderId(ordersId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 刪除所有訂單明細資料。
     *
     * @return 返回 204 無內容。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrderDetails() {
        orderDetailsService.deleteAllOrderDetails();
        return ResponseEntity.noContent().build();
    }
}
