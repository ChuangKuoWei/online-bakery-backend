package com.back_end_project.back_end_project.controller;

import com.back_end_project.back_end_project.database.Orders;
import com.back_end_project.back_end_project.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrdersController 類，用於處理與訂單相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class OrdersController {

    @Autowired
    private OrdersService ordersService; // 注入 OrdersService，負責處理業務邏輯

    /**
     * 根據訂單 ID 更新訂單資料。
     *
     * @param ordersId     訂單 ID
     * @param updatedOrder 更新後的訂單資料
     * @return 如果更新成功則返回更新後的訂單物件，否則返回 404。
     */
    @PutMapping("/{ordersId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer ordersId, @RequestBody Orders updatedOrder) {
        // 根據 ID 查詢訂單是否存在
        Orders existingOrder = ordersService.findOrderById(ordersId);

        if (existingOrder != null) {
            // 更新訂單的屬性
            if (updatedOrder.getCustomer() != null) {
                existingOrder.setCustomer(updatedOrder.getCustomer());
            }
            if (updatedOrder.getOrderDate() != null) {
                existingOrder.setOrderDate(updatedOrder.getOrderDate());
            }
            if (updatedOrder.getTotalAmount() != null) {
                existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
            }
            if (updatedOrder.getPaymentStatus() != null) {
                existingOrder.setPaymentStatus(updatedOrder.getPaymentStatus());
            }
            if (updatedOrder.getShippingStatus() != null) {
                existingOrder.setShippingStatus(updatedOrder.getShippingStatus());
            }
            if (updatedOrder.getShippingAddress() != null) {
                existingOrder.setShippingAddress(updatedOrder.getShippingAddress());
            }
            if (updatedOrder.getBillingAddress() != null) {
                existingOrder.setBillingAddress(updatedOrder.getBillingAddress());
            }
            if (updatedOrder.getPaymentMethod() != null) {
                existingOrder.setPaymentMethod(updatedOrder.getPaymentMethod());
            }
            if (updatedOrder.getTrackingNumber() != null) {
                existingOrder.setTrackingNumber(updatedOrder.getTrackingNumber());
            }
            if (updatedOrder.getEstimatedDeliveryDate() != null) {
                existingOrder.setEstimatedDeliveryDate(updatedOrder.getEstimatedDeliveryDate());
            }
            if (updatedOrder.getNotes() != null) {
                existingOrder.setNotes(updatedOrder.getNotes());
            }
            if (updatedOrder.getIsCancelled() != null) {
                existingOrder.setIsCancelled(updatedOrder.getIsCancelled());
            }
            if (updatedOrder.getCancellationDate() != null) {
                existingOrder.setCancellationDate(updatedOrder.getCancellationDate());
            }

            // 自動設置更新時間
            existingOrder.setUpdatedDate(LocalDateTime.now());

            // 保存更新後的訂單
            Orders savedOrder = ordersService.saveOrder(existingOrder);

            // 返回更新後的訂單資料
            return ResponseEntity.ok(savedOrder);
        } else {
            // 如果找不到訂單，返回 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 新增或更新訂單。
     *
     * @param order 訂單物件
     * @return 新增或更新後的訂單物件
     */
    @PostMapping
    public ResponseEntity<Orders> saveOrder(@RequestBody Orders order) {
        Orders savedOrder = ordersService.saveOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    /**
     * 根據訂單 ID 查詢訂單。
     *
     * @param ordersId 訂單 ID
     * @return 如果找到則返回訂單物件，否則返回 404。
     */
    @GetMapping("/{ordersId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Integer ordersId) {
        Orders order = ordersService.findOrderById(ordersId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 查詢所有訂單。
     *
     * @return 訂單列表
     */
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> ordersList = ordersService.findAllOrders();
        return ResponseEntity.ok(ordersList);
    }

    /**
     * 根據客戶 ID 查詢該客戶的所有訂單。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的訂單列表
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerId(@PathVariable Integer customerId) {
        List<Orders> ordersList = ordersService.findOrdersByCustomerId(customerId);
        return ResponseEntity.ok(ordersList);
    }

    /**
     * 根據訂單是否取消的狀態查詢訂單。
     *
     * @param isCancelled 是否取消
     * @return 符合條件的訂單列表
     */
    @GetMapping("/status/cancelled/{isCancelled}")
    public ResponseEntity<List<Orders>> getOrdersByIsCancelled(@PathVariable Boolean isCancelled) {
        List<Orders> ordersList = ordersService.findOrdersByIsCancelled(isCancelled);
        return ResponseEntity.ok(ordersList);
    }

    /**
     * 根據訂單 ID 刪除訂單。
     *
     * @param ordersId 訂單 ID
     * @return 如果刪除成功則返回 204，否則返回 404。
     */
    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Integer ordersId) {
        boolean isDeleted = ordersService.deleteOrderById(ordersId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 刪除所有訂單。
     *
     * @return 返回 204 無內容。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrders() {
        ordersService.deleteAllOrders();
        return ResponseEntity.noContent().build();
    }

    /**
     * 查詢最後一筆訂單的 ID
     *
     * @return 最後一筆訂單的 ID，如果沒有訂單則返回 404。
     */
    @GetMapping("/last")
    public ResponseEntity<Integer> getLastOrderId() {
        Orders lastOrder = ordersService.getLastOrder();

        if (lastOrder != null) {
            return ResponseEntity.ok(lastOrder.getOrdersId()); // 返回最後一筆訂單的 ID
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果沒有訂單，返回 404
        }
    }

}
