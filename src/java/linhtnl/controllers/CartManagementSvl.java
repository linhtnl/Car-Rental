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
import linhtnl.DTOs.Voucher;
import linhtnl.daos.CarDAO;
import linhtnl.daos.InvoiceDAO;
import linhtnl.daos.VoucherDAO;
import linhtnl.util.Path;

/**
 *
 * @author ASUS
 */
public class CartManagementSvl extends HttpServlet {

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
            out.println("<title>Servlet CartManagementSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartManagementSvl at " + request.getContextPath() + "</h1>");
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
            Account ACC = (Account) session.getAttribute("ACC");
            Vector<CarDTO> cart = (Vector<CarDTO>) session.getAttribute("CART");
            String action = request.getParameter("subaction");
            System.out.println(action);
            int flag = 0;
            if (cart == null) {
                cart = new Vector<>();
            }
            if (ACC == null) {
                url = Path.LOGIN;
            } else {
                CarDAO dao = new CarDAO();

                System.out.println(action);
                if (action == null) {
                    action = "";
                }
                if (action.equals("checkout")) {
                    if (ACC.getEmail() != "") {
                        String voucherId = request.getParameter("voucher");
                        VoucherDAO vDAO = new VoucherDAO();
                        Voucher voucher = vDAO.getVoucher(voucherId);
                        session.setAttribute("voucher", null);
                        if (voucher.isAvailable() == false) {
                            voucherId = null;
                        } else {
                            voucherId = "\'" + voucherId + "\'";
                        }
                        String id = dao.checkout(cart, ACC.getEmail(), voucherId);
                        if (!id.equals("")) {
                            session.setAttribute("CART", new Vector<CarDTO>());
                            InvoiceDAO iDao = new InvoiceDAO();
                            Invoice invoice = iDao.getInvoiceById(id);
                            if (invoice != null) {
                                session.setAttribute("invoiceFB", invoice);
                                url = Path.FEEDBACK; //Xem hóa đơn
                            }
                        }
                    }
                } else if (action.equals("checkQuantity")) {
                    for (CarDTO dto : cart) {
                        dto = dao.checkAvailable(dto); //set status is available for dto
                    }
                    String voucherId = request.getParameter("voucher");
                    Voucher voucher = null;
                    if (!voucherId.trim().equals("")) {

                        VoucherDAO vDAO = new VoucherDAO();
                        voucher = vDAO.getVoucher(voucherId);
                    }
                    System.out.println(voucher);
                    session.setAttribute("voucher", voucher);
                    url = Path.VIEW_CART;
                    flag = 1;
                } else if (action.equals("remove")) {
                    String license = request.getParameter("licensePlate");
                    int index = -1;
                    for (int i = 0; i < cart.size(); i++) {
                        if (cart.get(i).getLicensePlate().equals(license)) {
                            index = i;
                        }
                    }
                    cart.remove(index);
                    session.setAttribute("CART", cart);
                    url = Path.VIEW_CART;
                } else {
                    if (cart.size() != 0) {
                        cart = dao.getInfomationOfCars(cart);
                        Voucher v = new Voucher();
                        v.setAvailable(true);
                        session.setAttribute("voucher", v);
                        session.setAttribute("CART", cart);
                    }
                    url = Path.VIEW_CART;
                }
            }
            session.setAttribute("flag", flag);
        } catch (Exception e) {
            log("ERROR at CartManagementSvl:" + e.getMessage());
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
