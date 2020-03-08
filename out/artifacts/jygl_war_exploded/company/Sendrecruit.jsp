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
    String cid = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    cid = String.valueOf(user.getId());
%>
<form action="/jygl/recruitManage?action=add" method="post">
    <table width="600px" border="1">
        <tr>
            <td colspan="2" align="center">发布招聘信息</td>
        </tr>
        <input type="hidden" name="cid" id="cid" value="<%=cid%>">
        <tr>
            <td>企业名称</td>
            <td><input type="text" name="companyname" id="companyname"></td>
        </tr>
        <tr>
            <td>企业地址</td>
            <td><input type="text" name="address" id="address"></td>
        </tr>
        <tr>
            <td>企业邮编</td>
            <td><input type="text" name="postcode" id="postcode"></td>
        </tr>
        <tr>
            <td>招聘人数</td>
            <td><input type="text" name="recruitment" id="recruitment"></td>
        </tr>
        <tr>
            <td>工作地点</td>
            <td><input type="text" name="workingplace" id="workingplace"></td>
        </tr>
        <tr>
            <td>企业类型</td>
            <td><input type="text" name="positiontype" id="positiontype"></td>
        </tr>
        <tr>
            <td>学历要求</td>
            <td><input type="text" name="edurequire" id="edurequire"></td>
        </tr>
        <tr>
            <td>职位描述和要求</td>
            <td><input type="text" name="description" id="description"></td>
        </tr>
        <tr>
            <td>部门</td>
            <td><input type="text" name="branch" id="branch"></td>
        </tr>
        <tr>
            <td>联系人</td>
            <td><input type="text" name="linkman" id="linkman"></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><input type="text" name="telephone" id="telephone"></td>
        </tr>
        <tr>
            <td>企业主页</td>
            <td><input type="text" name="hostpage" id="hostpage"></td>
        </tr>
        <tr>
            <td>电子邮箱</td>
            <td><input type="text" name="email" id="email"></td>
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
