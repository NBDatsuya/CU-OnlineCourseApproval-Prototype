package ags.edu.cu.oca.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ags.edu.cu.oca.bean.Enrollment;
import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ExamineServlet", value = "/examine.do")
public class ExamineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enrollment enrollment =
                Global.enrollService.queryByID(
                        request.getParameter("enroll_id"));

        enrollment.setExamineUser((User) request.getSession().getAttribute("user"));
        enrollment.setEDate(LocalDateTime.now().
                format(DateTimeFormatter.
                        ofPattern("yyyy-MM-dd HH:mm:ss")));
        enrollment.setStatus(
                request.getParameter("result").equals("0") ? "refused" : "approved");
        Global.enrollService.statusChange(enrollment);

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print("<script>alert('操作成功！');");
        response.getWriter().println("location.href='main.jsp'</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
