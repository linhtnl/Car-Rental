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
public class Invoice implements Serializable{
    private String id, dateSubmit;
    private int discount;
    private boolean isFeedback;
    private Vector<CarDTO> list;
    public Invoice() {
    }

    public String getId() {
        return id;
    }

    public void setIsFeedback(boolean isFeedback) {
        this.isFeedback = isFeedback;
    }

    public boolean isIsFeedback() {
        return isFeedback;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setList(Vector<CarDTO> list) {
        this.list = list;
    }

    public Vector<CarDTO> getList() {
        return list;
    }
    public Invoice(String id, String dateSubmit) {
        this.id = id;
        this.dateSubmit = dateSubmit;
    }

    public String getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(String dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

  
    
}
