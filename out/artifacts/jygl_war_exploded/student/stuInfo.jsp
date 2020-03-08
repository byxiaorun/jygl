<%@ page import="jygl.beans.Student" %>
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
        function edit(sid,page) {
            layer.open({
                type: 2,
                title: '修改学生资料',
                maxmin: true,
                area: ['500px', '600px'],
                content:[ '/jygl/studentManage?action=edit&sid='+sid+'&page='+page,'no'],
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
    Student stu= (Student) request.getAttribute("student");
%>
<table width="100%" border="1">
    <tr>
        <td>编号</td>
        <td><%=stu.getSid()%></td>
    </tr>
    <tr>
        <td>姓名</td>
        <td><%=stu.getSname()%></td>
    </tr>
    <tr>
        <td>性别</td>
        <td><%=stu.getGender()%></td>
    </tr>
    <tr>
        <td>身份证号码</td>
        <td><%=stu.getIdnumber()%></td>
    </tr>
    <tr>
        <td>毕业院校</td>
        <td><%=stu.getSchool()%></td>
    </tr>
    <tr>
        <td>系别</td>
        <td><%=stu.getDepartment()%></td>
    </tr>
    <tr>
        <td>专业</td>
        <td><%=stu.getMajor()%></td>
    </tr>
    <tr>
        <td>学历</td>
        <td><%=stu.getEducation()%></td>
    </tr>
    <tr>
        <td>入学时间</td>
        <td><%=stu.getEntrancedate()%></td>
    </tr>
    <tr>
        <td>籍贯</td>
        <td><%=stu.getNativeplace()%></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <a href="javascript:void (0);" onclick="edit(<%=stu.getSid()%>)">修改资料</a>
        </td>
    </tr>
</table>
</body>
</html>
