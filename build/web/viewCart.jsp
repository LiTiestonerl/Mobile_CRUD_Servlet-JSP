<%-- 
    Document   : viewCart
    Created on : Nov 29, 2023, 8:23:28 PM
    Author     : Admin
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="Devices.MobileDTO"%>
<%@page import="Devices.CartDTO"%>
<%@page import="Users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="viewCart.css">
        <script src="viewCart.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String minValue = request.getParameter("minValue");
            if (minValue == null) {
                minValue = "";
            }

            String maxValue = request.getParameter("maxValue");
            if (maxValue == null) {
                maxValue = "";
            }
            String viewMessage = (String) request.getAttribute("VIEW_MESSAGE");
            if (viewMessage == null) {
                viewMessage = "";
            }
        %>
        <header style="color: black">Your Cart</header>
        <div class="hello">
            <div class="avatar-container" id="avatarContainer">
                <img src="img/doIu.jpg" alt="Avatar" class="avatar" id="avatar">
                <div id="userInfo" class="hidden">
                    <p><%= "userID: " + loginUser.getUserID()%></p>
                    <a href="MainController?action=Logout" id="logoutLink">
                        <i class="fas fa-sign-out-alt"></i> Logout
                    </a>
                </div>
            </div>
            <p><%= loginUser.getFullName()%></p>
        </div>
        <%

            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (viewMessage.equals("Buy Successful")) {
                session.setAttribute("CART", null);
            }
            if (cart != null) {
        %>
        <section>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Image</th>
                            <th>Mobile Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Has Pay</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 1;
                            double total = 0;
                            String formattedTotal = null;
                            for (MobileDTO mobile : cart.getCart().values()) {
                                total += mobile.getQuantity() * mobile.getPrice();
                                DecimalFormat df = new DecimalFormat("#.##");
                                formattedTotal = df.format(total);
                        %>
                    <form action="MainController" method="POST">
                        <tr>
                            <td>
                                <%=count++%>
                            </td>
                            <td>
                                <img src="img/<%=mobile.getMobileID()%>.jpg" alt="<%= mobile.getMobileName()%> Image" onclick="openModal('img/<%=mobile.getMobileID()%>.jpg')"/>
                            </td>
                        <input type="hidden" name = "mobileID" value="<%=mobile.getMobileID()%>" readonly=""/>
                        <td><%=mobile.getDescription()%></td>
                        <td><%=mobile.getPrice()%></td>
                        <td><input type="number" name="quantity" value="<%=mobile.getQuantity()%>"/></td>
                        <td><%=mobile.getPrice() * mobile.getQuantity()%></td>
                        <td><button type="submit" name ="action" value="Remove">Remove</button></td>
                        <input type ="hidden" name ="minValue" value ="<%=minValue%>">
                        <input type ="hidden" name ="maxValue" value ="<%=maxValue%>">
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <h1>Total: <%=formattedTotal%>$</h1>
            <form action="MainController" class="Buy-button">
                <button type="button" onclick="showConfirmation()">Buy</button>
                <button type="button" onclick="window.location.href = 'MainController?minValue=<%=minValue%>&action=Search&maxValue=<%=maxValue%>'">Shopping more</button>
            </form>
            <%
            } else {
            %>
            <p>You haven't placed any orders in cart <a href="userPage.jsp">Go Back To Shopping Now</a></p> 
            <%}%>
        </section>
        <div id="viewMessageDialog" class="confirm_box" style="display: none;">
            <div class="confirm_dialog">
                <p class="noti" id="viewMessageText"></p>
                <div class="modal-buttons">
                    <button onclick="closeViewMessageDialog()">OK</button>
                </div>
            </div>
        </div>
        <script>
            var viewMessage = '<%=viewMessage%>';
        </script>
        <div id="myModal" class="modal">
            <span class="close" onclick="document.getElementById('myModal').style.display = 'none'">&times;</span>
            <img src="" id="modalImg" class="modal-content">
        </div>
        <div class ="confirm_box" id="confirm">
            <div class="confirm_dialog">
                <p>Are you sure to make the purchase?</p>
                <div class="modal-buttons">
                    <button onclick="confirmPurchase()">Confirm</button>
                    <button onclick="cancelPurchase()">Cancel</button>
                </div>
            </div>
        </div>
    </body>
</html>
