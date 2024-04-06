<%-- 
    Document   : createAccount
    Created on : Nov 30, 2023, 3:18:10 PM
    Author     : Admin
--%>

<%@page import="Users.UserDTO"%>
<%@page import="Users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="create.css">
    <meta charset="UTF-8">
    <title>Register</title>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
            UserDTO loginUser =(UserDTO) session.getAttribute("LOGIN_USER");
            int roleID = 1;
            if(loginUser == null){
                 roleID = -1;
            }else{
                roleID = loginUser.getRole();
            }
            String heading = (roleID!=1)? "Register": "Add new staff";
        %>
</head>
<body>
    <% if(roleID!=-1){%>
        <div class="hello">
            <div class="avatar-container" id="avatarContainer">
                <img src="img/doIu.jpg" alt="Avatar" class="avatar" id="avatar">
                <div id="userInfo" class="hidden">
                    <p><%= "userID: " + loginUser.getUserID() %></p>
                        <a href="MainController?action=Logout" id="logoutLink">
                            <i class="fas fa-sign-out-alt"></i> Logout
                        </a>
                </div>
            </div>
            <p><%= loginUser.getFullName() %></p>
        </div>
     <%}%>
    <section>
        <div class="form-box">
            <div class="form-value">
                <h1><%= heading%></h1>
                <form action="MainController" method="POST">
                    <div class="inputbox">
                        <input type="text" name="userID" required="">
                        <label for="">User ID</label>
                        <h2><%= userError.getUserID()%></h2>
                    </div>
                    <div class="inputbox">
                        <input type="text" name="fullName" required="">
                        <label for="">Full Name</label>
                        <h2><%= userError.getFullName()%></h2>
                    </div>
                    <div class="inputbox">
                        <input type="password" name="password" required="">
                        <label for="">Password</label>
                    </div>
                    <div class="inputbox">
                        <input type="password" name="confirm" required="">
                        <h2><%= userError.getConfirm()%></h2>
                        <label for="">Confirm Password</label>
                    </div>
                        <button type="submit" name="action" value="Create">Create</button>
                    <% if(roleID !=1){%>
                        <div class="login">
                            <p style="font-family: sans-serif">Already have an account? <a href="loginPage.jsp">Back to Login</a></p>
                        </div>
                    <%}else{%>
                        <a href="managerPage.jsp">
                          <button type="button">Back</button>
                        </a>
                    <%}%>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
