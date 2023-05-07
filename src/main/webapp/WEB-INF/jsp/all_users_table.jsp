<%@ page import="java.util.List" %>
<%@ page import="com.speedxcourier.SpeedX.domain.User" %>

<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: All Users</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/table.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h2>All Users | <%= session.getAttribute("username") %> (<a href="/logout">logout</a>)</h2>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                
                <%
                    List<User> users = (List<User>) session.getAttribute("customersList");
                %>

                <table>
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Created At</th>
                    </tr>
                    <% for(User item : users){ %>
                        <tr>
                            <td><%= item.getId() %></td>
                            <td><%= item.getUsername() %></td>
                            <td><%= item.getCreatedAt() %></td>
                        </tr>
                    <% } %>
                </table>
                <br>
                <a href="/admin/home" class="home-button">User-Home</a>

            </div>
        </div>
        <script src="../../resources/script.js"></script>
    </body>
</html>