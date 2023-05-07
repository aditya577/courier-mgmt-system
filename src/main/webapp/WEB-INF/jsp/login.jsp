<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Login</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/form.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h1>Login</h1>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                <form action="/login" method="post">
                    <input type="text" name="username" placeholder="username"><br><br>
                    <input type="password" name="password" placeholder="password"><br><br>
                    <button type="submit">Login</button><br><br>
                    <a href="/" class="home-button">Home</a>
                </form>
            </div>
        </div>
        <script src="../../resources/script.js"></script>
    </body>
</html>