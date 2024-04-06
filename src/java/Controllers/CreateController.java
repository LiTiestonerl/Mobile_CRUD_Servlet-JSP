/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Users.UserDAO;
import Users.UserDTO;
import Users.UserError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CreateController extends HttpServlet {
    private static final String ERROR = "createAccount.jsp";
    private static final String LOGIN_PAGE = "loginPage.jsp";
    private static final String MANAGE_PAGE = "managerPage.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        UserDAO dao = new UserDAO();
        boolean checkValidation = true;
        HttpSession session = request.getSession();
        UserDTO loginUser =(UserDTO) session.getAttribute("LOGIN_USER");
        int role = (loginUser == null) ? 0 : 2;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            if (userID.length() < 2 || userID.length() > 10) {
                userError.setUserID("User ID must be 2-10 characters long ");
                checkValidation = false;
            }
            boolean checkDuplicate = dao.checkDuplicate(userID);
            if (checkDuplicate) {
                userError.setUserID("Duplicate userID");
                checkValidation = false;
            }
            if (fullName.length() < 5 || fullName.length() > 20) {
                userError.setFullName("Full name must be 5-20 characters long");
                checkValidation = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirm("Passwords do not match");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(userID, password, fullName, role);
                boolean checkInsert = dao.insertV3(user);
                if (checkInsert) {
                    url = (role == 0)? LOGIN_PAGE: MANAGE_PAGE;
                    request.setAttribute("ERROR", "Successful");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception ex) {
            log("Error at CreateController: " + ex.toString());
            if (ex.toString().contains("duplicate")) {
                userError.setUserID("Duplicate userID");
                request.setAttribute("USER_ERROR", userError);
            }
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
