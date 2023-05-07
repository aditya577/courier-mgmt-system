<%@ page import="java.util.List" %>
<%@ page import="com.speedxcourier.SpeedX.domain.Grievance" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Filed Grievance</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/table.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h2>Filed Grievance | <%= session.getAttribute("username") %> (<a href="/logout">logout</a>)</h2>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                
                <%
                    List<Grievance> list = (List<Grievance>) session.getAttribute("userGrievances");
                %>

                <table>
                    <tr>
                        <th>Content</th>
                        <th>Booking Date</th>
                        <th>Status</th>
                    </tr>
                    <% for(Grievance item : list){ %>
                        <tr>
                            <td><%= item.getContent() %></td>
                            <td><%= item.getCreatedAt() %></td>
                            <td><%= item.getGrievanceStatus() %></td>
                        </tr>
                    <% } %>
                </table>
                <br>
                <a href="/user_home" class="home-button">User-Home</a>

            </div>
        </div>
        <script src="../../resources/script.js"></script>
    </body>
</html>