<%@ page import="jygl.beans.Student" %>
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
                url:'/jygl/studentManage?action=update',
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
    Student student= (Student) request.getAttribute("student");
%>
<form id="form1">
    <table width="100%" height="100%" border="1" >
        <tr>
            <td>学生名字</td>
            <td>
                <input type="text" name="sname" id="sname" value="<%=student.getSname()%>">
                <input type="hidden" name="sid" value="<%=student.getSid()%>">
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input type="text" name="gender" id="gender" value="<%=student.getGender()%>"></td>
        </tr>
        <tr>
            <td>身份证号码</td>
            <td><input type="text" name="idnumber" id="idnumber" value="<%=student.getIdnumber()%>"></td>
        </tr>
        <tr>
            <td>毕业学校</td>
            <td><input type="text" name="school" id="school" value="<%=student.getSchool()%>"></td>
        </tr>
        <tr>
            <td>系别</td>
            <td><input type="text" name="department" id="department" value="<%=student.getDepartment()%>"></td>
        </tr>
        <tr>
            <td>专业</td>
            <td><input type="text" name="major" id="major" value="<%=student.getMajor()%>"></td>
        </tr>
        <tr>
            <td>学历</td>
            <td><input type="text" name="education" id="education" value="<%=student.getEducation()%>"></td>
        </tr>
        <tr>
            <td>入学日期</td>
            <td><input type="text" name="entrancedate" id="entrancedate" value="<%=student.getEntrancedate()%>"></td>
        </tr>
        <tr>
            <td>籍贯</td>
            <td><input type="text" name="nativeplace" id="nativeplace" value="<%=student.getNativeplace()%>"></td>
        </tr>
        <tr align="center" style="display: none">
            <td colspan="2"><input type="button" value="提交" onclick="dosumit();" id="submit">
                <input type="reset" value="重置" id="reset"></td>
        </tr>
    </table>
</form>
</body>
</html>
