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
import linhtnl.DTOs.Invoice;
import linhtnl.DTOs.SearchDTO;
import linhtnl.daos.InvoiceDAO;
import linhtnl.util.Path;

/**
 *
 * @author ASUS
 */
public class InvoiceManagementSvl extends HttpServlet {

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
            out.println("<title>Servlet InvoiceManagementSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InvoiceManagementSvl at " + request.getContextPath() + "</h1>");
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
            if (acc == null) {
                url = Path.LOGIN;
            } else {
                String subaction = request.getParameter("subaction");
                if (subaction == null) {
                    subaction = "";
                }
                InvoiceDAO dao = new InvoiceDAO();
                if (subaction.equals("delete")) {
                    String id = request.getParameter("id");
                    if (dao.deleteInvoice(id)) {
                        Vector<Invoice> list = dao.init();
                        session.setAttribute("listInvoice", list);
                        url = Path.VIEW_INVOICE;
                    }
                }
                else if (subaction.equals("search")) {
                    String dateRent=request.getParameter("dateRent");
                    String dateReturn = request.getParameter("dateReturn");
                    String name = request.getParameter("invoiceName");
                    SearchDTO dto = new SearchDTO();
                    dto.setDateRent(dateRent); dto.setDateReturn(dateReturn);dto.setName(name);
                    Vector<Invoice> list = dao.searchInvoice(dateReturn, dateRent,name);
                    session.setAttribute("listInvoice", list);
                    session.setAttribute("searchInfo", dto);
                    url = Path.VIEW_INVOICE;
                }else {
                    //View invoice
                    Vector<Invoice> list = dao.init();
                    session.setAttribute("listInvoice", list);
                    session.setAttribute("searchInfo", null);
                    url = Path.VIEW_INVOICE;

                }
            }
        } catch (Exception e) {
            log("ERROR at InvoiceManagementSvl:" + e.getMessage());
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
