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

    private String carId, name, color, categoryId,licensePlate,dateRent,dateReturn,location;
    private int year, totalCar,rating;
    private float price,rateAvg;
    private boolean available;
    
    public CarDTO(String color, String licensePlate, float price) {
        this.color = color;
        this.licensePlate = licensePlate;
        this.price = price;
        available=true;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    
    public CarDTO() {
        available=true;
    }

    public CarDTO(String carId, String name, String color, String categoryId, int year, float price,String licensePlate) {
        this.carId = carId;
        this.name = name;
        this.color = color;
        this.categoryId = categoryId;
        this.year = year;
        this.licensePlate=licensePlate;
        this.price = price;
        available=true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public String getDateRent() {
        return dateRent;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }
     
   public String getLicensePlate() {
        return licensePlate;
    }

    

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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

    public int getTotalCar() {
        return totalCar;
    }

    public void setTotalCar(int totalCar) {
        this.totalCar = totalCar;
    }

   

   

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "----------"+this.licensePlate+"----------"+
                "\nDate rent: "+this.dateRent+
                "\nDate return: "+this.dateReturn+
                "\nStatus: "+this.isAvailable()+
                "\n----------------------";
    }
    
}
