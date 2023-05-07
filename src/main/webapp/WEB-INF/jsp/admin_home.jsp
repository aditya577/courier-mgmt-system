<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: User Home</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h1>User Home | <%= session.getAttribute("username") %> (<a href="/logout">logout</a>)</h1>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                <h2><a href="/admin/get/users">View All Users</a></h2>
                <h2><a href="/admin/get/packages">View All Packages</a></h2>
                <h2><a href="/admin/get/grievances">View All Complaint/Grievance</a></h2>
            </div>
        </div>
        <script src="../../resources/script.js"></script>
    </body>
</html>