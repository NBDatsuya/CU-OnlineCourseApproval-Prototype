package ags.edu.cu.oca.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ags.edu.cu.oca.bean.Enrollment;
import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.service.CourseService;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@WebServlet(name = "Enroll", value = "/enroll.do")
public class EnrollServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = Global.courseService;

        Enrollment enrollment = new Enrollment(
                UUID.randomUUID().toString(),
                courseService.queryByID(request.getParameter("course_id")),
                (User)request.getSession().getAttribute("user"),
                null,
                "submitted",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                null
        );

        Global.enrollService.submit(enrollment);

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("<script>alert('选课成功！');");
        response.getWriter().println("location.href='main.jsp'</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
