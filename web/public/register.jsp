<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <style type="text/css">
        .errormsg {
            font-size: 9pt;
            color: #ff0000;
        }
    </style>
</head>
<body>
<%
    Object obj = request.getAttribute("errorMsg");   //接收RegisterServlet传过来的errorMsg
    String msg = "";
    if (obj != null) {    //如果有errorMsg
        msg = String.valueOf(obj);   //将errorMsg的值放入msg变量
    }
%>
<form id="form1" name="form1" method="post" action="/jygl/register">
    <div align="center">
        <table width="422" height="195" border="1">
            <tr>
                <td height="39" colspan="2" align="center" scope="col"><strong>用户注册</strong></td>
            </tr>
            <tr>
                <td width="109" height="44">用户名：</td>
                <td width="298">
                    <input type="text" name="username" id="username" required="required"/>
                    <span class="errormsg"><%=msg%>
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password" id="password" required="required"/></td>
            </tr>
            <tr>
                <td>用户类型：</td>
                <td><p>
                    <input name="usertypes" type="radio" value="student" checked="checked"/>学生
                    <input type="radio" name="usertypes" value="company"/> 企业
                    <input type="radio" name="usertypes" value="admin"/>管理员
                </p></td>
            </tr>
            <tr>
                <td height="39" colspan="2" align="center"><span class="td">
                <input type="submit" value="注册"/>
                <input type="reset" value="取消"/>
              </span></td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
