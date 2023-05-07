<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Book Package</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/form.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h2>Book Package | <%= session.getAttribute("username") %> (<a href="/logout">logout</a>)</h2>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                
                <form action="/package/book" method="post">
                    <select name="type">
                        <option value="Document" selected>Document</option>
                        <option value="Non Document">Others</option>
                    </select><br><br>
                    <input type="text" name="sourceCity" placeholder="source city"><br><br>
                    <input type="text" name="destinationCity" placeholder="destination city"><br><br>
                    <button type="submit">Book</button><br><br>
                    <a href="/user_home" class="home-button">User-Home</a>
                </form>

            </div>
        </div>
        <script src="../../resources/script.js"></script>
    </body>
</html>