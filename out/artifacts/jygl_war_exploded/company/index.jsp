<%@ page import="jygl.beans.User" %>
<%@ page import="jygl.common.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理首页</title>
    <link rel="stylesheet" href="/jygl/admin/css/page.css"/>
    <script type="text/javascript" src="/jygl/admin/js/jquery.min.js"></script>
    <script type="text/javascript" src="/jygl/admin/js/index.js"></script>
    <script>
        function iframeLoad() {
            $("#iframe").height($(window).height() - 120);
        }
    </script>
</head>
<body>
<%
    User user = null;
    String username = "", usertype = "",cid="";
    if (session.getAttribute("user") == null) {
        //判断是否登录
        Utils.gotoPage("/public/login.jsp", request, response);
    } else {
        //获取登录信息，转换成user对象
        user = (User) session.getAttribute("user");
        usertype = user.getUsertypes();//获取用户类型
        //获取登录用户名
        username = user.getUsername();
        cid= String.valueOf(user.getId());
        if (!usertype.equals("company")) {
            Utils.gotoPage("/public/login.jsp", request, response);
        }
    }
%>
<%--左侧栏--%>
<div class="left">
    <div class="bigTitle">企业就业管理系统</div>
    <div class="lines">
        <div onclick="pageClick(this)" data-url="/jygl/public/right.jsp" class="active"><img
                src="/jygl/admin/img/icon-2.png"/> 首页
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/companyManage?action=showDetail2&cid=<%=cid%>" class="active"><img
                src="/jygl/admin/img/icon-1.png"/> 修改资料
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/company/Sendrecruit.jsp" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 发布招聘信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/recruitManage?action=list2&cid=<%=cid%>" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 管理招聘信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/resumeManage?action=list&cid=<%=cid%>" class="active"><img
                src="/jygl/admin/img/icon-4.png"/> 查看学生简历
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/employmentManage?action=list" class="active"><img
                src="/jygl/admin/img/icon-4.png"/> 管理就业信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/student/Sendmessage.jsp" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 给管理员留言
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/messageManage?action=list&uid=<%=cid%>" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 查看管理员留言回复
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/public/updatepassword.jsp" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 修改密码
        </div>
        <div class="active" id="alink">
            <img src="/jygl/admin/img/icon-3.png"/>
            <a href="/jygl/layout" onclick="return confirm('确定退出吗？')">退出登录</a>
        </div>
    </div>
</div>
<div class="top">
    <div class="leftTiyle" id="flTitle">首页</div>
    <a href="/jygl/layout" onclick="return confirm('确定退出吗？')">
        <div class="thisUser">当前企业：<%=username%>
    </a>
</div>
</div>
<div class="content">
    <iframe name="iframe" id="iframe" style="width: 100%" frameborder="0" onload="iframeLoad()"/>
</div>
</body>
</html>
