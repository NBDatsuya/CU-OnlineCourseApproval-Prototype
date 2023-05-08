<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆 - 加里敦大学课程审批系统</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div id="page-container">
    <h1 style="color: #705992;">加里敦大学 统一身份验证</h1>
    <div style="color: orangered;text-align: center">
        <% if (request.getParameter("error") != null) {%>
            <span id="error">用户名或者密码错误</span>
        <%}%>
    </div>
    <form action="${pageContext.request.contextPath}/login" method="POST">
        <label for="username">用户名</label>
        <input type="text" id="username" name="username" required="required">
        <label for="password">密码</label>
        <input type="password" id="password" name="password" required="required">
        <input type="submit" value="登陆">
    </form>
</div>

</body>
</html>
