<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="jygl.beans.Recruit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Page p = (Page) request.getAttribute("page");//取出数据
    List list = p.getList();
    Recruit recruit = null;
%>
<table width="100%" border="1">
    <tr>
        <th>招聘编号</th>
        <th>招聘企业信息</th>
        <th>企业地址</th>
        <th>工作地点</th>
        <th>操作</th>
    </tr>
    <%
        int i = 0;
        while (i < list.size()) {
            recruit = (Recruit) list.get(i);
    %>
    <tr class="change">
        <td><%=recruit.getRid()%>
        </td>
        <td><%=recruit.getCompanyname()%>
        </td>
        <td><%=recruit.getAddress()%>
        </td>
        <td><%=recruit.getWorkingplace()%>
        </td>
