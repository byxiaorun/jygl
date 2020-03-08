<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="jygl.beans.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
    <link rel="stylesheet" href="/jygl/css/pager.css">
    <script type="text/javascript" src="/jygl/admin/js/jquery.min.js"></script>
    <script type="text/javascript" src="/jygl/admin/layer/layer.js"></script>
    <script type="text/javascript">
        function showDetail(sid) {
            layer.open({
                type: 2,
                title: '查看学生信息',
                maxmin: true,
                area: ['400px', '500px'],
                content: '/jygl/studentManage?action=showDetail&sid='+sid,
                btn: ['确定', '关闭']
            });
        }
    </script>
</head>
<body>
<%
    Page p = (Page) request.getAttribute("page");//取出数据
    List list = p.getList();
    Student student = null;
%>
<table width="100%" border="1">
    <tr>
        <th>姓名</th>
        <th>性别</th>
        <th>毕业院校</th>
        <th>专业</th>
        <th>学历</th>
        <th>操作</th>
    </tr>
    <%
        int i = 0;
        while (i < list.size()) {
            student = (Student) list.get(i);
    %>
    <tr class="change">
        <td><%=student.getSname()%>
        </td>
        <td><%=student.getGender()%>
        </td>
        <td><%=student.getSchool()%>
        </td>
        <td><%=student.getMajor()%>
        </td>
        <td><%=student.getEducation()%>
        </td>
        <td>
            <%--            <a href="/jygl/studentManage?action=showDetail&sid=<%=student.getSid()%>&page=<%=p.getCurrentPage()%>" onclick="showDetail()">查看</a>--%>
            <a href="javascript:void (0);" onclick="showDetail(<%=student.getSid()%>)">查看</a>
            <a href="/jygl/studentManage?action=delete&sid=<%=student.getSid()%>&page=<%=p.getCurrentPage()%>"
               onclick="return confirm('确定删除吗？')">删除</a>
        </td>
    </tr>
    <%
            i++;
        }%>
</table>
<div>
    <%@include file="../pager.jsp"%>
<%--    <a href="/jygl/studentManage?action=list&page=1">首页</a>--%>
<%--    <% if(p.getCurrentPage()>1){%>--%>
<%--    <a href="/jygl/studentManage?action=list&page=<%=p.getCurrentPage()+1%>">下一页</a>--%>
<%--    <%}%>--%>
<%--    <a href="/jygl/studentManage?action=list&page=<%=p.getTotalPage()%>">最后一页</a>--%>

<%--    &nbsp;&nbsp;当前第<%=p.getCurrentPage()%>页,共<%=p.getTotalPage()%>页--%>
</div>
</body>
</html>
