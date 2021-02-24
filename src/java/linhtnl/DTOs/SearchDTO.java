/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package linhtnl.DTOs;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class SearchDTO implements Serializable{
    String nameCar, categoryId;
    int carNum;
    String dateReturn, dateRent;
    String name;
    @Override
    public String toString() {
        return nameCar +" - "+carNum;
    }
    
    public SearchDTO() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SearchDTO(String nameCar, String categoryId, int carNum, String dateReturn, String dateRent) {
        this.nameCar = nameCar;
        this.categoryId = categoryId;
        this.carNum = carNum;
        this.dateReturn = dateReturn;
        this.dateRent = dateRent;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getDateRent() {
        return dateRent;
    }

    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }
    
}
