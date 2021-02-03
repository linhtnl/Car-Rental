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
public class CarDTO implements Serializable{

    private String carId, name, color, categoryId;
    private int year, quantity;
    private float price,rateAvg;

    public CarDTO() {
    }

    public CarDTO(String carId, String name, String color, String categoryId, int year, int quantity, float price) {
        this.carId = carId;
        this.name = name;
        this.color = color;
        this.categoryId = categoryId;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
    }

    public void setRateAvg(float rateAvg) {
        this.rateAvg = rateAvg;
    }

    public float getRateAvg() {
        return rateAvg;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
