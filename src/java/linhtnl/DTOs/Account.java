/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package linhtnl.DTOs;

/**
 *
 * @author ASUS
 */
public class Account {
    private String email, password,name,phone,address,status;
    private String accountError, password_Error;

    public Account() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountError() {
        return accountError;
    }

    public void setAccountError(String accountError) {
        this.accountError = accountError;
    }

    public String getPassword_Error() {
        return password_Error;
    }

    public void setPassword_Error(String password_Error) {
        this.password_Error = password_Error;
    }
    
   
}
