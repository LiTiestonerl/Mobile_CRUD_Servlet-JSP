/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {
    private static final String ERROR_PAGE = "error.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String VIEW = "View";
    private static final String VIEW_PAGE = "viewCart.jsp";
    private static final String BUY = "Buy";
    private static final String BUY_CONTROLLER = "BuyController";
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String INSERT = "Insert";
    private static final String INSERT_CONTROLLER = "InsertController";
    private static final String SAVE= "Save";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String UPDATE= "Update";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String CREATE= "Create";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            String action = request.getParameter("action");
            if(action.equals(LOGIN)){
                url = LOGIN_CONTROLLER;
            }else if(action.equals(LOGOUT)){
                url = LOGOUT_CONTROLLER;
            }else if(action.equals(SEARCH)){
                url = SEARCH_CONTROLLER;
            }else if(action.equals(ADD)){
                url = ADD_CONTROLLER;
            }else if(action.equals(VIEW)){
                url = VIEW_PAGE;
            }else if(action.equals(BUY)){
                url = BUY_CONTROLLER;
            }else if(action.equals(REMOVE)){
                url = REMOVE_CONTROLLER;
            }else if(action.equals(DELETE)){
                url = DELETE_CONTROLLER;
            }else if(action.equals(INSERT)){
                url = SEARCH_CONTROLLER;
            }else if(action.equals(SAVE)){
                url = INSERT_CONTROLLER;
            }else if(action.equals(UPDATE)){
                url = UPDATE_CONTROLLER;
            }else if(action.equals(CREATE)){
                url = CREATE_CONTROLLER;
            }
            else{
                request.setAttribute("ERROR", "Your action is not supported!");
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        }finally{
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
