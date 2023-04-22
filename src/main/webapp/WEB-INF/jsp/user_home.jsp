<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: User Home</title>
    </head>
    <body>
        <h1>User Home</h1>
        <hr>
        <jsp:include page="prompt_msg.jsp" />
        <br>

        <h1>Welcome <%= session.getAttribute("username") %></h1>
        <p><a href="/logout">logout</a></p>
    </body>
</html>