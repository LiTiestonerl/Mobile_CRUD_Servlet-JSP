/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Devices.MobileDTO;
import Users.UserDAO;
import Users.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tuấn Anh
 */
public class UpdateController extends HttpServlet {
    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
        int roleID = loginUser.getRole();  
            try {
                if (roleID == 2) {
                    String mobileID = request.getParameter("mobileID");
                    String description = request.getParameter("description");
                    float price = Float.parseFloat(request.getParameter("price"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int status = Integer.parseInt(request.getParameter("notSale"));
                    MobileDTO mobile = new MobileDTO(mobileID, description, price, "", 0, quantity, status);
                    UserDAO dao = new UserDAO();
                    boolean checkUpdate = dao.update(mobile);
                    if (checkUpdate){
                    request.setAttribute("ERROR", "Update Successful ");    
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Update fail ");
                    }
                } else {
                    String userID = request.getParameter("userID");
                    String fullName = request.getParameter("fullName");
                    UserDTO user = new UserDTO(userID, "*****", fullName, 2);
                    UserDAO dao = new UserDAO();
                    boolean checkUpdate = dao.updateV2(user);
                    if (checkUpdate) {
                        url = SUCCESS;
                        request.setAttribute("ERROR", "Update successful");
                    } else {
                        request.setAttribute("ERROR", "Update fail");
                    }                    
                }
        } catch (Exception ex) {
            log("Error at UpdateController: " + ex.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
