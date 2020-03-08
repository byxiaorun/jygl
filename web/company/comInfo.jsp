<%@ page import="jygl.beans.Student" %>
<%@ page import="jygl.beans.Company" %>
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
        function edit(cid,page) {
            layer.open({
                type: 2,
                title: '修改企业资料',
                maxmin: true,
                area: ['500px', '600px'],
                content:[ '/jygl/companyManage?action=edit&cid='+cid+'&page='+page,'no'],
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
    Company com= (Company) request.getAttribute("company");
%>
<div>
<table width="100%" height="100%" border="1">
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
        <td>公司邮编</td>
        <td><%=com.getPostcode()%></td>
    </tr>
    <tr>
    <td colspan="2" align="center">
        <a href="javascript:void (0);" onclick="edit(<%=com.getCid()%>)">修改资料</a>
    </td>
    </tr>
</table>
</div>
</body>
</html>
