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
        function edit(rid,page) {
            layer.open({
                type: 2,
                title: '修改招聘信息',
                maxmin: true,
                area: ['500px', '400px'],
                content:[ '/jygl/recruitManage?action=edit&rid='+rid+'&page='+page,'no'],
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
<%@include file="recruit.jsp"%>
        <td>
            <a href="javascript:void (0);" onclick="edit(<%=recruit.getRid()%>)">修改</a>
            <a href="/jygl/recruitManage?action=delete&rid=<%=recruit.getRid()%>&page=<%=p.getCurrentPage()%>"
               onclick="return confirm('确定删除吗？')">删除</a>
        </td>
    </tr>
    <%
            i++;
        }%>
</table>
<div>
    <%@include file="../pager.jsp"%>
</div>
</body>
</html>
