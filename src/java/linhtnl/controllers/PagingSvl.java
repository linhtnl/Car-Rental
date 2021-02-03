/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhtnl.DTOs.Account;
import linhtnl.DTOs.CarDTO;
import linhtnl.DTOs.SearchDTO;
import linhtnl.daos.CarDAO;
import linhtnl.util.Path;
import linhtnl.util.PathUser;

/**
 *
 * @author ASUS
 */
public class PagingSvl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PagingSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PagingSvl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String url = Path.ERROR;
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("ACC");
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            CarDAO dao = new CarDAO();
            boolean flag = true; //Search 
            SearchDTO dto = (SearchDTO) session.getAttribute("searchDTO");
            if (dto == null) {
                flag = false;//Khong SEARCH
            }
            Vector<CarDTO> list = new Vector<>();
            int totalPage = 0;
            /*
             1.Get list to display by pageNum
             2.Get totalPage
             3.Set URL
             */

            if (acc == null) { //For guest
                if (flag) {//search
                    list = dao.searchCar(pageNum, dto.getNameCar(), dto.getCategoryId(), dto.getCarNum(), dto.getDateRent(), dto.getDateReturn());
                    totalPage = dao.getTotalSearchPage(dto.getNameCar(), dto.getCategoryId(), dto.getCarNum(), dto.getDateRent(), dto.getDateReturn());
                } else {
                    list = dao.getAllCar(pageNum);
                    totalPage = dao.getTotalPage();
                }
                url = Path.INDEX;
            } else {

                if (flag) {//search                       
                    list = dao.searchCar(pageNum, dto.getNameCar(), dto.getCategoryId(), dto.getCarNum(), dto.getDateRent(), dto.getDateReturn());
                    totalPage = dao.getTotalSearchPage(dto.getNameCar(), dto.getCategoryId(), dto.getCarNum(), dto.getDateRent(), dto.getDateReturn());
                } else {//Khong search
                    list = dao.getAllCar(pageNum);
                    totalPage = dao.getTotalPage();
                }
                url = Path.USER_HOME;
                session.setAttribute("Url", PathUser.INDEX);

            }
            System.out.println("totalpage: " + totalPage);
            session.setAttribute("totalPage", totalPage);
            session.setAttribute("listCar", list);
            session.setAttribute("pageNum", pageNum);
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at PagingSvl: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
