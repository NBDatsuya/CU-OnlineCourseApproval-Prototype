package ags.edu.cu.oca.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchCourseServlet", value = "/searchCourse.do")
public class SearchCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Course> courses = Global.courseService.queryLikeField(
                request.getParameter("field"),
                request.getParameter("value"));

        request.setAttribute("resultList", courses);
        request.getRequestDispatcher("searchCourse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
