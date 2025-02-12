package com.back_end_project.back_end_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_end_project.back_end_project.database.Customer;
import com.back_end_project.back_end_project.service.CustomerService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CustomerController 類，用於處理與客戶相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class CustomerController {

    @Autowired
    private CustomerService customerService; // 注入 CustomerService 處理業務邏輯

    /**
     * 根據客戶 ID 更新客戶資料。
     *
     * @param customerId 客戶 ID
     * @param customer   包含更新數據的客戶物件
     * @return 更新後的客戶物件
     */
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody Customer customer) {

        // 查詢是否存在該客戶
        Customer existingCustomer = customerService.findCustomerById(customerId);
        if (existingCustomer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果客戶不存在，返回 404
        }

        // 更新客戶資料
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setState(customer.getState());
        existingCustomer.setPostalCode(customer.getPostalCode());
        existingCustomer.setCountry(customer.getCountry());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setPreferredLanguage(customer.getPreferredLanguage());
        existingCustomer.setBirthDate(customer.getBirthDate());
        existingCustomer.setIsActive(customer.getIsActive());
        existingCustomer.setLoyaltyPoints(customer.getLoyaltyPoints());
        existingCustomer.setLastLogin(customer.getLastLogin());
        existingCustomer.setUpdatedDate(LocalDateTime.now()); // 更新時間為當前時間

        // 保存更新後的客戶
        Customer updatedCustomer = customerService.saveCustomer(existingCustomer);

        return ResponseEntity.ok(updatedCustomer); // 返回更新後的客戶
    }

    /**
     * 保存或更新客戶資料。
     *
     * @param customer 客戶物件
     * @return 保存或更新後的客戶物件
     */
    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    /**
     * 根據客戶 ID 查詢客戶資料。
     *
     * @param customerId 客戶 ID
     * @return 如果找到則返回客戶物件，否則返回 404 錯誤。
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據客戶電子郵件查詢客戶資料。
     *
     * @param email 客戶的電子郵件
     * @return 如果找到則返回客戶物件，否則返回 404 錯誤。
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.findCustomerByEmail(email);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 查詢所有客戶資料。
     *
     * @return 所有客戶的列表。
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * 根據客戶名稱查詢客戶資料。
     *
     * @param name 客戶名稱
     * @return 符合條件的客戶列表。
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name) {
        List<Customer> customers = customerService.findCustomersByName(name);
        return ResponseEntity.ok(customers);
    }

    /**
     * 根據電話號碼查詢客戶資料。
     *
     * @param phoneNumber 客戶電話號碼
     * @return 符合條件的客戶列表。
     */
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<Customer>> getCustomersByPhoneNumber(@PathVariable String phoneNumber) {
        List<Customer> customers = customerService.findCustomersByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customers);
    }

    /**
     * 根據客戶 ID 刪除客戶資料。
     *
     * @param customerId 客戶 ID
     * @return 刪除成功返回 204，若客戶不存在則返回 404。
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer customerId) {
        boolean deleted = customerService.deleteCustomerById(customerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 刪除所有客戶資料。
     *
     * @return 刪除成功返回 204。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllCustomers() {
        customerService.deleteAllCustomers();
        return ResponseEntity.noContent().build();
    }
}
