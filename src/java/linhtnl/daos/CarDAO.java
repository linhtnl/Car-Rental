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
import linhtnl.DTOs.CarByName;
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
        int count=1;
        int total=0;
        int size = searchCar(count, carName, category, carNum, dateRent, dateReturn).size();
        total+=size;
        while(size==20){
            count++;
            size =searchCar(count, carName, category, carNum, dateRent, dateReturn).size();
            total+=size;
        }
        
        return (int) Math.ceil(total * 1.0 / numberOfCarAPage);
    }

    public Vector<CarByName> searchCar(int page, String carName, String categoryId, int carNum, String dateRent, String dateReturn) throws Exception { 
        Vector<CarDTO> list = new Vector<>();
        Vector<CarByName> cbnlist = new Vector<>();
        try {
            /*
             1. Dựa vào categoryId  và name  và number để tìm ra những Car name thỏa mãn trước -> rs: carId thõa mãn
             2. Lấy chi tiết xe dựa trên car name
             3. Loại trừ lại những biển số xe đã được cho thuê.
             */
            //Step 1
            String sql = "SELECT C.CarId ,C.categoryID,C.name,C.year,img, noOfSeats, fuel \n"
                    + "FROM Car C, Car_Detail CD, Category CA   \n"
                    + "where C.categoryID=Ca.CategoryId  and CD.CarID=C.CarId and CA.categoryID like '" + categoryId + "' and C.name like '%" + carName + "%' \n"
                    + "Group by C.CarId,C.categoryID,C.name,C.year,img, noOfSeats,fuel\n"
                    + "Having count(licensePlate)>=" + carNum;
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String carId = rs.getString("carId");
                String name = rs.getString("name");
                int year = rs.getInt("year");
                String cateID = rs.getString("categoryID");
                int noOfSeats = rs.getInt("noOfSeats");
                String img  = rs.getString("img");
                CarByName car = new CarByName(cateID, name, carId, year);
                car.setImg(img); car.setNoOfSeats(noOfSeats);car.setFuel(rs.getString("fuel"));
                cbnlist.add(car);
            }
            //Step 2:
            for (CarByName car : cbnlist) {
                sql = "SELECT licensePlate, color,price, pickupLocation, returnLocation\n"
                        + "FROM Car_Detail \n"
                        + "where CarID='" + car.getCarID() + "'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String color = rs.getString("color");
                    String licensePlate = rs.getString("licensePlate");
                    float price = rs.getFloat("price");
                    CarDTO dto = new CarDTO(color, licensePlate, price);
                    dto.setPickup(rs.getString("pickupLocation"));
                    dto.setReturnLocation(rs.getString("returnLocation"));
                    list.add(dto);
                }
                car.setList(list);
                list = new Vector<>();
            }
        } finally {
            closeConnection();
        }
        //Step 3: Check car is renting     
        cbnlist = checkQuantity(dateRent, dateReturn, cbnlist);

        //pagination
        int size = cbnlist.size();
        int[] arr = pagination(page, size);
        return new Vector<CarByName>(cbnlist.subList(arr[0], arr[1]));
    }

    private int[] pagination(int page, int size) {
        int start = (page - 1) * numberOfCarAPage;
        int end = 0;
        if (size / (page * numberOfCarAPage) < 1) {
            end = size;
        } else {
            end = page * numberOfCarAPage;
        }
        int arr[] = {start, end};
        return arr;
    }

    private Vector<CarByName> checkQuantity(String dateRent, String dateReturn, Vector<CarByName> listCar) throws Exception {
        if(listCar.size()==0) return listCar;
        Vector<Integer> index = new Vector<>();
        int count=0;
        try {
            String sql = "select ID.licensePlate\n"
                    + "from invoice I, invoice_detail ID \n"
                    + "where I.id = id.invoiceId and dateReturn >= '" + dateReturn + "' and  dateRent >= '" + dateRent + "'";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String plate = rs.getString("licensePlate");
                for (CarByName car : listCar) {
                    for (CarDTO dto : car.getList()) {
                        if (dto.getLicensePlate().equals(plate)) {
                            index.add(count);
                        }
                        count++;
                    }
                }

            }
        } finally {
            closeConnection();
        }
        for (Integer integer : index) {
            listCar.remove(integer);
        }
        return listCar;
    }

    public int getTotalPageByCarPlates(String carID) throws Exception {
        int num = 0;
        try {
            String sql = "select count(licensePlate) as total from Car_Detail where CarID = ?";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, carID);
            rs = pst.executeQuery();
            if (rs.next()) {
                num = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return (int) Math.ceil(num * 1.0 / numberOfCarAPage);
    }

    public int getTotalPageByCarName() throws Exception {
        int num = 0;
        try {
            String sql = "select count(carId) as total from Car";
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

    public Vector<CarByName> getAllCar(int pageNum) throws Exception {
        Vector<CarByName> cbnlist = new Vector<>();
        Vector<CarDTO> result = new Vector<>();
        try {
            //Get CarID
            String sql = "select c.carID, name, year, categoryID , img,noOfSeats,fuel \n"
                    + "from car c, Car_Detail cd\n"
                    + "where c.CarId=cd.CarID\n"
                    + "group by c.carID, name, year, categoryID ,img,noOfSeats,fuel";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String carId = rs.getString("carId");
                String name = rs.getString("name");
                int year = rs.getInt("year");
                String categoryId = rs.getString("categoryID");
                CarByName dto = new CarByName(categoryId, name, carId, year);
                dto.setImg(rs.getString("img"));
                dto.setNoOfSeats(rs.getInt("noOfSeats"));
                dto.setFuel(rs.getString("fuel"));
                cbnlist.add(dto);
            }
            //Get list plates by CarId
            for (CarByName car : cbnlist) {
                sql = "select licensePlate,price,pickupLocation,color,returnLocation from Car_Detail where CarID=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, car.getCarID());
                rs = pst.executeQuery();
                while (rs.next()) {
                    String licensePlate = rs.getString("licensePlate");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    CarDTO dto = new CarDTO(color, licensePlate, price);
                    dto.setPickup(rs.getString("pickupLocation"));
                    dto.setReturnLocation(rs.getString("returnLocation"));
                    result.add(dto);
                }
                car.setList(result);
                result = new Vector<>();
            }
        } finally {
            closeConnection();
        }
        //pagination
        int size = cbnlist.size();
        int[] arr = pagination(pageNum, size);
        return new Vector<CarByName>(cbnlist.subList(arr[0], arr[1]));
    }
}
