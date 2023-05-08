package ags.edu.cu.oca.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EditCourseServlet", value = "/editCourse.do")
public class EditCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = new Course(request.getParameter("c_id"),
                request.getParameter("c_name"),
                request.getParameter("instructor"),
                request.getParameter("schedule")
        );

        String title = "";
        if (request.getParameter("flag").equals("add")) {
            course.setUuid(UUID.randomUUID().toString());
            Global.courseService.add(course);
            title = "添加";
        } else {
            Global.courseService.edit(course);
            title = "修改";
        }

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("<script>alert('" + title + "成功！');");
        response.getWriter().println("location.href='main.jsp'</script>");
    }
}
