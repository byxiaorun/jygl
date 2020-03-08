<%@ page import="jygl.common.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    Utils.gotoPage("/public/login.jsp", request, response);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
