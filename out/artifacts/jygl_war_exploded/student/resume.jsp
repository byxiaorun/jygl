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
    String sid = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    sid = String.valueOf(user.getId());
%>
<form action="/jygl/resumeManage?action=add" method="post">
    <table width="100%" border="1">
        <tr>
            <td colspan="2" align="center"><b>制作简历</b></td>
        </tr>
        <input type="hidden" name="sid" id="sid" value="<%=sid%>">
        <tr>
            <td>姓名</td>
            <td><input name="sname" id="sname" value=""></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input name="gender" id="gender" value=""></td>
        </tr>
        <tr>
            <td>出生年月</td>
            <td><input name="birthdate" id="birthdate" value=""></td>
        </tr>
        <tr>
            <td>民族</td>
            <td><input name="nation" id="nation" value=""></td>
        </tr>
        <tr>
            <td>政治面貌</td>
            <td><input name="politics" id="politics" value=""></td>
        </tr>
        <tr>
            <td>毕业时间</td>
            <td><input name="graduation" id="graduation" value=""></td>
        </tr>
        <tr>
            <td>毕业院校</td>
            <td><input name="school" id="school" value=""></td>
        </tr>
        <tr>
            <td>所学专业</td>
            <td><input name="major" id="major" value=""></td>
        </tr>
        <tr>
            <td>学历</td>
            <td><input name="education" id="education" value=""></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input name="email" id="email" value=""></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><input name="phone" id="phone" value=""></td>
        </tr>
        <tr>
            <td>外语水平</td>
            <td><input name="foreignlanguage" id="foreignlanguage" value=""></td>
        </tr>
        <tr>
            <td>特长爱好</td>
            <td><input name="hobby" id="hobby" value=""></td>
        </tr>
        <tr>
            <td>社会实践经历</td>
            <td><textarea name="practice" id="practice"></textarea></td>
        </tr>
        <tr>
            <td>在校担任职务</td>
            <td><textarea name="position" id="position"></textarea></td>
        </tr>
        <tr>
            <td>在校获奖</td>
            <td><textarea name="honor" id="honor"></textarea></td>
        </tr>
        <tr>
            <td>科研成果</td>
            <td><textarea name="research" id="research"></textarea></td>
        </tr>
        <tr>
            <td>自我评价</td>
            <td><textarea name="selfevaluation" id="selfevaluation"></textarea></td>
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
