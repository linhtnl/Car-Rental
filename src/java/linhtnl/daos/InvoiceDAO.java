/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import linhtnl.DTOs.CarDTO;
import linhtnl.DTOs.Invoice;
import linhtnl.db.LinhConnection;

/**
 *
 * @author ASUS
 */
public class InvoiceDAO {

    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    private final int numberOfCarAPage = 20;

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

    public boolean feedback(Invoice invoice) throws Exception {
        boolean check = false;
        int count = 0;
        try {
            //1.Set isFeedback in invoice_detail
            //2.Update ratingAvg in car_detail
            con = LinhConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "";
            for (CarDTO dto : invoice.getList()) {
                //Step 1
                sql = "update invoice set isFeedback=1 where  id ='" + invoice.getId() + "'";
                pst = con.prepareStatement(sql);
                count += pst.executeUpdate();
                //Get avg
                sql = "select avg from car_detail where licensePlate ='" + dto.getLicensePlate() + "'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                float avg = 0;
                if (rs.next()) {
                    if (rs.getFloat("avg") == 0) {
                        avg = dto.getRating();
                    } else {
                        avg = (dto.getRating() + rs.getFloat("avg")) / 2; //Calculate RatingAvg
                    }
                }
                //Step 2
                sql = "Update car_detail set avg=" + new DecimalFormat("#.##").format(avg) + " where licensePlate='" + dto.getLicensePlate() + "'";
                pst = con.prepareStatement(sql);
                count += pst.executeUpdate();
            }
            con.commit();
            if (count == invoice.getList().size() + 1) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteInvoice(String id) throws Exception {
        boolean check = true;
        try {
            String sql = "UPDATE invoice\n"
                    + "set isDelete=1\n"
                    + "where id = '" + id + "'";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            check = pst.executeUpdate() == 1 ? true : false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public Vector<Invoice> searchInvoice(String dateReturn, String dateRent,String name) throws Exception {
        Vector<Invoice> list = new Vector<>();
        try {
            String wSe = "";
            if(!dateReturn.equals("")){
                wSe+= " and dateSubmit >='" + dateRent +"' ";
            }
            if(!dateRent.equals("")){
               
                wSe+= " and dateSubmit <='" + dateReturn + "' ";
            } 
            if(!name.equals("")){
              
                wSe+= "  and id like '%"+name+"%' ";
            }
            String sql = "select id,dateSubmit from invoice "
                    + "where isDelete=0 " + wSe
                    + "ORDER BY dateSubmit desc";
      
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Invoice i = new Invoice(rs.getString("id"), rs.getString("dateSubmit"));
                list.add(i);
            }
            for (Invoice i : list) {
                sql = "select  ID.licensePlate,ID.price,dateRent,dateReturn,ID.pickupLocation,ID.returnLocation,C.name\n"
                        + "from invoice_detail ID, Car C, Car_Detail CD\n"
                        + "where C.CarId = CD.CarID and CD.licensePlate = ID.licensePlate and  invoiceId = '" + i.getId() + "'   ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                Vector<CarDTO> sublist = new Vector<>();
                while (rs.next()) {
                    CarDTO dto = new CarDTO();
                    dto.setLicensePlate(rs.getString("licensePlate"));
                    dto.setPrice(rs.getFloat("price"));
                    dto.setDateRent(rs.getString("dateRent"));
                    dto.setDateReturn(rs.getString("dateReturn"));
                    String p = rs.getString("pickupLocation");
                    if (p.equalsIgnoreCase("null")) {
                        p = "Unknown";
                    }
                    dto.setPickup(p);
                    String r = rs.getString("returnLocation");
                    if (r.equalsIgnoreCase("null")) {
                        r = "Unknown";
                    }
                    dto.setReturnLocation(r);
                    dto.setName(rs.getString("name"));
                    sublist.add(dto);
                }
                i.setList(sublist);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public Invoice getInvoiceById(String id) throws Exception {
        Invoice invoice = null;
        try {
            String sql = "select v.percentage \n"
                    + "from Invoice I, voucher V\n"
                    + "where I.voucherId=V.id and I.id = '" + id + "' ";
            System.out.println(sql);
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            invoice = new Invoice();
            invoice.setId(id);
            if (rs.next()) {
                invoice.setDiscount(rs.getInt("percentage")); //Set if has voucher
            }
            //detail
            sql = "select  ID.licensePlate,ID.price,dateRent,dateReturn,ID.pickupLocation,ID.returnLocation,C.name\n"
                    + "from invoice_detail ID, Car C, Car_Detail CD\n"
                    + "where C.CarId = CD.CarID and CD.licensePlate = ID.licensePlate and  invoiceId = '" + id + "'   ";
            System.out.println(sql);
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            Vector<CarDTO> sublist = new Vector<>();
            while (rs.next()) {
                CarDTO dto = new CarDTO();
                dto.setLicensePlate(rs.getString("licensePlate"));
                dto.setPrice(rs.getFloat("price"));
                dto.setDateRent(rs.getString("dateRent"));
                dto.setDateReturn(rs.getString("dateReturn"));
                String p = rs.getString("pickupLocation");
                if (p.equalsIgnoreCase("null")) {
                    p = "Unknown";
                }
                dto.setPickup(p);
                String r = rs.getString("returnLocation");
                if (r.equalsIgnoreCase("null")) {
                    r = "Unknown";
                }
                dto.setReturnLocation(r);
                dto.setName(rs.getString("name"));
                sublist.add(dto);
            }
            invoice.setList(sublist);
        } finally {
            closeConnection();
        }
        return invoice;
    }

    public Vector<Invoice> init() throws Exception {
        Vector<Invoice> list = new Vector<>();
        try {
            String sql = "select id  ,dateSubmit , isFeedback\n"
                    + "from Invoice\n"
                    + "where isDelete= 0\n"
                    + "ORDER BY dateSubmit desc";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            //list init
            while (rs.next()) {
                Invoice i = new Invoice(rs.getString("id"), rs.getString("dateSubmit"));
                i.setIsFeedback(rs.getBoolean("isFeedback"));
                list.add(i);
            }

            //detail
            for (Invoice i : list) {
                //Update voucher discount
                sql = "select v.percentage \n"
                        + "from Invoice I, voucher V\n"
                        + "where I.voucherId=V.id and i.id ='" + i.getId() + "'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    i.setDiscount(rs.getInt("percentage"));
                }
                sql = "select  ID.licensePlate,ID.price,dateRent,dateReturn,ID.pickupLocation,ID.returnLocation,C.name\n"
                        + "from invoice_detail ID, Car C, Car_Detail CD\n"
                        + "where C.CarId = CD.CarID and CD.licensePlate = ID.licensePlate and  invoiceId = '" + i.getId() + "'   ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                Vector<CarDTO> sublist = new Vector<>();
                while (rs.next()) {
                    CarDTO dto = new CarDTO();
                    dto.setLicensePlate(rs.getString("licensePlate"));
                    dto.setPrice(rs.getFloat("price"));
                    dto.setDateRent(rs.getString("dateRent"));
                    dto.setDateReturn(rs.getString("dateReturn"));

                    String p = rs.getString("pickupLocation");
                    if (p.equalsIgnoreCase("null")) {
                        p = "Unknown";
                    }
                    dto.setPickup(p);
                    String r = rs.getString("returnLocation");
                    if (r.equalsIgnoreCase("null")) {
                        r = "Unknown";
                    }
                    dto.setReturnLocation(r);
                    dto.setName(rs.getString("name"));
                    sublist.add(dto);
                }
                i.setList(sublist);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
