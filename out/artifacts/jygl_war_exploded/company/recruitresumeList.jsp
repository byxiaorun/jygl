<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="jygl.beans.Recruitresume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
    <link rel="stylesheet" href="/jygl/css/pager.css">
</head>
<body>
<%
    Page p = (Page) request.getAttribute("page");//取出数据
    List list = p.getList();
    Recruitresume recruitresume = null;
%>
<table width="100%" border="1">
    <tr>
        <th>简历id</th>
        <th>企业id</th>
        <th>学生id</th>
        <th>操作</th>
    </tr>
    <%
        int i = 0;
        while (i < list.size()) {
            recruitresume = (Recruitresume) list.get(i);
    %>
    <tr class="change">
        <td><%=recruitresume.getRid()%>
        </td>
        <td><%=recruitresume.getCid()%>
        </td>
        <td><%=recruitresume.getSid()%>
        </td>
        <td>
            <a href="/jygl/resumeManage?action=edit&sid=<%=recruitresume.getSid()%>&page=<%=p.getCurrentPage()%>">查看学生简历</a>
        </td>
    </tr>
    <%
            i++;
        }%>
</table>
<div>
    <%@include file="../admin/pager.jsp"%>
</div>
</body>
</html>
