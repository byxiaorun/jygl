<%@ page import="jygl.beans.User" %>
<%@ page import="jygl.common.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
</head>
<body>
<%
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    java.util.Date currentTime = new java.util.Date();//得到当前系统时间
    String str_date1 = formatter.format(currentTime); //将日期时间格式化
%>
<%
    User user = null;
    String username = "", sid = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    //获取登录用户名
    username = user.getUsername();
    sid = String.valueOf(user.getId());
%>
<form action="/jygl/messageManage?action=add" method="post">
    <table width="600px" border="1">
        <tr>
            <td colspan="2" align="center">留言管理</td>
        </tr>
        <input type="hidden" name="uid" id="uid" value="<%=sid%>">
        <input type="hidden" name="username" id="username" value="<%=username%>">
        <tr>
            <td>标题</td>
            <td><input type="text" name="title" id="title"></td>
        </tr>
        <input type="hidden" name="msgtime" id="msgtime" value="<%=str_date1%>">
        <tr>
            <td>内容</td>
            <td><input type="text" name="content" id="content"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
