package ags.edu.cu.oca.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ags.edu.cu.oca.bean.Enrollment;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchEnrollServlet", value = "/searchEnroll.do")
public class SearchEnrollServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Enrollment> enrollments = Global.enrollService.queryLikeField(
                request.getParameter("field"),
                request.getParameter("value"));
        System.out.println(enrollments);
        request.setAttribute("resultList", enrollments);
        request.getRequestDispatcher("searchEnroll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
