<%@ page import="jygl.beans.Employment" %>
<%@ page import="jygl.beans.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jygl/admin/js/jquery.min.js"></script>
    <script>
        function dosumit() {
            $.ajax({//异步提交
                type:'post',
                url:'/jygl/companyManage?action=update',
                data:$("#form1").serialize(),//标准化数据
                success:function (obj) {
                    if (obj=="true"){
                        parent.layer.msg('修改成功', {icon: 6});
                        setTimeout(function(){window.parent.location.reload();}, 500);//刷新数据
                    }else {
                        parent.layer.msg('修改失败,请稍后重试', {icon: 5});
                    }
                }
            });
        }
    </script>
</head>
<body>
<%
    Company company= (Company) request.getAttribute("company");
%>
<form id="form1">
    <table width="100%" height="100%" border="1" >
        <tr>
            <td>企业名称</td>
            <td>
                <input type="text" name="companyname" id="companyname" value="<%=company.getCompanyname()%>">
                <input type="hidden" name="cid" value="<%=company.getCid()%>">
            </td>
        </tr>
        <tr>
            <td>企业类型</td>
            <td><input type="text" name="unitproperty" id="unitproperty" value="<%=company.getUnitproperty()%>"></td>
        </tr>
        <tr>
            <td>企业方向</td>
            <td><input type="text" name="industry" id="industry" value="<%=company.getIndustry()%>"></td>
        </tr>
        <tr>
            <td>营业执照号</td>
            <td><input type="text" name="licensenumber" id="licensenumber" value="<%=company.getLicensenumber()%>"></td>
        </tr>
        <tr>
            <td>企业规模</td>
            <td><input type="text" name="unitscale" id="unitscale" value="<%=company.getUnitscale()%>"></td>
        </tr>
        <tr>
            <td>企业地址</td>
            <td><input type="text" name="address" id="address" value="<%=company.getAddress()%>"></td>
        </tr>
        <tr>
            <td>企业主页</td>
            <td><input type="text" name="webaddress" id="webaddress" value="<%=company.getWebaddress()%>"></td>
        </tr>
        <tr>
            <td>公司联系人</td>
            <td><input type="text" name="linkman" id="linkman" value="<%=company.getLinkman()%>"></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><input type="text" name="telephone" id="telephone" value="<%=company.getTel()%>"></td>
        </tr>
        <tr>
            <td>电子邮箱</td>
            <td><input type="text" name="email" id="email" value="<%=company.getEmail()%>"></td>
        </tr>
        <tr>
            <td>邮政编码</td>
            <td><input type="text" name="postcode" id="postcode" value="<%=company.getPostcode()%>"></td>
        </tr>
        <tr align="center" style="display: none">
            <td colspan="2"><input type="button" value="提交" onclick="dosumit();" id="submit">
                <input type="reset" value="重置" id="reset"></td>
        </tr>
    </table>
</form>
</body>
</html>
