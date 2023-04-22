<div>    
    <br>
        <%
            String msg = (String) session.getAttribute("msg");
            String clr = (String) session.getAttribute("color");
            if(msg != null) {
                if(clr.equals("red")) {
        %>
            <p style="color: red; font-weight: bold;"> <%=msg%> </p>
        <%      } else { %>
            <p style="color: green; font-weight: bold;"> <%=msg%> </p>
        <%
                }
            session.setAttribute("msg", null);
            session.setAttribute("clr", null);
            }
        %>
    <br>
</div>