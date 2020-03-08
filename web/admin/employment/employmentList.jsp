<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="jygl.beans.Employment" %>
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
        function edit(eid,page) {
            layer.open({
                type: 2,
                title: '修改就业信息',
                maxmin: true,
                area: ['500px', '400px'],
                content:[ '/jygl/employmentManage?action=edit&eid='+eid+'&page='+page,'no'],
                btn: ['提交', '重置','关闭'],
                yes:function (index,layero) {
                    var iframe=window[layero.find('iframe')[0]['name']];//得到弹窗对象
                    var b=iframe.document.getElementById("submit");
                    b.click();
                },
                btn2:function (index,layero) {
                    var iframe=window[layero.find('iframe')[0]['name']];//得到弹窗对象
                    var b=iframe.document.getElementById("reset");
                    b.click();
                    return false;
                }
            });
        }
    </script>
</head>
<body>
<%
    Page p = (Page) request.getAttribute("page");//取出数据
    List list = p.getList();
    Employment employment = null;
%>
<table width="100%" border="1">
    <tr>
        <th>学生姓名</th>
        <th>学校</th>
        <th>就职企业信息</th>
        <th>就职岗位</th>
        <th>操作</th>
    </tr>
    <%
        int i = 0;
        while (i < list.size()) {
            employment = (Employment) list.get(i);
    %>
    <tr class="change">
        <td><%=employment.getStudentname()%>
        </td>
        <td><%=employment.getSchool()%>
        </td>
        <td><%=employment.getCompanyname()%>
        </td>
        <td><%=employment.getPosition()%>
        </td>
        <td >
            <a href="javascript:void (0);" onclick="edit(<%=employment.getEid()%>)">修改</a>
            <a href="/jygl/employmentManage?action=delete&eid=<%=employment.getEid()%>&page=<%=p.getCurrentPage()%>"
               onclick="return confirm('确定删除吗？')">删除</a>
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
