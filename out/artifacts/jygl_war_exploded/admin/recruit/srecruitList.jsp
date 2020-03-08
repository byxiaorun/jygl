<%@ page import="jygl.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
    <link rel="stylesheet" href="/jygl/css/pager.css">
</head>
<body>
<%@include file="recruit.jsp"%>
<%
    User user = null;
    String sid = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    sid = String.valueOf(user.getId());
%>
<td>
    <a href="/jygl/resumeManage?action=send&cid=<%=recruit.getCid()%>&rid=<%=recruit.getRid()%>&sid=<%=sid%>" onclick="">投简历</a>
</td>
</tr>
<%
    i++;
    }%>
</table>
<div>
    <%@include file="../pager.jsp"%>
</div>
</body>
</html>
