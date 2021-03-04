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
import linhtnl.DTOs.Invoice;
import linhtnl.daos.InvoiceDAO;
import linhtnl.util.Path;

/**
 *
 * @author ASUS
 */
public class FeedbackSvl extends HttpServlet {

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
            out.println("<title>Servlet FeedbackSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackSvl at " + request.getContextPath() + "</h1>");
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
        String url = Path.ERROR;
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("ACC");
            if (acc == null) {
                url = Path.LOGIN;
            } else {
                String invoiceId = request.getParameter("invoiceID");
                Vector<Invoice> list = (Vector<Invoice>) session.getAttribute("listInvoice");
                Invoice invoice = new Invoice();
                for (Invoice i : list) {
                    if (i.getId().equals(invoiceId)) {
                        invoice = i;
                    }
                }
                session.setAttribute("invoiceFB", invoice);
                url = Path.FEEDBACK;
            }
        } catch (Exception e) {
            log("ERROR at doGet of FeedbackSvl:" + e.getMessage());
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
        // processRequest(request, response);
        String url = Path.ERROR;
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("ACC");
            if (acc == null) {
                url = Path.LOGIN;
            } else {
                int flag = 1;
                Invoice invoice = (Invoice) session.getAttribute("invoiceFB");
                for (CarDTO dto : invoice.getList()) {
                    int rating = Integer.parseInt(request.getParameter("rating" + dto.getLicensePlate()));
                    if (rating >= 0 && rating <= 10) {
                        dto.setRating(rating);
                    } else {
                        flag = 0;//ERROR rating                      
                    }
                    if (flag == 0) {
                        break;
                    }
                }
                if (flag == 1) {
                    //save in database
                    InvoiceDAO dao = new InvoiceDAO();
                    if (dao.feedback(invoice)) {
                        session.setAttribute("listInvoice", dao.init(acc.getEmail()));
                        url = Path.VIEW_INVOICE;
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at doPost of FeedbackSvl:" + e.getMessage());
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
