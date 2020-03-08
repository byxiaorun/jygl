<%@ page import="jygl.beans.User" %>
<%@ page import="jygl.beans.Resume" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
</head>
<body>
<%
    User user = null;
    String sid = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    sid = String.valueOf(user.getId());
    Resume resume= (Resume) request.getAttribute("resume");
%>
    <table width="100%" border="1">
        <tr>
            <td colspan="2" align="center"><b>查看简历</b></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><%=resume.getSname()%></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><%=resume.getGender()%></td>
        </tr>
        <tr>
            <td>出生年月</td>
            <td><%=resume.getBirthdate()%></td>
        </tr>
        <tr>
            <td>民族</td>
            <td><%=resume.getNation()%></td>
        </tr>
        <tr>
            <td>政治面貌</td>
            <td><%=resume.getPolitics()%></td>
        </tr>
        <tr>
            <td>毕业时间</td>
            <td><%=resume.getGraduation()%></td>
        </tr>
        <tr>
            <td>毕业院校</td>
            <td><%=resume.getSchool()%></td>
        </tr>
        <tr>
            <td>所学专业</td>
            <td><%=resume.getMajor()%></td>
        </tr>
        <tr>
            <td>学历</td>
            <td><%=resume.getEducation()%></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><%=resume.getEmail()%></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><%=resume.getPhone()%></td>
        </tr>
        <tr>
            <td>外语水平</td>
            <td><%=resume.getForeignlanguage()%></td>
        </tr>
        <tr>
            <td>特长爱好</td>
            <td><%=resume.getHobby()%></td>
        </tr>
        <tr>
            <td>社会实践经历</td>
            <td><%=resume.getPractice()%></td>
        </tr>
        <tr>
            <td>在校担任职务</td>
            <td><%=resume.getPosition()%></td>
        </tr>
        <tr>
            <td>在校获奖</td>
            <td><%=resume.getHonor()%></td>
        </tr>
        <tr>
            <td>科研成果</td>
            <td><%=resume.getResearch()%></td>
        </tr>
        <tr>
            <td>自我评价</td>
            <td><%=resume.getSelfevaluation()%></td>
        </tr>
    </table>
</body>
</html>
