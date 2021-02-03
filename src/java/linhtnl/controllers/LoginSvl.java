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
import linhtnl.daos.AccountDAO;
import linhtnl.daos.CarDAO;
import linhtnl.util.Path;
import linhtnl.util.PathAdmin;
import linhtnl.util.PathUser;

/**
 *
 * @author ASUS
 */
public class LoginSvl extends HttpServlet {

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
            out.println("<title>Servlet LoginSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginSvl at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String url = Path.LOGIN;
        HttpSession session = request.getSession();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            session.setAttribute("searchDTO", null);
            AccountDAO dao = new AccountDAO();
            int check = dao.login(email, password);
            //-1: not exist
            // 0: wrong password
            // 1: valid
            Account acc = new Account();
            acc.setEmail(email);
            acc.setPassword(password);

            if (check == 1) {
                acc = dao.getAccount(email);
                if (acc.getStatus().equalsIgnoreCase("New")) {
                    acc.setAccountError("This account is not active yet");
                } else if (acc.getStatus().equalsIgnoreCase("Active")) {
                    //SET LIST AGAIN
                    //--SET LIST
                    //--SET PAGE NUM
                    //--SET TOTAL PAGE
                    CarDAO cDao = new CarDAO();

                    //DIVIDE ROLE
                    session.setAttribute("listCar", cDao.getAllCar(1));
                    url = Path.USER_HOME;
                    session.setAttribute("Url", PathUser.INDEX);
                    session.setAttribute("totalPage", cDao.getTotalPage());

                    session.setAttribute("pageNum", 1);

                }
            } else if (check == 0) {
                acc.setPassword_Error("Wrong password!");
            } else if (check == -1) {
                acc.setAccountError("This account is not found!");
            }
            session.setAttribute("ACC", acc);

        } catch (Exception e) {
            log("ERROR at LoginSvl: " + e.getMessage());
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
