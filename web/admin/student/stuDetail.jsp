<%@ page import="jygl.beans.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
</head>
<body>
<%
    Student stu= (Student) request.getAttribute("student");
%>
<table width="100%" border="1">
    <tr>
        <td>编号</td>
        <td><%=stu.getSid()%></td>
    </tr>
    <tr>
        <td>姓名</td>
        <td><%=stu.getSname()%></td>
    </tr>
    <tr>
        <td>性别</td>
        <td><%=stu.getGender()%></td>
    </tr>
    <tr>
        <td>身份证号码</td>
        <td><%=stu.getIdnumber()%></td>
    </tr>
    <tr>
        <td>毕业院校</td>
        <td><%=stu.getSchool()%></td>
    </tr>
    <tr>
        <td>系别</td>
        <td><%=stu.getDepartment()%></td>
    </tr>
    <tr>
        <td>专业</td>
        <td><%=stu.getMajor()%></td>
    </tr>
    <tr>
        <td>学历</td>
        <td><%=stu.getEducation()%></td>
    </tr>
    <tr>
        <td>入学时间</td>
        <td><%=stu.getEntrancedate()%></td>
    </tr>
    <tr>
        <td>籍贯</td>
        <td><%=stu.getNativeplace()%></td>
    </tr>
</table>
</body>
</html>
