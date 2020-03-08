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
    String username = "", usertype = "";
    if (session.getAttribute("user") == null) {
        //判断是否登录
        Utils.gotoPage("/public/login.jsp", request, response);
    } else {
        //获取登录信息，转换成user对象
        user = (User) session.getAttribute("user");
        usertype = user.getUsertypes();//获取用户类型
        //获取登录用户名
        username = user.getUsername();
        if (!usertype.equals("admin")) {
            Utils.gotoPage("/public/login.jsp", request, response);
        }
    }
%>
<%--左侧栏--%>
<div class="left">
    <div class="bigTitle">就业管理系统管理页</div>
    <div class="lines">
        <div onclick="pageClick(this)" data-url="/jygl/admin/right.jsp" class="active"><img
                src="/jygl/admin/img/icon-2.png"/> 首页
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/userManage?action=list" class="active"><img
                src="/jygl/admin/img/icon-1.png"/> 用户管理
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/public/register.jsp" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 添加学生信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/studentManage?action=list" class="active"><img
                src="/jygl/admin/img/icon-4.png"/> 管理学生信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/public/register.jsp" class="active"><img
                src="/jygl/admin/img/icon-5.png"/> 添加企业信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/companyManage?action=list" class="active"><img
                src="/jygl/admin/img/icon-1.png"/> 管理企业信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/recruitManage?action=list" class="active"><img
                src="/jygl/admin/img/icon-2.png"/> 管理招聘信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/admin/employment/publishEmployment.jsp" class="active"><img
                src="/jygl/admin/img/icon-5.png"/> 发布就业信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/employmentManage?action=list" class="active"><img
                src="/jygl/admin/img/icon-4.png"/> 管理就业信息
        </div>
        <div onclick="pageClick(this)" data-url="/jygl/messageManage?action=listall" class="active"><img
                src="/jygl/admin/img/icon-3.png"/> 留言管理
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
        <div class="thisUser">当前用户：
        <%=username%>
    </a>
</div>
</div>
<div class="content">
    <iframe name="iframe" id="iframe" style="width: 100%" frameborder="0" onload="iframeLoad()"/>
</div>
</body>
</html>
