<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: User Home</title>
    </head>
    <body>
        <h1>User Home</h1>
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

        <h1>Welcome <%= session.getAttribute("username") %></h1>
        <p><a href="/logout">logout</a></p>
    </body>
</html>