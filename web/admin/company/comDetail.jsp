<%@ page import="jygl.beans.Student" %>
<%@ page import="jygl.beans.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/jygl/admin/css/style.css">
</head>
<body>
<%
    Company com= (Company) request.getAttribute("company");
%>
<table width="100%" border="1">
    <tr>
        <td>编号</td>
        <td><%=com.getCid()%></td>
    </tr>
    <tr>
        <td>公司名字</td>
        <td><%=com.getCompanyname()%></td>
    </tr>
    <tr>
        <td>公司性质</td>
        <td><%=com.getUnitproperty()%></td>
    </tr>
    <tr>
        <td>企业营业执照号</td>
        <td><%=com.getLicensenumber()%></td>
    </tr>
    <tr>
        <td>运营方向</td>
        <td><%=com.getIndustry()%></td>
    </tr>
    <tr>
        <td>公司规模</td>
        <td><%=com.getUnitscale()%></td>
    </tr>
    <tr>
        <td>公司地址</td>
        <td><%=com.getAddress()%></td>
    </tr>
    <tr>
        <td>公司主页</td>
        <td><%=com.getWebaddress()%></td>
    </tr>
    <tr>
        <td>公司联系人</td>
        <td><%=com.getLinkman()%></td>
    </tr>
    <tr>
        <td>联系电话</td>
        <td><%=com.getTel()%></td>
    </tr>
    <tr>
        <td>公司电子邮箱</td>
        <td><%=com.getEmail()%></td>
    </tr>
    <tr>
        <td>公司邮政邮箱</td>
        <td><%=com.getPostcode()%></td>
    </tr>
</table>
</body>
</html>
