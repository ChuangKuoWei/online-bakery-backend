package com.back_end_project.back_end_project.RepositoryDaoAbstract;

import java.util.List;
import java.util.Optional;
import com.back_end_project.back_end_project.database.Customer;

/**
 * CustomerDAO 介面，用於操作 Customer 表的數據。
 */
public interface CustomerDAO {

    /**
     * 保存或更新客戶資料。
     *
     * @param customer 要保存或更新的客戶物件
     * @return 保存或更新後的客戶物件
     */
    Customer save(Customer customer);

    /**
     * 根據客戶 ID 查詢客戶資料。
     *
     * @param customerId 客戶 ID
     * @return 包含客戶資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Customer> findById(Integer customerId);

    /**
     * 根據客戶電子郵件查詢客戶資料。
     *
     * @param email 客戶的電子郵件
     * @return 包含客戶資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    Optional<Customer> findByEmail(String email);

    /**
     * 查詢所有客戶資料。
     *
     * @return 客戶資料的列表
     */
    List<Customer> findAll();

    /**
     * 根據客戶名稱查詢客戶資料。
     *
     * @param name 客戶名稱
     * @return 符合條件的客戶列表
     */
    List<Customer> findByName(String name);

    /**
     * 根據電話號碼查詢客戶資料。
     *
     * @param phoneNumber 客戶電話號碼
     * @return 符合條件的客戶列表
     */
    List<Customer> findByPhoneNumber(String phoneNumber);

    /**
     * 根據客戶 ID 刪除客戶資料。
     *
     * @param customerId 客戶 ID
     */
    void deleteById(Integer customerId);

    /**
     * 刪除所有客戶資料。
     */
    void deleteAll();
}
