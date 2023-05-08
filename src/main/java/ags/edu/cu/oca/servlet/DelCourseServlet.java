package ags.edu.cu.oca.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;

@WebServlet(name = "DelCourseServlet", value = "/delCourse.do")
public class DelCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = Global.courseService.queryByID(request.getParameter("course_id"));
        Global.courseService.del(course);

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("<script>alert('删除成功！');");
        response.getWriter().println("location.href='main.jsp'</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
