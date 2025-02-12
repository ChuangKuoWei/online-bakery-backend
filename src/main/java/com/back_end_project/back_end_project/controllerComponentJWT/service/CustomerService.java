package com.back_end_project.back_end_project.controllerComponentJWT.service;

import com.back_end_project.back_end_project.database.Customer;
import com.back_end_project.back_end_project.controllerComponentJWT.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jwtCustomerService")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 驗證使用者的 Email 和密碼
     *
     * @param email    使用者的電子郵件
     * @param password 使用者的密碼
     * @return 如果成功，返回 Customer；否則返回 null
     */
    public Customer validateCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPasswordHash().equals(password)) {
            return customer; // 驗證成功
        }
        return null; // 驗證失敗
    }
}
