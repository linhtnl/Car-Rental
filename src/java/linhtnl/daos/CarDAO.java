/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import linhtnl.DTOs.CarDTO;
import linhtnl.db.LinhConnection;

/**
 *
 * @author ASUS
 */
public class CarDAO implements Serializable {

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

    public int getTotalSearchPage(String carName, String category, int carNum, String dateRent, String dateReturn) throws Exception {;
        Vector<CarDTO> list = new Vector<>();
        try {
            String sql = "SELECT CarId,C.name,color,year,C.categoryID,price,quantity,rateAvg\n"
                    + "FROM Car C, Category CA, Invoice I \n"
                    + "where C.categoryID=Ca.CategoryId  and C.categoryID like '" + category + "' and C.name like '%" + carName + "%' and quantity >= " + carNum + " \n";

            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            System.out.println(sql);
            while (rs.next()) {
                String carId = rs.getString("carId");
                String name = rs.getString("name");
                String color = rs.getString("color");
                int year = rs.getInt("year");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String categoryId = rs.getString("categoryID");
                CarDTO dto = new CarDTO(carId, name, color, categoryId, year, quantity, price);
                dto.setRateAvg(rs.getFloat("rateAvg"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        //Check car is renting
        Iterator<CarDTO> iterator = list.iterator();

        while (iterator.hasNext()) {
            CarDTO dto = iterator.next();
            HashMap<String, Integer> temp = checkQuantity(dateRent, dateReturn);
            for (String x : temp.keySet()) {
                if (dto.getCarId().equals(x)) {
                    if (dto.getQuantity() - temp.get(x) < carNum) {
                        list.remove(x);
                    } else {
                        dto.setQuantity(dto.getQuantity() - temp.get(x));
                    }
                }
            }
        }

        return (int) Math.ceil(list.size() * 1.0 / numberOfCarAPage);
    }

    public Vector<CarDTO> searchCar(int page, String carName, String category, int carNum, String dateRent, String dateReturn) throws Exception {
        Vector<CarDTO> list = new Vector<>();
        try {

            String sql = "DECLARE @PageNumber AS INT  \n"
                    + "SET @PageNumber= " + page + " \n"
                    + "SELECT CarId,C.name,color,year,C.categoryID,price,quantity,rateAvg\n"
                    + "FROM Car C, Category CA, Invoice I \n"
                    + "where C.categoryID=Ca.CategoryId  and C.categoryID like '" + category + "' and C.name like '%" + carName + "%' and quantity >= " + carNum + " \n"
                    + "ORDER BY year DESC  \n"
                    + "OFFSET (@PageNumber-1) * " + numberOfCarAPage + "  ROWS  \n"
                    + "FETCH NEXT " + numberOfCarAPage + "  ROWS Only ";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
//            pst.setInt(1, page);
//            pst.setString(2, "%" + carName + "%");
//            pst.setString(3, "%" + category + "%");
//            pst.setInt(4, carNum);
            rs = pst.executeQuery();
//            System.out.println(sql);
            while (rs.next()) {
                String carId = rs.getString("carId");
                String name = rs.getString("name");
                String color = rs.getString("color");
                int year = rs.getInt("year");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String categoryId = rs.getString("categoryID");
                CarDTO dto = new CarDTO(carId, name, color, categoryId, year, quantity, price);
                dto.setRateAvg(rs.getFloat("rateAvg"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        //Check car is renting
        Iterator<CarDTO> iterator = list.iterator();

        while (iterator.hasNext()) {
            CarDTO dto = iterator.next();
            HashMap<String, Integer> temp = checkQuantity(dateRent, dateReturn);
            for (String x : temp.keySet()) {
                if (dto.getCarId().equals(x)) {
                    if (dto.getQuantity() - temp.get(x) < carNum) {
                        list.remove(x);
                    } else {
                        dto.setQuantity(dto.getQuantity() - temp.get(x));
                    }
                }
            }
        }
//            System.out.println("---------------------");
//             for (CarDTO carDTO : list) {
//                System.out.println(carDTO.getCarId()+" - "+carDTO.getQuantity());
//            }

        return list;
    }

    private HashMap<String, Integer> checkQuantity(String dateRent, String dateReturn) throws Exception {
        HashMap<String, Integer> list = new HashMap();

        try {
            String sql = "select carId, quantity\n"
                    + "from invoice I, invoice_detail ID\n"
                    + "where I.id = id.invoiceId and dateReturn >= '" + dateReturn + "' and  dateOrder >= '" + dateRent + "'";

            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
//            pst.setString(1, dateReturn);
//            pst.setString(2, dateRent);
//            System.out.println(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String carId = rs.getString("carId");
                int quantity = rs.getInt("quantity");
                if (list.containsKey(carId)) {
                    list.put(carId, list.get(carId) + quantity);
                } else {
                    list.put(carId, quantity);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getTotalPage() throws Exception {
        int num = 0;
        try {
          
            String sql = "SELECT count(carId) as total\n"
                    + "FROM Car C, Category CA\n"
                    + "where C.categoryID=Ca.CategoryId and quantity > 0" ;
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                num = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return (int) Math.ceil(num * 1.0 / numberOfCarAPage);
    }

    public Vector<CarDTO> getAllCar(int pageNum) throws Exception {// 1 admin
        Vector<CarDTO> result = new Vector<>();
        try {
            
            String sql = "DECLARE @PageNumber AS INT  \n"
                    + "SET @PageNumber=?\n"
                    + "SELECT CarId,C.name,color,year,C.categoryID,CA.name,price,quantity,rateAvg\n"
                    + "FROM Car C, Category CA\n"
                    + "where C.categoryID=Ca.CategoryId and quantity > 0\n" 
                    + "ORDER BY year DESC  \n"
                    + "OFFSET (@PageNumber-1) *  " + numberOfCarAPage + "  ROWS  \n"
                    + "FETCH NEXT  " + numberOfCarAPage + "  ROWS Only ";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, pageNum);
            rs = pst.executeQuery();
            while (rs.next()) {
                String carId = rs.getString("carId");
                String name = rs.getString("name");
                String color = rs.getString("color");
                int year = rs.getInt("year");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String categoryId = rs.getString("categoryID");
                CarDTO dto = new CarDTO(carId, name, color, categoryId, year, quantity, price);
                dto.setRateAvg(rs.getFloat("rateAvg"));
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
