/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Devices.CartDTO;
import Devices.MobileDTO;
import Users.UserDAO;
import Users.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin    
 */
public class BuyController extends HttpServlet {

    public static final String ERROR = "viewCart.jsp";
    public static final String SUCCESS = "viewCart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO)session.getAttribute("CART");
            UserDTO user = (UserDTO)session.getAttribute("LOGIN_USER");
            if(cart != null){
                UserDAO dao = new UserDAO();
                boolean check = true;
                for(MobileDTO mobile : cart.getCart().values()){
                    if(mobile.getQuantity() > dao.getQuantity(mobile)){
                        request.setAttribute("VIEW_MESSAGE", mobile.getDescription() + " is not available in sufficient quantity, please make another selection!!!");
                        check = false;
                        break;
                    }
                }
                
                if(check){
                    boolean checkDuplicate = true;
                    List<MobileDTO> mobileList = dao.getHistoryList(user.getUserID());
                    for(MobileDTO mobile : cart.getCart().values()){
                        dao.updateQuantity(mobile);
                        checkDuplicate = true;
                        for(MobileDTO mobileHistory : mobileList){
                            if(mobile.getMobileID().equals(mobileHistory.getMobileID())){
                                checkDuplicate = false;
                            }
                        }
                        if(checkDuplicate){
                            dao.insert(user, mobile);
                        }else{
                            dao.updateQuantityV2(mobile);
                        }
                    }
                    request.setAttribute("VIEW_MESSAGE", "Buy Successful");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
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
