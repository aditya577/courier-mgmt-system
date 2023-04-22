<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Login</title>
    </head>
    <body>
        <h1>Login</h1>
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
        <form action="/login" method="post">
            <input type="text" name="username" placeholder="username"><br><br>
            <input type="password" name="password" placeholder="password"><br><br>
            <button type="submit">Login</button>
        </form>
    </body>
</html>