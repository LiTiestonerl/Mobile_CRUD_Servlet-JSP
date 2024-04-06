<%-- 
    Document   : staffPage
    Created on : Dec 4, 2023, 11:16:24 AM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="Devices.MobileDTO"%>
<%@page import="Users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="staff.css">
        <script src="viewCart.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
    </head>
    <body>
        <%
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || loginUser.getRole() != 2) {
                response.sendRedirect("loginPage.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <header>Staff Manage</header>
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
        <section>
            <% if (!action.equals("Insert")) {%>
            <form action ="MainController">
                <div class ="search-box">
                    <input type = "text" name = "search" value = "<%=search%>"/>
                    <button type="submit" name="action" value="Search">Search</button>
                    <button type="submit" name="action" value="Insert">Insert</button>
                </div>
            </form>
            <%}%>
            <%
                List<MobileDTO> mobileList = (List<MobileDTO>) request.getAttribute("LIST_MOBILE");
                if (mobileList != null && !action.equals("Insert")) {
                    if (mobileList.size() > 0) {
            %>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Image</th>
                            <th>Mobile ID</th>
                            <th>Mobile Name</th>
                            <th>Mobile Description</th>
                            <th>Year Of Production</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Status</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for (MobileDTO mobile : mobileList) {
                        %>
                    <form action="MainController" method="POST">
                        <tr>
                            <td><%= ++count%></td>
                            <td>
                                <img src="img/<%=mobile.getMobileID()%>.jpg" alt="<%= mobile.getMobileName()%> Image" onclick="openModal('img/<%=mobile.getMobileID()%>.jpg')"/>
                            </td>
                            <td>
                                <input type = "text" name = "mobileID" value ="<%= mobile.getMobileID()%>" readonly=""/>
                            </td>
                            <td>
                                <input type = "text" name = "mobileName" value ="<%= mobile.getMobileName()%>" readonly=""/>
                            </td>
                            <td>
                                <input type = "text" name = "description" value ="<%= mobile.getDescription()%>" required=""/>
                            </td>
                            <td>
                                <input type = "number" name = "yearOfProduction" value ="<%= mobile.getYearOfProduction()%>" readonly=""/>
                            </td>
                            <td>
                                <input type = "number" name = "price" value ="<%= mobile.getPrice()%>" step="any"  required=""/>
                            </td>
                            <td>
                                <input type = "number" name = "quantity" value ="<%= mobile.getQuantity()%>" required=""/>
                            </td>
                            <td>
                                <%int status = mobile.getStatus();%>
                                <select name="notSale">
                                    <option value="0" <%= (status == 0) ? "selected" : ""%>>Sale</option>
                                    <option value="1" <%= (status == 1) ? "selected" : ""%>>Not Sale</option>
                                </select>
                            </td>
                            <td>
                                <button type="submit" name="action" value="Delete">Delete</button>
                            </td>
                            <td>
                                <button type="submit" name="action" value="Update">Update</button>
                            </td>
                        <input type ="hidden" name ="search" value ="<%=search%>">
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <%
                    }
                }
            %>
            <%
                String error = (String) request.getAttribute("ERROR");
                if (error == null) {
                    error = "";
                }
            %>
            <h2><%= error%></h2>

            <%
                String message = (String) request.getAttribute("MESSAGE");
                if (message == null) {
                    message = "";
                }
            %>
            <h2><%= message%></h2>
            <% if (action.equals("Insert")) { %>
            <div class="form-value">
                <h1>Add new mobile</h1>
                <form action="MainController" method="POST">
                    <div class="inputbox">
                        <input type="text" id="ID" name="mobileID" required=""/>
                        <label for="ID">Mobile ID</label>
                    </div>
                    <div class="inputbox">
                        <input type="text" id="name" name="mobileName" required=""/>
                        <label for="name">Mobile Name</label>
                    </div>
                    <div class="inputbox">
                        <input type="text" id="des" name="description" required=""/>
                        <label for="des">Description </label>
                    </div>
                    <div class="inputbox">
                        <input type="number" id="price" name="price" step="any" required=""/>
                        <label for="price">Price </label>
                    </div>
                    <div class="inputbox">
                        <input type="number" id="year" name="yearOfProduct"required=""/>
                        <label for="year">Year OF Production </label>
                    </div>
                    <div class="inputbox">
                        <input type="number" id="quantity" name="quantity"  required=""/>
                        <label for="quantity">Quantity</label>
                    </div>
                    Status <select name="option">
                        <option value="0">Sale</option>
                        <option value="1">Not Sale</option>
                    </select>
                    <div class="buttons">
                        <button type="submit" name="action" value="Save">Save</button>
                        <a href="staffPage.jsp">
                            <button type="button">Back</button>
                        </a>
                    </div>
                </form>
            </div>
            <%
                }
            %> 
        </section>
        <div id="myModal" class="modal">
            <span class="close" onclick="document.getElementById('myModal').style.display = 'none'">&times;</span>
            <img src="" id="modalImg" class="modal-content">
        </div>      
    </body>
</html>
