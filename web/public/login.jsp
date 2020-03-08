<%@ page import="jygl.beans.User" %>
<%@ page import="jygl.common.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <%
        //判断是否登录，如果已经登录跳转到对应页面
        String usertype = "";
        User user = (User) session.getAttribute("user");
        if (user != null) {
            usertype = user.getUsertypes();
            if (usertype.equals("admin")) {
                response.sendRedirect("/jygl/admin");
            } else if (usertype.equals("student")) {
                response.sendRedirect("/jygl/student");
            } else if (usertype.equals("company")) {
                response.sendRedirect("/jygl/company");
            }
        }
    %>
</head>
<body>
<%
    Object obj = request.getAttribute("errorMsg");   //获取LoginServlet传来的errorMsg
    String errMsg = "";
    if (obj != null) {
        errMsg = String.valueOf(obj);
    }
%>

<h1 style="text-align: center;margin-top: 100px;">学生就业信息管理系统</h1>
<form action="/jygl/login" method="post" name="form1">
    <table width="600" border="1" cellpadding="0" align="center">
        <tr>
            <td align="right" height="40">用户名：</td>
            <td><input type="text" name="username" size="20" required="required"/><%=errMsg%>
            </td>
        </tr>
        <tr>
            <td align="right" height="40">密码：</td>
            <td><input type="password" name="password" size="20" required="required"/></td>
        </tr>
        <tr>
            <td align="right" height="40">用户类型：</td>
            <td>
                <input type="radio" value="admin" name="usertype"/>管理员
                <input type="radio" value="company" name="usertype"/>企业
                <input type="radio" value="student" name="usertype" checked/>学生
            </td>
        </tr>
        <tr>
            <td height="40">&nbsp;</td>
            <td>
                <input name="submit" type="submit" value="登录"/>
                <input name="reg" type="button" onclick="document.location='/jygl/public/register.jsp'" value="注册"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
