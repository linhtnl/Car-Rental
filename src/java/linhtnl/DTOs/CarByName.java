/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.DTOs;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class CarByName implements Serializable{

    private String categoryID, categoryName,fuel,img;
    private String carName, carID;
    private int year,quantity;
    private Vector<CarDTO> list;
    private int noOfSeats;
    
    public CarByName(String categoryID, String carName, String carID, int year) {
        this.categoryID = categoryID;
        this.carName = carName;
        this.carID = carID;
        this.year = year;
    }

    public CarByName() {
        carID = carName = categoryID = categoryName = "";
        list = new Vector<>();
    }

    public CarByName(String categoryID, String categoryName, String carName, String carID, Vector<CarDTO> list) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.carName = carName;
        this.carID = carID;
        this.list = list;
    }

    public String getCarID() {
        return carID;
    }
   public int getSize(){
       return list.size();
   }
    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public String getFuel() {
        return fuel;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCarName() {
        return carName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Vector<CarDTO> getList() {
        return list;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setList(Vector<CarDTO> list) {
        this.list = list;
    }

}
