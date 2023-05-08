package ags.edu.cu.oca.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ags.edu.cu.oca.bean.Enrollment;
import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.service.CourseService;
import ags.edu.cu.oca.service.EnrollService;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;

@WebServlet(name = "UnEnroll", value = "/unEnroll.do")
public class UnEnrollServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService courseService = Global.courseService;
        EnrollService service = Global.enrollService;


        Enrollment enrollment = new Enrollment();

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        enrollment.setCourse(courseService.queryByID(request.getParameter("course_id")));
        enrollment.setApplicant((User)request.getSession().getAttribute("user"));
        service.withdraw(enrollment);

        response.getWriter().print("<script>alert('退课成功！');");
        response.getWriter().println("location.href='main.jsp'</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
