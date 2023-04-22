<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Home</title>
    </head>
    <body>
        <h1>Welcome to SpeedX Courier Services</h1>
        <hr>
        <br>
        <%
            String msg = (String) session.getAttribute("msg");
            String clr = (String) session.getAttribute("color");
            if(msg != null) {
                if(clr.equals("red")) {
        %>
            <p style="color: red; font-weight: bold;"> <%=msg%> </p>
        <%      } else { %>
            <p style="color: green; font-weight: bold;"> <%=msg%> </p>
        <%
                }
            session.setAttribute("msg", null);
            session.setAttribute("clr", null);
            }
        %>
        <br>
        <h2><a href="/login">Login</a></h2>
        <h2><a href="/signup">SignUp</a></h2>
    </body>
</html>