<%-- 
    Document   : login
    Created on : Nov 29, 2023, 6:43:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="login.css">
  <title>Login Page</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <section>
        <div class="form-box">
            <div class="form-value">
                <form action="MainController" method="POST">
                    <h2>Login</h2>
                    <div class="inputbox">
                        <input type="text" name = "userID" required>
                        <label for="a">User ID</label>
                    </div>
                    <div class="inputbox">
                        <input type="password" name = "password" required="">
                        <label for="a">Password</label>
                    </div>
                        <input type="submit" name="action" value="Login"/>
                    <div class="register">
                        <p>Don't have an account ? <a href="createAccount.jsp">Register</a></p>
                    </div>
                    <% 
                        String error = (String) request.getAttribute("ERROR");
                        String status = null;
                        if (error == null) {
                            error = "";
                         }else if(error.equals("Incorrect userID or password")){
                             status = "error";
                         } else if (error.equals("Logout Successfull")||error.equals("Successful")){
                             status = "success";
                         }  
                    %>          
                         <h1 class="<%=status%>"> <%=error%> </h1>
                </form>
            </div>
        </div>
    </section>
</body>
</html>


