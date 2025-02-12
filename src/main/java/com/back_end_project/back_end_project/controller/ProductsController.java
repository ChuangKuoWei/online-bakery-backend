package com.back_end_project.back_end_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.back_end_project.back_end_project.database.Products;
import com.back_end_project.back_end_project.service.ProductsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ProductsController 類，處理與產品相關的 HTTP 請求。
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173") // 指定允許的前端域名
public class ProductsController {

    @Autowired
    private ProductsService productsService; // 注入 ProductsService，處理業務邏輯

    /**
     * 更新產品庫存數量
     *
     * @param productsId 產品 ID
     * @param newStock   新的庫存數量
     * @return 更新後的產品
     */
    @PutMapping("/{productsId}/update-stock")
    public ResponseEntity<Products> updateStock(
            @PathVariable Integer productsId,
            @RequestBody Integer newStock) {

        // 查找產品是否存在
        Products existingProduct = productsService.findProductById(productsId);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 更新庫存數量
        existingProduct.setQuantityInStock(newStock);
        existingProduct.setUpdatedDate(LocalDateTime.now()); // 記錄更新時間

        // 保存更新後的產品
        Products updatedProduct = productsService.saveProduct(existingProduct);

        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * 根據產品 ID 更新產品資料。
     *
     * @param productsId 產品 ID
     * @param product    包含更新數據的產品物件
     * @return 更新後的產品物件
     */
    @PutMapping("/{productsId}")
    public ResponseEntity<Products> updateProduct(
            @PathVariable Integer productsId,
            @RequestBody Products product) {

        // 查詢是否存在該產品
        Products existingProduct = productsService.findProductById(productsId);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果產品不存在，返回 404
        }

        // 更新產品資料
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscountRate(product.getDiscountRate());
        existingProduct.setQuantityInStock(product.getQuantityInStock());
        existingProduct.setLastRestocked(product.getLastRestocked());
        existingProduct.setThresholdLevel(product.getThresholdLevel());
        existingProduct.setWarehouseLocation(product.getWarehouseLocation());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setIsFeatured(product.getIsFeatured());
        existingProduct.setRating(product.getRating());
        existingProduct.setReviewsCount(product.getReviewsCount());
        existingProduct.setUpdatedDate(LocalDateTime.now()); // 更新時間為當前時間
        existingProduct.setCategory(product.getCategory()); // 更新分類

        // 保存更新後的產品
        Products updatedProduct = productsService.saveProduct(existingProduct);

        return ResponseEntity.ok(updatedProduct); // 返回更新後的產品
    }

    /**
     * 保存或更新產品資料。
     *
     * @param product 產品物件
     * @return 保存或更新後的產品物件
     */
    @PostMapping
    public ResponseEntity<Products> saveProduct(@RequestBody Products product) {
        Products savedProduct = productsService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * 透過 POST 方法更新產品資料，支援 Base64 格式圖片
     *
     * @param productsId 產品 ID
     * @param product    產品更新資料
     * @return 更新後的產品物件
     */
    @PostMapping("/{productsId}")
    public ResponseEntity<Products> updateProductViaPost(
            @PathVariable Integer productsId,
            @RequestBody Products product) {

        // 查詢是否存在該產品
        Products existingProduct = productsService.findProductById(productsId);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 更新產品資料
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscountRate(product.getDiscountRate());
        existingProduct.setQuantityInStock(product.getQuantityInStock());
        existingProduct.setLastRestocked(product.getLastRestocked());
        existingProduct.setThresholdLevel(product.getThresholdLevel());
        existingProduct.setWarehouseLocation(product.getWarehouseLocation());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setIsFeatured(product.getIsFeatured());
        existingProduct.setRating(product.getRating());
        existingProduct.setReviewsCount(product.getReviewsCount());
        existingProduct.setUpdatedDate(LocalDateTime.now());
        existingProduct.setCategory(product.getCategory());

        // 🔥 Base64 圖片處理 🔥
        if (product.getImage() != null && product.getImage().length > 0) {
            existingProduct.setImage(product.getImage());
        }

        // 保存更新後的產品
        Products updatedProduct = productsService.saveProduct(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * 根據產品 ID 查詢產品資料。
     *
     * @param productsId 產品 ID
     * @return 如果找到則返回產品物件，否則返回 404 錯誤。
     */
    @GetMapping("/{productsId}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer productsId) {
        Products product = productsService.findProductById(productsId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 根據產品名稱查詢產品資料。
     *
     * @param productName 產品名稱
     * @return 如果找到則返回產品物件，否則返回 404 錯誤。
     */
    @GetMapping("/name/{productName}")
    public ResponseEntity<Products> getProductByName(@PathVariable String productName) {
        Products product = productsService.findProductByName(productName);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 查詢所有產品資料。
     *
     * @return 所有產品的列表。
     */
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * 根據分類 ID 查詢該分類下的所有產品。
     *
     * @param categoriesId 分類 ID
     * @return 符合條件的產品列表。
     */
    @GetMapping("/category/{categoriesId}")
    public ResponseEntity<List<Products>> getProductsByCategoryId(@PathVariable Integer categoriesId) {
        List<Products> products = productsService.findProductsByCategoryId(categoriesId);
        return ResponseEntity.ok(products);
    }

    /**
     * 根據價格範圍查詢產品資料。
     *
     * @param minPrice 最低價格
     * @param maxPrice 最高價格
     * @return 符合條件的產品列表。
     */
    @GetMapping("/price")
    public ResponseEntity<List<Products>> getProductsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<Products> products = productsService.findProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    /**
     * 查詢所有特色產品資料。
     *
     * @return 特色產品的列表。
     */
    @GetMapping("/featured")
    public ResponseEntity<List<Products>> getFeaturedProducts() {
        List<Products> products = productsService.findFeaturedProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * 根據產品 ID 刪除產品資料。
     *
     * @param productsId 產品 ID
     * @return 刪除成功返回 204，否則返回 404。
     */
    @DeleteMapping("/{productsId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer productsId) {
        boolean deleted = productsService.deleteProductById(productsId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 刪除所有產品資料。
     *
     * @return 刪除成功返回 204。
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts() {
        productsService.deleteAllProducts();
        return ResponseEntity.noContent().build();
    }
}
