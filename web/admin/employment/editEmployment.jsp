<%@ page import="jygl.beans.Employment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jygl/admin/js/jquery.min.js"></script>
    <script>
        function dosumit() {
            $.ajax({//异步提交
                type:'post',
                url:'/jygl/employmentManage?action=update',
                data:$("#form1").serialize(),//标准化数据
                success:function (obj) {
                    if (obj=="true"){
                        parent.layer.msg('修改成功', {icon: 6});
                        //var index=parent.layer.getFrameIndex(window.name);//当前窗口s索引
                        //setTimeout(function(){parent.layer.close(index)}, 1000);
                        setTimeout(function(){window.parent.location.reload();}, 500);//刷新数据
                        // alert("修改成功")
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
    Employment employment= (Employment) request.getAttribute("employment");
%>
<form id="form1">
    <table width="100%" height="100%" border="1" >
        <tr>
            <td>学生姓名</td>
            <td>
                <input type="text" name="studentname" id="studentname" value="<%=employment.getStudentname()%>">
                <input type="hidden" name="eid" value="<%=employment.getEid()%>">
            </td>
        </tr>
        <tr>
            <td>毕业学校</td>
            <td><input type="text" name="school" id="school" value="<%=employment.getSchool()%>"></td>
        </tr>
        <tr>
            <td>就职企业</td>
            <td><input type="text" name="companyname" id="companyname" value="<%=employment.getCompanyname()%>"></td>
        </tr>
        <tr>
            <td>就职岗位</td>
            <td><input type="text" name="position" id="position" value="<%=employment.getPosition()%>"></td>
        </tr>
        <tr align="center" style="display: none">
            <td colspan="2"><input type="button" value="提交" onclick="dosumit();" id="submit">
            <input type="reset" value="重置" id="reset"></td>
        </tr>
    </table>
</form>
</body>
</html>
