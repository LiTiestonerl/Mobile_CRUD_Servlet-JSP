/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Devices.MobileDTO;
import Users.UserDAO;
import Users.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tuấn Anh
 */
public class SearchController extends HttpServlet {

    private static final String ERROR="error.jsp";
    private static final String USER="userPage.jsp";
    private static final String STAFF="staffPage.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
        int roleID = loginUser.getRole();
        if(roleID == 0){
            try{
                url = USER;
                String minValue = request.getParameter("minValue");
                String maxValue = request.getParameter("maxValue");
                UserDAO dao = new UserDAO();
                List<MobileDTO> mobileList = new ArrayList<>();
                if (minValue.isEmpty() && maxValue.isEmpty()) {
                    mobileList = dao.getAllMobiles();
                } else if(minValue.isEmpty() && !maxValue.isEmpty()){
                    mobileList = dao.getMobileList(0, Float.parseFloat(maxValue));
                }else if (!minValue.isEmpty()&& maxValue.isEmpty()){
                    mobileList = dao.getMobileList(Float.parseFloat(minValue), dao.getMaxPrice());
                }else{
                    mobileList = dao.getMobileList(Float.parseFloat(minValue), Float.parseFloat(maxValue));
                }
                if(mobileList.size()>0){
                    request.setAttribute("LIST_MOBILE", mobileList);
                }
            }catch(Exception e){
                log("Error at SearchController: " + e.toString());
            }finally{
                request.getRequestDispatcher(url).forward(request, response);
            }
        }else if (roleID == 2) {
            try {
                url = STAFF;
                String search = request.getParameter("search");
                UserDAO dao = new UserDAO();
                List<MobileDTO> mobileList = dao.getMobileListV2(search);
                if(mobileList.size()>0){
                    request.setAttribute("LIST_MOBILE", mobileList);
                }
            } catch (Exception e) {
                log("Error at SearchController: " + e.toString());
            }finally{
                request.getRequestDispatcher(url).forward(request, response);
            }
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
