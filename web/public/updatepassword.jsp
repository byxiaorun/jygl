<%@ page import="jygl.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
</head>
<body>
<%
    User user = null;
    String id = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    id = String.valueOf(user.getId());
%>
<form action="/jygl/userManage?action=updatepassword" method="post">
    <table width="600px" border="1" >
        <tr>
            <td colspan="2" align="center">修改密码</td>
            <input type="hidden" name="id" id="id" value="<%=id%>">
        </tr>
        <tr>
            <td>原密码</td>
            <td><input type="text" name="oldpassword" id="oldpassword"></td>
        </tr>
        <tr>
            <td>请输入新密码</td>
            <td><input type="text" name="newpassword" id="newpassword"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
