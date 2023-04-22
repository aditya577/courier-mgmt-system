<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: SignUp</title>
    </head>
    <body>
        <h1>SignUp</h1>
        <hr>
        <jsp:include page="prompt_msg.jsp" />
        <br>
        <form action="/signup" method="post">
            <input type="text" name="username" placeholder="username"><br><br>
            <select name="role">
                <option value="ADMIN">Admin</option>
                <option value="EXECUTIVE">Executive</option>
                <option value="COURIER">Courier</option>
                <option value="CUSTOMER" selected>Customer</option>
            </select><br><br>
            <input type="text" name="name" placeholder="name"><br><br>
            <input type="tel" name="phone" placeholder="phone no."><br><br>
            <input type="email" name="email" placeholder="email"><br><br>
            <input type="password" name="password" placeholder="password"><br><br>
            <button type="submit">Register</button>
        </form>
    </body>
</html>