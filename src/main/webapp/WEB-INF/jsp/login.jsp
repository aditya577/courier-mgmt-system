<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <hr>
        <jsp:include page="prompt_msg.jsp" />
        <br>
        <form action="/login" method="post">
            <input type="text" name="username" placeholder="username"><br><br>
            <input type="password" name="password" placeholder="password"><br><br>
            <button type="submit">Login</button>
        </form>
    </body>
</html>