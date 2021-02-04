/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import linhtnl.DTOs.Account;
import linhtnl.db.LinhConnection;

/**
 *
 * @author ASUS
 */
public class AccountDAO {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean updateStatus(String email) throws Exception {
        boolean check = true;
        try {
            String sql = "update Account set status='Active' where email ='" + email + "'";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            check = pst.executeUpdate() == 1 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean createNew(Account acc) throws Exception {
        boolean check = true;
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String date = f.format(Calendar.getInstance().getTime());
            String sql = "Insert INTO Account(email,phone,name,address,createDate,status,password)\n"
                    + "values('" + acc.getEmail() + "','" + acc.getPhone() + "','" + acc.getName() + "','" + acc.getAddress() + "','" + date + "','New','" + acc.getPassword() + "')";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            check = pst.executeUpdate() == 1 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean emailExist(String email) throws Exception {
        boolean check = false;
        try {
            String sql = "select * from Account where email='" + email + "'";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public Account getAccount(String email) throws Exception {
        Account acc = new Account();
        try {
            String sql = "select phone, name, address, status \n"
                    + "from account\n"
                    + "where email = ? ";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String status = rs.getString("status");

                acc.setPhone(phone);
                acc.setName(name);
                acc.setAddress(address);
                acc.setStatus(status);

            }
        } finally {
            closeConnection();
        }
        return acc;
    }

    public int login(String email, String password) throws Exception {
        //-1: not exist
        // 0: wrong password
        // 1: valid
        int check = -1;
        try {
            System.out.println(email);
            String sql = "Select email, password from Account where email = '" + email + "'";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            System.out.println(sql);
            if (rs.next()) {
                String mail = rs.getString("email");
                String pass = rs.getString("password");
                if (mail.equals(email)) {
                    check = 0;
                    if (pass.equals(password)) {
                        check = 1;
                    }
                }

            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
