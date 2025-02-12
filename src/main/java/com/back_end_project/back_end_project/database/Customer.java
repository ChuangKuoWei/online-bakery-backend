package com.back_end_project.back_end_project.database;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Customer 實體類，對應資料庫中的 Customer 表。
 */
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId; // 客戶ID (主鍵)

    @Column(nullable = false, length = 50)
    private String name; // 客戶名字

    @Column(unique = true, nullable = false, length = 100)
    private String email; // 客戶電子郵件 (唯一)

    @Column(nullable = false, length = 255)
    private String passwordHash; // 加密密碼

    @Column(length = 20)
    private String phoneNumber; // 客戶聯絡電話

    @Column(length = 255)
    private String address; // 客戶地址

    @Column(length = 100)
    private String city; // 城市

    @Column(length = 100)
    private String state; // 州或省

    @Column(length = 20)
    private String postalCode; // 郵遞區號

    @Column(length = 100)
    private String country; // 國家

    @Column
    private LocalDateTime registrationDate = LocalDateTime.now(); // 註冊時間

    @Column
    private Boolean isActive = true; // 帳號是否啟用

    @Column(length = 10)
    private String gender; // 性別

    @Column
    private LocalDate birthDate; // 出生日期

    @Column(length = 50)
    private String preferredLanguage; // 偏好語言

    @Column
    private Integer loyaltyPoints = 0; // 累積積分

    @Column
    private LocalDateTime lastLogin; // 最後登入時間

    @Column(columnDefinition = "VARBINARY(MAX)")
    private byte[] image; // 個人照片

    @Column(length = 50, nullable = true)
    private String notes; // 客戶備註

    @Column
    private LocalDateTime createdDate = LocalDateTime.now(); // 創建時間

    @Column
    private LocalDateTime updatedDate = LocalDateTime.now(); // 更新時間

    @Column
    private Boolean isSuperAdmin = false; // 是否為超級管理員

    // Getter 和 Setter

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }
}
