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
import linhtnl.DTOs.Voucher;
import linhtnl.db.LinhConnection;

/**
 *
 * @author ASUS
 */
public class VoucherDAO {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

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

    public Voucher getVoucher(String id) throws Exception {
        Voucher voucher = new Voucher();
        voucher.setId(id);
        voucher.setAvailable(false);
        String sql = "Select percentage, expiryDate from voucher where id= '" + id + "'";
        try {
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {             
                voucher.setPercentage(rs.getInt("percentage"));
                if(Integer.compare(Integer.parseInt(rs.getString("expiryDate").replaceAll("-", "")), Integer.parseInt(formatter.format(Calendar.getInstance().getTime()).replaceAll("/", "")))==1){
                    voucher.setAvailable(true);
                }
            }
        } finally {
            closeConnection();
        }
        return voucher;
    }
}
