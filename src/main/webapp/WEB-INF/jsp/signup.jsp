<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: SignUp</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/form.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h1>SignUp</h1>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                <form action="/signup" method="post">
                    <select name="role">
                        <option value="ADMIN">Admin</option>
                        <option value="EXECUTIVE">Executive</option>
                        <option value="COURIER">Courier</option>
                        <option value="CUSTOMER" selected>Customer</option>
                    </select><br><br>
                    <input type="text" name="name" placeholder="name"><br><br>
                    <input type="tel" name="phone" placeholder="phone no."><br><br>
                    <input type="email" name="email" placeholder="email"><br><br>
                    <input type="text" name="username" placeholder="username"><br><br>
                    <input type="password" name="password" placeholder="password"><br><br>
                    <button type="submit">Register</button><br><br>
                    <a href="/" class="home-button">Home</a>
                </form>
            </div>
        </div>

        <script src="../../resources/script.js"></script>
    </body>
</html>