package com.back_end_project.back_end_project.controllerComponentJWT.repository;

import com.back_end_project.back_end_project.database.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email); // 根據 Email 查找客戶
}