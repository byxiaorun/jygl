<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="jygl.beans.Message" %>
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
    Message message = null;
%>
<table width="100%" border="1">
    <tr>
        <th>标题</th>
        <th>留言内容</th>
        <th>留言时间</th>
        <th>操作</th>
    </tr>
        <%
        int i=0;
        while (i<list.size()) {
            message= (Message) list.get(i);
    %>
    <tr class="change">
        <td>
            <%=message.getTitle()%>
        </td>
        <td>
            <%=message.getContent()%>
        </td>
        <td>
            <%=message.getMsgtime()%>
        </td>