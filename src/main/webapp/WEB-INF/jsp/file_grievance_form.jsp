<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: File Grievance</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/form.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h2>File Grievance | <%= session.getAttribute("username") %> (<a href="/logout">logout</a>)</h2>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                
                <form action="/grievance/file" method="post">
                    <textarea name="content" rows="4" cols="50" placeholder="write content in brief ..."></textarea>
                    <button type="submit">File Grievance</button><br><br>
                    <a href="/user_home" class="home-button">User-Home</a>
                </form>

            </div>
        </div>
        <script src="../../resources/script.js"></script>
    </body>
</html>