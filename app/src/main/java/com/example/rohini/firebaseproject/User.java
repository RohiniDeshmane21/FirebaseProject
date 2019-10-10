package com.example.rohini.firebaseproject;

public class User {

    String keyId, UserName,Phone,Email;

    public User() {
    }

    public User(String UserName, String Phone, String Email) {
        this.UserName = UserName;
        this.Phone = Phone;
        this.Email = Email;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String KeyId) {
        this.keyId = KeyId;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}