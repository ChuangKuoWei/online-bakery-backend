package com.back_end_project.back_end_project.RepositoryDaoImplement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.back_end_project.back_end_project.RepositoryDaoAbstract.CustomerDAO;
import com.back_end_project.back_end_project.database.Customer;

import java.util.List;
import java.util.Optional;

/**
 * CustomerDAOImpl 類，實現 CustomerDAO 介面，用於操作 Customer 表的數據。
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager; // 使用 JPA 的 EntityManager 操作資料庫

    /**
     * 保存或更新客戶資料。
     * 如果客戶 ID 為 null，則執行新增操作；否則執行更新操作。
     *
     * @param customer 要保存或更新的客戶物件
     * @return 保存或更新後的客戶物件
     */
    @Override
    public Customer save(Customer customer) {
        if (customer.getCustomerId() == null) {
            entityManager.persist(customer); // 新增客戶
            return customer;
        } else {
            return entityManager.merge(customer); // 更新客戶
        }
    }

    /**
     * 根據客戶 ID 查詢客戶資料。
     *
     * @param customerId 客戶 ID
     * @return 包含客戶資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Customer> findById(Integer customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        return Optional.ofNullable(customer);
    }

    /**
     * 根據客戶電子郵件查詢客戶資料。
     *
     * @param email 客戶的電子郵件
     * @return 包含客戶資料的 Optional 物件，若無結果則為 Optional.empty()
     */
    @Override
    public Optional<Customer> findByEmail(String email) {
        String jpql = "SELECT c FROM Customer c WHERE c.email = :email";
        try {
            Customer customer = entityManager.createQuery(jpql, Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(customer);
        } catch (NoResultException e) {
            return Optional.empty(); // 如果查無結果，返回空的 Optional
        }
    }

    /**
     * 查詢所有客戶資料。
     *
     * @return 客戶資料的列表
     */
    @Override
    public List<Customer> findAll() {
        String jpql = "SELECT c FROM Customer c";
        return entityManager.createQuery(jpql, Customer.class).getResultList();
    }

    /**
     * 根據客戶名稱查詢客戶資料。
     *
     * @param name 客戶名稱
     * @return 符合條件的客戶列表
     */
    @Override
    public List<Customer> findByName(String name) {
        String jpql = "SELECT c FROM Customer c WHERE c.name = :name";
        return entityManager.createQuery(jpql, Customer.class)
                .setParameter("name", name)
                .getResultList();
    }

    /**
     * 根據電話號碼查詢客戶資料。
     *
     * @param phoneNumber 客戶電話號碼
     * @return 符合條件的客戶列表
     */
    @Override
    public List<Customer> findByPhoneNumber(String phoneNumber) {
        String jpql = "SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber";
        return entityManager.createQuery(jpql, Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }

    /**
     * 根據客戶 ID 刪除客戶資料。
     *
     * @param customerId 客戶 ID
     */
    @Override
    public void deleteById(Integer customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer != null) {
            entityManager.remove(customer); // 刪除客戶
        }
    }

    /**
     * 刪除所有客戶資料。
     */
    @Override
    public void deleteAll() {
        String jpql = "DELETE FROM Customer";
        entityManager.createQuery(jpql).executeUpdate(); // 刪除所有客戶資料
    }
}
