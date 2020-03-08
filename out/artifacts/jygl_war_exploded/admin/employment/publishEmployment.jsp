<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
</head>
<body>
<form action="/jygl/employmentManage?action=add" method="post">
    <table width="600px" border="1" >
        <tr>
            <td colspan="2" align="center">发布就业信息</td>
        </tr>
        <tr>
            <td>学生姓名</td>
            <td><input type="text" name="studentname" id="studentname"></td>
        </tr>
        <tr>
            <td>毕业学校</td>
            <td><input type="text" name="school" id="school"></td>
        </tr>
        <tr>
            <td>就职企业</td>
            <td><input type="text" name="companyname" id="companyname"></td>
        </tr>
        <tr>
            <td>就职岗位</td>
            <td><input type="text" name="position" id="position"></td>
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
