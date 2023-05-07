<%@ page import="java.util.List" %>
<%@ page import="com.speedxcourier.SpeedX.domain.Package" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SpeedX Courier: Booked Package</title>
        <link rel="stylesheet" type="text/css" href="../../resources/style.css">
        <link rel="stylesheet" type="text/css" href="../../resources/table.css">
    </head>
    <body>
        <div id="container">
            <div id="content">
                <h2>Booked Package | <%= session.getAttribute("username") %> (<a href="/logout">logout</a>)</h2>
                <hr>
                <jsp:include page="prompt_msg.jsp" />
                
                <%
                    List<Package> packages = (List<Package>) session.getAttribute("userPackages");
                %>

                <table>
                    <tr>
                        <th>Package Type</th>
                        <th>Source City</th>
                        <th>Destination City</th>
                        <th>Booking Date</th>
                        <th>Status</th>
                    </tr>
                    <% for(Package item : packages){ %>
                        <tr>
                            <td><%= item.getPackageType() %></td>
                            <td><%= item.getSourceCity() %></td>
                            <td><%= item.getDestinationCity() %></td>
                            <td><%= item.getCreatedAt() %></td>
                            <td><%= item.getPackageStatus() %></td>
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