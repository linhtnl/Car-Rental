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
public class Voucher implements Serializable{
    private String id;
    private int percentage;
    private boolean available;
    public Voucher() {
        percentage =0;
        id="";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public Voucher(String id, int percentag) {
        this.id = id;
        if(percentag==0) percentag=0;
        this.percentage = percentag;
    }

    public String getId() {
        return id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return id+" - "+percentage;
    }
    
    
}
