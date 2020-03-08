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
        function showDetail(mid) {
            layer.open({
                type: 2,
                title: '回复留言信息',
                maxmin: true,
                area: ['400px', '500px'],
                content: '/jygl/messageManage?action=showDetail&mid=' + mid,
                btn: ['确定', '关闭']
            });
        }
    </script>
</head>
<body>
<%@include file="message.jsp"%>
<td><a href="javascript:void (0);" onclick="showDetail(<%=message.getMid()%>)">回复</a>
    <a href="/jygl/messageManage?action=delete&mid=<%=message.getMid()%>&page=<%=p.getCurrentPage()%>"
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