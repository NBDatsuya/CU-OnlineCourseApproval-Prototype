<%@ page import="ags.edu.cu.oca.bean.Course" %>
<%@ page import="ags.edu.cu.oca.util.Global" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: Huang Samuel
  Date: 2023/5/5
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Course course = null;
    if (request.getParameter("flag") != null) {
        String flag = request.getParameter("flag");
        if (flag.equals("add")) {
            String cid = UUID.randomUUID().toString();
        } else {
            course =
                    Global.courseService.queryByID(
                            request.getParameter("course_id"));
        }
    }
%>
<html>
<head>
    <title>加大课程审批系统 - 课程信息</title>
    <link rel="stylesheet" href="css/style.css"/>
    <script>
        function bfSubmit() {
            if ((document.getElementsByName("c_name")[0].value === "") ||
                (document.getElementsByName("instructor")[0].value === "") ||
                (document.getElementsByName("schedule")[0].value === "")) {

                alert("课程名、授课教师和日程安排均不能为空!")
                return
            }

            if (confirm("确定保存？"))
                document.getElementById('form').submit()
        }
    </script>
</head>
<body>
<div id="page-container">
    <%
        if (request.getParameterMap().size()!=0 && (request.getParameter("flag") != null) && (
                (request.getParameter("course_id") != null) || request.getParameter("flag").equals("add"))) {
    %>
    <h1 style="color: #705992;">课程信息</h1>
    <form action="${pageContext.request.contextPath}/editCourse.do" method="POST" id="form">
        <table>
            <tr>课程名</tr>
            <tr><input type="text" name="c_name" value="<%= course!=null?course.getCourseName():""%>"
                       required="required"/></tr>
            <tr>授课教师</tr>
            <tr><input type="text" name="instructor" value="<%= course!=null?course.getInstructor():""%>"
                       required="required"/></tr>
            <tr>日程安排</tr>
            <tr><input type="text" name="schedule" value="<%= course!=null?course.getSchedule():""%>"
                       required="required"/></tr>
            <tr>
                <input type="button" value="提交" onclick="bfSubmit()"/>
            </tr>
        </table>
        <input type="hidden" name="flag" value="<%= request.getParameter("flag") %>"/>
        <input type="hidden" name="c_id" value="<%= request.getParameter("course_id") %>"/>
    </form>
    <%} else {%>
    <div style="color: orangered;text-align: center">
        <div>参数错误</div>
        <a href="javascript:window.history.back()">&lt;&lt;返回上一页</a>
    </div>
    <%}%>
</div>
</body>
</html>
