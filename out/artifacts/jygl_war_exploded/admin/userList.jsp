<%@ page import="jygl.beans.User" %>
<%@ page import="jygl.common.Utils" %>
<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
    <link rel="stylesheet" href="/jygl/css/pager.css">
</head>
<body>
<%
    User u = (User) session.getAttribute("user");
    if (u == null || !u.getUsertypes().equals("admin")) {
        Utils.gotoPage("/public/login.jsp", request, response);
    }
    Page p = (Page) request.getAttribute("page");//取出
    List list = p.getList();//取出用户列表
    User user = null;
    String status = "", verify = "", link = "";
%>
<form id="form1" name="form1" method="post" action="">
    <select name="selVerify" id="selVerify">
        <option value="0">审核状态</option>
        <option value="1">未审核</option>
        <option value="2">已审核</option>
        <option value="3">审核未通过</option>
    </select>
</form>
<table width="100%" border="1">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>用户类型</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <%
        int i = 0;
        while (i < list.size()) {
            user = (User) list.get(i);
            verify = user.getVerify();
            if (verify.equals("1")) {
                status = "未审核";
                link = "<a href='/jygl/userManage?action=active&id=" + user.getId() +"&page="+p.getCurrentPage()+"'>通过</a> |";
                link += "<a href='/jygl/userManage?action=invalid&id=" + user.getId() +"&page="+p.getCurrentPage()+"'>不通过</a>";
            } else if (verify.equals("2")) {
                status = "已通过";
                link = "<a href='/jygl/userManage?action=disable&id=" + user.getId() + "&page="+p.getCurrentPage()+"'>禁用</a>";
            } else if (verify.equals("3")) {
                status = "未通过";
                link = "<a href='/jygl/userManage?action=active&id=" + user.getId() +"&page="+p.getCurrentPage()+"'>通过</a>";
            }
    %>
    <tr class="change">
        <td><%=user.getId()%>
        </td>
        <td><%=user.getUsername()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td><%=user.getUsertypes()%>
        </td>
        <td><%=status%>
        </td>
        <td><%=link%>
        </td>
    </tr>
    <%
            i++;}
    %>
</table>
<div style="text-align: center">
    <%@include file="pager.jsp"%>
<%--    <div>--%>
<%--        <a href="/jygl/userManage?action=list&page=1">首页</a>--%>
<%--        <%--%>
<%--            if (p.getCurrentPage() > 1) {--%>
<%--        %> <a href="/jygl/userManage?action=list&page=<%=p.getCurrentPage()-1%>">上一页</a>--%>
<%--        <%}%>--%>
<%--        <%--%>
<%--            if (p.getCurrentPage() < p.getTotalPage()) {--%>
<%--        %> <a href="/jygl/userManage?action=list&page=<%=p.getCurrentPage()+1%>">下一页</a>--%>
<%--        <%}%>--%>
<%--        <a href="/jygl/userManage?action=list&page=<%=p.getTotalPage()%>">尾页</a>--%>
<%--    </div>--%>
<%--    <span>--%>
<%--        当前第<%=p.getCurrentPage()%>页--%>
<%--        共<%=p.getTotalPage()%>页<%=p.getRecordCount()%>条记录--%>
<%--    </span>--%>
<%--</div>--%>
</body>
</html>
