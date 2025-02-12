package com.back_end_project.back_end_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_end_project.back_end_project.database.ShoppingCart;
import com.back_end_project.back_end_project.service.ShoppingCartService;

import java.util.List;

/**
 * ShoppingCartController 類，用於處理與購物車相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/shopping-carts") // 使用複數形式，符合 RESTful 規範
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService; // 注入 ShoppingCartService，負責處理業務邏輯

    /**
     * 更新購物車項目。
     *
     * @param shoppingCartId 購物車項目 ID
     * @param shoppingCart   要更新的購物車資料
     * @return 更新後的購物車物件，或者 404 如果項目不存在。
     */
    @PutMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(
            @PathVariable Integer shoppingCartId,
            @RequestBody ShoppingCart shoppingCart) {

        // 檢查購物車項目是否存在
        ShoppingCart existingCart = shoppingCartService.findShoppingCartById(shoppingCartId);
        if (existingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果不存在，返回 404
        }

        // 更新購物車項目的相關資料
        existingCart.setCustomer(shoppingCart.getCustomer());
        existingCart.setProduct(shoppingCart.getProduct());
        existingCart.setQuantity(shoppingCart.getQuantity());
        existingCart.setDateAdded(shoppingCart.getDateAdded());

        // 保存更新後的購物車項目
        ShoppingCart updatedCart = shoppingCartService.saveShoppingCart(existingCart);

        return ResponseEntity.ok(updatedCart); // 返回更新後的購物車物件
    }

    /**
     * 新增或更新購物車項目。
     *
     * @param shoppingCart 購物車物件
     * @return 新增或更新後的購物車物件
     */
    @PostMapping
    public ResponseEntity<?> saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        // 驗證客戶和產品是否有效
        if (shoppingCart.getCustomer() == null || shoppingCart.getCustomer().getCustomerId() == null) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        }
        if (shoppingCart.getProduct() == null || shoppingCart.getProduct().getProductsId() == null) {
            return ResponseEntity.badRequest().body("Invalid product ID");
        }
        ShoppingCart savedCart = shoppingCartService.saveShoppingCart(shoppingCart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    /**
     * 根據購物車 ID 查詢購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     * @return 如果找到則返回購物車物件，否則返回 404。
     */
    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Integer shoppingCartId) {
        ShoppingCart cart = shoppingCartService.findShoppingCartById(shoppingCartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據客戶 ID 查詢該客戶的購物車項目。
     *
     * @param customerId 客戶 ID
     * @return 該客戶的購物車項目列表
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ShoppingCart>> getShoppingCartsByCustomerId(@PathVariable Integer customerId) {
        List<ShoppingCart> carts = shoppingCartService.findShoppingCartsByCustomerId(customerId);
        return ResponseEntity.ok(carts);
    }

    /**
     * 根據產品 ID 查詢該產品的購物車項目。
     *
     * @param productsId 產品 ID
     * @return 該產品的購物車項目列表
     */
    @GetMapping("/product/{productsId}")
    public ResponseEntity<List<ShoppingCart>> getShoppingCartsByProductsId(@PathVariable Integer productsId) {
        List<ShoppingCart> carts = shoppingCartService.findShoppingCartsByProductsId(productsId);
        return ResponseEntity.ok(carts);
    }

    /**
     * 查詢所有購物車項目。
     *
     * @return 所有購物車項目的列表
     */
    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        List<ShoppingCart> carts = shoppingCartService.findAllShoppingCarts();
        return ResponseEntity.ok(carts);
    }

    /**
     * 根據購物車 ID 刪除購物車項目。
     *
     * @param shoppingCartId 購物車 ID
     * @return 如果刪除成功則返回 204，否則返回 404。
     */
    @DeleteMapping("/{shoppingCartId}")
    public ResponseEntity<Void> deleteShoppingCartById(@PathVariable Integer shoppingCartId) {
        boolean isDeleted = shoppingCartService.deleteShoppingCartById(shoppingCartId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據客戶 ID 刪除該客戶的所有購物車項目。
     *
     * @param customerId 客戶 ID
     * @return 返回 204 無內容。
     */
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Void> deleteShoppingCartsByCustomerId(@PathVariable Integer customerId) {
        shoppingCartService.deleteShoppingCartsByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 根據產品 ID 刪除該產品的所有購物車項目。
     *
     * @param productsId 產品 ID
     * @return 返回 204 無內容。
     */
    @DeleteMapping("/product/{productsId}")
    public ResponseEntity<Void> deleteShoppingCartsByProductsId(@PathVariable Integer productsId) {
        shoppingCartService.deleteShoppingCartsByProductsId(productsId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 刪除所有購物車項目。
     *
     * @return 返回 204 無內容。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllShoppingCarts() {
        shoppingCartService.deleteAllShoppingCarts();
        return ResponseEntity.noContent().build();
    }
}
