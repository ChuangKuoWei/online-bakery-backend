package com.back_end_project.back_end_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.CustomerDAO;
import com.back_end_project.back_end_project.database.Customer;

import java.util.List;

/**
 * CustomerService 類，用於處理與客戶相關的業務邏輯。
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO; // 注入 CustomerDAO，負責與資料庫交互

    /**
     * 保存或更新客戶資料。
     *
     * @param customer 客戶物件
     * @return 保存或更新後的客戶物件
     */
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerDAO.save(customer);
    }

    /**
     * 根據客戶 ID 查詢客戶資料。
     *
     * @param customerId 客戶 ID
     * @return 如果找到則返回客戶物件，否則返回 null。
     */
    public Customer findCustomerById(Integer customerId) {
        return customerDAO.findById(customerId).orElse(null);
    }

    /**
     * 根據客戶電子郵件查詢客戶資料。
     *
     * @param email 客戶的電子郵件
     * @return 如果找到則返回客戶物件，否則返回 null。
     */
    public Customer findCustomerByEmail(String email) {
        return customerDAO.findByEmail(email).orElse(null);
    }

    /**
     * 查詢所有客戶資料。
     *
     * @return 客戶資料的列表
     */
    public List<Customer> findAllCustomers() {
        return customerDAO.findAll();
    }

    /**
     * 根據客戶名稱查詢客戶資料。
     *
     * @param name 客戶名稱
     * @return 符合條件的客戶列表
     */
    public List<Customer> findCustomersByName(String name) {
        return customerDAO.findByName(name);
    }

    /**
     * 根據電話號碼查詢客戶資料。
     *
     * @param phoneNumber 客戶電話號碼
     * @return 符合條件的客戶列表
     */
    public List<Customer> findCustomersByPhoneNumber(String phoneNumber) {
        return customerDAO.findByPhoneNumber(phoneNumber);
    }

    /**
     * 根據客戶 ID 刪除客戶資料。
     *
     * @param customerId 客戶 ID
     * @return 如果刪除成功則返回 true，否則返回 false。
     */
    @Transactional
    public boolean deleteCustomerById(Integer customerId) {
        if (customerDAO.findById(customerId).isPresent()) {
            customerDAO.deleteById(customerId);
            return true;
        }
        return false;
    }

    /**
     * 刪除所有客戶資料。
     */
    @Transactional
    public void deleteAllCustomers() {
        customerDAO.deleteAll();
    }
}
