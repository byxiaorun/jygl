<%@ page import="jygl.beans.Recruit" %>
<%@ page import="jygl.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jygl/admin/js/jquery.min.js"></script>
    <script>
        function dosumit() {
            $.ajax({//异步提交
                type:'post',
                url:'/jygl/recruitManage?action=update',
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
    Recruit recruit= (Recruit) request.getAttribute("recruit");
    User user = null;
    String cid = "";
    //获取登录信息，转换成user对象
    user = (User) session.getAttribute("user");
    cid = String.valueOf(user.getId());
%>
<form id="form1">
    <table width="100%" height="100%" border="1" >
        <tr>
            <td>企业名称</td>
            <td>
                <input type="text" name="companyname" id="companyname" value="<%=recruit.getCompanyname()%>">
                <input type="hidden" name="rid" value="<%=recruit.getRid()%>">
                <input type="hidden" name="cid" value="<%=cid%>">
            </td>
        </tr>
        <tr>
            <td>企业地址</td>
            <td><input type="text" name="address" id="address" value="<%=recruit.getAddress()%>"></td>
        </tr>
        <tr>
            <td>企业邮编</td>
            <td><input type="text" name="postcode" id="postcode" value="<%=recruit.getPostcode()%>"></td>
        </tr>
        <tr>
            <td>招聘人数</td>
            <td><input type="text" name="recruitment" id="recruitment" value="<%=recruit.getRecruitment()%>"></td>
        </tr>
        <tr>
            <td>工作地点</td>
            <td><input type="text" name="workingplace" id="workingplace" value="<%=recruit.getWorkingplace()%>"></td>
        </tr>
        <tr>
            <td>企业类型</td>
            <td><input type="text" name="positiontype" id="positiontype" value="<%=recruit.getPositiontype()%>"></td>
        </tr>
        <tr>
            <td>学历要求</td>
            <td><input type="text" name="edurequire" id="edurequire" value="<%=recruit.getEdurequire()%>"></td>
        </tr>
        <tr>
            <td>职位描述和要求</td>
            <td><input type="text" name="description" id="description" value="<%=recruit.getDescription()%>"></td>
        </tr>
        <tr>
            <td>部门</td>
            <td><input type="text" name="branch" id="branch" value="<%=recruit.getBranch()%>"></td>
        </tr>
        <tr>
            <td>联系人</td>
            <td><input type="text" name="linkman" id="linkman" value="<%=recruit.getLinkman()%>"></td>
        </tr>
        <tr>
            <td>联系电话</td>
            <td><input type="text" name="telephone" id="telephone" value="<%=recruit.getTelephone()%>"></td>
        </tr>
        <tr>
            <td>企业主页</td>
            <td><input type="text" name="hostpage" id="hostpage" value="<%=recruit.getHostpage()%>"></td>
        </tr>
        <tr>
            <td>电子邮箱</td>
            <td><input type="text" name="email" id="email" value="<%=recruit.getEmail()%>"></td>
        </tr>
        <tr align="center" style="display: none">
            <td colspan="2"><input type="button" value="提交" onclick="dosumit();" id="submit">
                <input type="reset" value="重置" id="reset"></td>
        </tr>
    </table>
</form>
</body>
</html>
