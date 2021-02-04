/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtnl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Session;
import linhtnl.DTOs.Account;
import linhtnl.daos.AccountDAO;
import linhtnl.util.Path;

/**
 *
 * @author ASUS
 */
public class RegisterSvl extends HttpServlet {

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
            out.println("<title>Servlet RegisterSvl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterSvl at " + request.getContextPath() + "</h1>");
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
        String url = Path.REGISTER;
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String confirm = request.getParameter("confirmPassword");
            String addr = request.getParameter("address");
            AccountDAO dao = new AccountDAO();
            Account dto = new Account(email, pass, name, phone, addr, confirm);
            if (dao.emailExist(email)) {
                dto.setAccountError("This email is registered!");
                session.setAttribute("ACC", dto);
            } else {
                if (dao.createNew(dto)) {
                    //Send avtive code to mail
                    if (send(email)) {
                        url = Path.WATING;
                    }
                }
                session.setAttribute("ACC", null);
            }
        } catch (Exception e) {
            log("ERROR at RegisterSvl: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    private static boolean send(String to) {
        boolean check = true;
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication("linhtnl2000@gmail.com", "Cuxin123");
                    }
                });
        //compose message    
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("ACTIVE EMAIL");
            message.setContent(" <h3>Click <a href=\"http://localhost:8084/CarRent/ActiveSvl?email=" + to + "\">here</a> to active</h3>", "text/html");
            //send message  
            Transport.send(message);
            check = true;
        } catch (MessagingException e) {
            check = false;
            throw new RuntimeException(e);

        }
        return check;
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
