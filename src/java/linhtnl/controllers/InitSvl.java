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
import linhtnl.DTOs.CategoryDTO;
import linhtnl.daos.CarDAO;
import linhtnl.daos.CategoryDAO;
import linhtnl.util.Path;

/**
 *
 * @author ASUS
 */
public class InitSvl extends HttpServlet {

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
            out.println("<title>Servlet InitSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InitSvl at " + request.getContextPath() + "</h1>");
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
        String url = Path.INDEX;
        HttpSession session = request.getSession();
        try {
            CarDAO dao = new CarDAO();
            CategoryDAO Cdao = new CategoryDAO();
            Vector<CarDTO> list = new Vector<>();
            int totalPage = 0;
            list = dao.getAllCar(1);
            totalPage = dao.getTotalPage();
            if (session.getAttribute("ACC") != null) {
                url = Path.USER_HOME;
            } 
            session.setAttribute("listCar", list);
            session.setAttribute("listCategory", Cdao.getAllCategory());
            session.setAttribute("pageNum", 1);
            session.setAttribute("totalPage", totalPage);
        } catch (Exception e) {
            log("ERROR at InitSvl: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
        }

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
        String url = Path.INDEX;
        HttpSession session = request.getSession();
        try {
            CarDAO dao = new CarDAO();
            CategoryDAO Cdao = new CategoryDAO();
            Vector<CarDTO> list = dao.getAllCar(1);
            int totalPage = dao.getTotalPage();;
            
            if (session.getAttribute("ACC") != null) {
                    url = Path.USER_HOME;           
            }
            session.setAttribute("listCar", list);
            session.setAttribute("listCategory", Cdao.getAllCategory());
            session.setAttribute("pageNum", 1);
            session.setAttribute("totalPage", totalPage);

        } catch (Exception e) {
            log("ERROR at InitSvl: " + e.getMessage());
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
