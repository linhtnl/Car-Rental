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
import java.util.Vector;
import linhtnl.DTOs.CategoryDTO;
import linhtnl.db.LinhConnection;

/**
 *
 * @author ASUS
 */
public class CategoryDAO implements Serializable{
    PreparedStatement pst ;
    Connection con;
    ResultSet rs;
    private void closeConnection()throws Exception{
        if(rs!=null) rs.close();
        if(pst!=null) pst.close();
        if(con!=null) con.close();
    }
    public Vector<CategoryDTO> getAllCategory()throws Exception{
        Vector<CategoryDTO> result = new Vector<>();
        try{
            String sql = "Select categoryId, name from Category";
            con = LinhConnection.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String id = rs.getString("categoryId");
                String name = rs.getString("name");
                CategoryDTO dto = new CategoryDTO(id, name);
                result.add(dto);
            }
           
        }finally{
            closeConnection();
        }
        return result;
    }
}
