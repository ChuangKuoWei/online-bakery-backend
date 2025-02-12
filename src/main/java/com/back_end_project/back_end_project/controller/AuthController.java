package com.back_end_project.back_end_project.controller;

import com.back_end_project.back_end_project.database.Customer;
import com.back_end_project.back_end_project.controllerComponentJWT.model.LoginRequest;
import com.back_end_project.back_end_project.controllerComponentJWT.model.LoginResponse;
import com.back_end_project.back_end_project.controllerComponentJWT.service.CustomerService;
import com.back_end_project.back_end_project.controllerComponentJWT.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // 定義 API 路徑為 /auth
@CrossOrigin(origins = "http://localhost:5173") // 允許跨域請求
public class AuthController {

    @Autowired
    private CustomerService customerService; // 使用者業務邏輯服務

    @Autowired
    private JwtTokenService jwtTokenService; // JWT 生成與驗證服務

    /**
     * 登入 API
     * 
     * @param loginRequest 包含帳號與密碼的登入請求
     * @return 如果成功，返回 JWT Token；否則返回錯誤訊息
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 驗證帳號與密碼
        Customer customer = customerService.validateCustomer(loginRequest.getEmail(), loginRequest.getPassword());
        if (customer == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("登入失敗，帳號或密碼錯誤！"));
        }

        // 生成 JWT Token
        String token = jwtTokenService.generateToken(customer);

        // 返回 Token 和客戶資訊
        LoginResponse response = new LoginResponse("登入成功", token, customer);
        return ResponseEntity.ok(response);
    }
}
