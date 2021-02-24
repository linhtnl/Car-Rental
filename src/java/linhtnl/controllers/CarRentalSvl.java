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
import linhtnl.DTOs.CarByName;
import linhtnl.DTOs.CarDTO;
import linhtnl.util.Path;

/**
 *
 * @author ASUS
 */
public class CarRentalSvl extends HttpServlet {

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
            out.println("<title>Servlet CarRentalSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarRentalSvl at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);
        String url = Path.ERROR;
        try {
            HttpSession session = request.getSession();
            String carID = request.getParameter("carID");
            Vector<CarByName> carList = (Vector<CarByName>) session.getAttribute("listCar");
            int index = 0;
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getCarID().equals(carID)) {
                    index = i;
                    break;
                }
            }
            CarByName carDetail = carList.get(index);
            url = Path.VIEW_CAR_RENTAL;
            session.setAttribute("carDetail", carDetail);
        } catch (Exception e) {
            log("ERROR at CarRentalSvl: " + e.getMessage());
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
        String url = "error.jsp";
        try {
            HttpSession session = request.getSession();
            String license = request.getParameter("licensePlate");
            String dateRent = request.getParameter("dateRent" + license);
            String dateReturn = request.getParameter("dateReturn" + license);
            Vector<CarDTO> cart = (Vector<CarDTO>) session.getAttribute("CART");
            CarByName car = (CarByName) session.getAttribute("carDetail");
            CarDTO dto = new CarDTO();
            dto.setName(car.getCarName());
            dto.setLicensePlate(license);
            dto.setDateRent(dateRent);
            dto.setDateReturn(dateReturn);
            if (cart == null) {
                cart = new Vector();
            } else {
                int flag = -1;
                for (int i = 0; i < cart.size(); i++) {
                    if (cart.get(i).getLicensePlate().equals(license)) {
                        flag = i;
                    }
                }
                if (flag != -1) {
                    cart.removeElementAt(flag);
                }
            }

            cart.add(dto);
            System.out.println("-----Cart--------");
            for (CarDTO dt : cart) {
                System.out.println(dt.getLicensePlate());
            }
            System.out.println("--------------");
            session.setAttribute("CART", cart);
            url = Path.VIEW_CAR_RENTAL;
        } catch (Exception r) {
            r.printStackTrace();
            log("ERROR at CarRentalSvl: " + r.getMessage());
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
