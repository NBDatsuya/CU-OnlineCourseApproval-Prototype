<%--
  Created by IntelliJ IDEA.
  User: Huang Samuel
  Date: 2023/5/6
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>搜索结果</title>
    <link rel="stylesheet" href="css/style1.css">
</head>
<body>
<div class="header">
    <h1>审批记录搜索</h1>
    <h2>搜索结果</h2>
</div>

<div class="container">
    <table align="center">
        <thead>
        <tr>
            <th>序号</th>
            <th>课程名</th>
            <th>授课教师</th>
            <th>课程安排</th>
            <th>申请人</th>
            <th>申请时间</th>
            <th>处理时间</th>
            <th>处理结果</th>
            <th>处理人</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="enroll" items="${requestScope.resultList}" varStatus="vst">
            <tr>
                <td>${vst.index+1}</td>
                <td>${enroll.getCourse().getCourseName()}</td>
                <td>${enroll.getCourse().getInstructor()}</td>
                <td>${enroll.getCourse().getSchedule()}</td>
                <td>${enroll.getApplicant().getRealName()}</td>
                <td>${enroll.getADate()}</td>
                <td>${enroll.getEDate()}</td>
                <td>${enroll.getStatusText()}</td>
                <td>${enroll.getExamineUser().getRealName()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/> <hr />
        <input type="button" value="关闭页面" onclick="window.close()"/>
</div>
</body>
</html>
