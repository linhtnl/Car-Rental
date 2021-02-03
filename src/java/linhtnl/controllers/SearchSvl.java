/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class SearchSvl extends HttpServlet {

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
            out.println("<title>Servlet SearchSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchSvl at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String url = Path.ERROR;
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("ACC");
            CarDAO dao = new CarDAO();     
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               
            String carName = (request.getParameter("carName") == null) ? "" : request.getParameter("carName");       
            String category = (request.getParameter("category") == null) ? ""  :request.getParameter("category");
            int carNum = (request.getParameter("carNum") == null || request.getParameter("carNum").isEmpty()) ? 1 : Integer.parseInt(request.getParameter("carNum")) ;
            String dateRent = (request.getParameter("dateRent")== null || request.getParameter("dateRent").isEmpty()) ? formatter.format(cal.getTime()) : request.getParameter("dateRent");
            String dateReturn = (request.getParameter("dateReturn")== null || request.getParameter("dateReturn").isEmpty()) ? formatter.format(cal.getTime()) : request.getParameter("dateRent");       
            SearchDTO searchDTO = new SearchDTO(carName, category, carNum, dateReturn, dateRent);
            session.setAttribute("searchDTO", searchDTO);
            Vector<CarDTO> list = dao.searchCar(1, carName, category,carNum, dateRent, dateReturn);
            /*
             1. Get carName - Price from to - Category
             2. Return list -> set listCar
             3. Set pageNum
             4. set totalPage
             5. url
             */
            if (acc == null) {//for Guest
                url = Path.INDEX;
            } else {          
                    url=Path.USER_HOME;          
            }
            session.setAttribute("listCar", list);
            session.setAttribute("pageNum", 1);
            session.setAttribute("totalPage", dao.getTotalSearchPage(carName, category, carNum, dateRent, dateReturn));
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at SearchSvl: " + e.getMessage());
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
