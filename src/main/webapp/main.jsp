<%@ page import="ags.edu.cu.oca.bean.User" %>
<%@ page import="ags.edu.cu.oca.util.Global" %>
<jsp:useBean id="user" scope="session" type="ags.edu.cu.oca.bean.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // 课程列表
    session.setAttribute("unSelCourseList",
            Global.courseService.queryUnSelected(user));

    session.setAttribute("selCourseList",
            Global.courseService.querySelected(user));

    if (user.getRole().equals("stu")) {
        session.setAttribute("enrollList", Global.enrollService.queryByApplicant(user));
    } else {
        session.setAttribute("enrollList", Global.enrollService.queryByExamineUser(user));
        session.setAttribute("toExamine", Global.enrollService.queryUnExamined());
    }
%>

<html>
<head>
    <script lang="ts" src="ts/page-switch.ts"></script>
    <script lang="ts" src="controller/courselist.ts"></script>
    <script lang="ts" src="controller/examine.ts"></script>
    <script lang="ts" src="controller/search.ts"></script>
    <title>智慧加大 - 课程审批系统 - <%= user.getRoleText() %>主页</title>
    <link rel="stylesheet" href="css/style1.css">
</head>
<body>

<div class="header">
    <h1>加里敦大学 在线课程审批系统</h1>
    <p>你好，<%= user.getRealName() %>，你的身份是<strong><%= user.getRoleText() %>
    </strong></p>
    <div id="nav-bar">

        <a href="main.jsp#courseList" onclick="show_container(0)">课程列表</a>

        <%
            if (user.getRole().equals("tea")) {
        %>
        <a href="main.jsp#examine" onclick="show_container(1)">课程审批</a>
        <% }%>
        <a href="main.jsp#examineLog" onclick="show_container(2)">审批记录</a>
        <a href="${pageContext.request.contextPath}/logout">退出登陆</a>
    </div>
</div>

<div class="container" id="courseList" style="display: block">
    <h1 style="text-align: center"><%= user.getRole().equals("stu") ? "未选" : "" %>课程列表</h1>
    <div style="text-align: center">
        <input type="text" value="" id="courseSearchKey"/>
        <input type="button" value="搜索" onclick="searchCourse('c_name', document.getElementById('courseSearchKey').value)"/>
        <% if (user.getRole().equals("tea")) { %>
        <hr/>
        <input type="button" value="添加课程" onclick="add()"/>
        <%}%>
    </div>
    <div style="text-align: center">
        <table align="center">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程名</th>
                <th>授课教师</th>
                <th>课程安排</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${sessionScope.unSelCourseList}" varStatus="vst">
                <tr>
                    <td>${vst.index+1}</td>
                    <td>${course.getCourseName()}</td>
                    <td>${course.getInstructor()}</td>
                    <td>${course.getSchedule()}</td>

                    <% if (user.getRole().equals("stu")) { %>
                    <td>
                        <input type="button" value="选课" onclick="enroll(1, '${course.getUuid()}')"/>
                    </td>
                    <% } else { %>
                    <td>
                        <input type="button" value="编辑" onclick="edit('${course.getUuid()}')"/>

                        <c:choose>

                            <c:when test="${Global.enrollService.queryNotDeletable(course)==0}">
                                <input type="button" value="删除" onclick="del('${course.getUuid()}')"/>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <%}%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <% if (user.getRole().equals("stu")) { %>
    <br/>
    <hr/>
    <br/>
    <h1 style="text-align: center">已选课程列表</h1>
    <div style="text-align: center">
        <table align="center">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程名</th>
                <th>授课教师</th>
                <th>课程安排</th>
                <% if (user.getRole().equals("stu")) { %>
                <th>操作</th>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${sessionScope.selCourseList}" varStatus="vst">
                <tr>

                    <td>${vst.index+1}</td>
                    <td>${course.getCourseName()}</td>
                    <td>${course.getInstructor()}</td>
                    <td>${course.getSchedule()}</td>

                    <td>
                    <c:choose>

                        <c:when test="${Global.enrollService.queryNotUnenrollable(course, sessionScope.user)==0}">
                                <input type="button" value="退课" onclick="enroll(0, '${course.getUuid()}')"/>
                        </c:when>
                        <c:otherwise>
                            <span id="error" style="color: orangered">审批已经处理，无法退课</span>
                        </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <% } %>
</div>

<div class="container" id="examine" style="display:none;">
    <% if (user.getRole().equals("tea")) {%>
    <h1 style="text-align: center">课程审批</h1>
    <div style="text-align: center">
        <table>
            <thead>
            <tr>
                <th>序号</th>
                <th>课程名</th>
                <th>授课教师</th>
                <th>课程安排</th>
                <th>申请人</th>
                <th>申请日期</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="enroll" items="${toExamine}" varStatus="vst">
                <tr>
                    <td>${vst.index+1}</td>
                    <td>${enroll.getCourse().getCourseName()}</td>
                    <td>${enroll.getCourse().getInstructor()}</td>
                    <td>${enroll.getCourse().getSchedule()}</td>
                    <td>${enroll.getApplicant().getRealName()}</td>
                    <td>${enroll.getADate()}</td>
                    <td><input type="button" value="通过" onclick="examine(1,'${enroll.getUuid()}')"/>
                        <input type="button" value="不通过" onclick="examine(0,'${enroll.getUuid()}')"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <% } %>
</div>
<div class="container" id="examineLog" style="display:none;">
    <h1 style="text-align: center">审批记录</h1>
    <div style="text-align: center">
        <input type="text" value="" id="enrollSearchKey"/>
        <input type="button" value="搜索某一课程的审批记录" onclick="searchEnroll('c_name', document.getElementById('enrollSearchKey').value)"/>
    </div>
    <table>
        <thead>
        <tr>
            <th>序号</th>
            <th>课程名</th>
            <th>授课教师</th>
            <th>课程安排</th>
            <% if(user.getRole().equals("tea")) {%>
            <th>申请人</th>
            <%}%>
            <th>申请时间</th>
            <th>处理时间</th>
            <th>处理结果</th>
            <th>处理人</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="enroll" items="${sessionScope.enrollList}" varStatus="vst">
            <tr>
                <td>${vst.index+1}</td>
                <td>${enroll.getCourse().getCourseName()}</td>
                <td>${enroll.getCourse().getInstructor()}</td>
                <td>${enroll.getCourse().getSchedule()}</td>
                <% if(user.getRole().equals("tea")) {%>
                <td>${enroll.getApplicant().getRealName()}</td>
                <%}%>
                <td>${enroll.getADate()}</td>
                <td>${enroll.getEDate()}</td>
                <td>${enroll.getStatusText()}</td>
                <td>${enroll.getExamineUser().getRealName()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
