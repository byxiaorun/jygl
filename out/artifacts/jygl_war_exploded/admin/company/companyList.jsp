<%@ page import="jygl.beans.Page" %>
<%@ page import="java.util.List" %>
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
        function showDetail(cid) {
            layer.open({
                type: 2,
                title: '查看企业信息',
                maxmin: true,
                area: ['400px', '500px'],
                content: '/jygl/companyManage?action=showDetail&cid=' + cid,
                btn: ['确定', '关闭']
            });
        }
    </script>
</head>
<body>
    <%
        Page p = (Page) request.getAttribute("page");//取出数据
        List list = p.getList();
        Company company = null;
    %>
    <table width="100%" border="1">
        <tr>
            <th>公司名字</th>
            <th>公司规模</th>
            <th>公司联系人</th>
            <th>公司地址</th>
            <th>公司性质</th>
            <th>操作</th>
        </tr>
        <%
            int i=0;
            while (i<list.size()) {
                company= (Company) list.get(i);
        %>
        <tr class="change">
            <td><%=company.getCompanyname()%>
            </td>
            <td><%=company.getUnitscale()%>
            </td>
            <td><%=company.getLinkman()%>
            </td>
            <td><%=company.getAddress()%>
            </td>
            <td><%=company.getUnitproperty()%>
            </td>
            <td><a href="javascript:void (0);" onclick="showDetail(<%=company.getCid()%>)">查看</a>
                <a href="/jygl/companyManage?action=delete&cid=<%=company.getCid()%>&page=<%=p.getCurrentPage()%>"
                   onclick="return confirm('确定删除吗？')">删除</a>
                <a href="/jygl/companyManage?action=zp&cid=<%=company.getCid()%>">发布招聘</a>
            </td>
        </tr>
        <%
                i++;
            }%>
    </table>
<div>
    <%@include file="../pager.jsp"%>
<%--    <a href="/jygl/companyManage?action=list&page=1">首页</a>--%>
<%--    <% if(p.getCurrentPage()>1){%>--%>
<%--    <a href="/jygl/companyManage?action=list&page=<%=p.getCurrentPage()+1%>">下一页</a>--%>
<%--    <%}%>--%>
<%--    <a href="/jygl/companyManage?action=list&page=<%=p.getTotalPage()%>">最后一页</a>--%>

<%--    &nbsp;&nbsp;当前第<%=p.getCurrentPage()%>页,共<%=p.getTotalPage()%>页--%>
</div>
</body>
</html>