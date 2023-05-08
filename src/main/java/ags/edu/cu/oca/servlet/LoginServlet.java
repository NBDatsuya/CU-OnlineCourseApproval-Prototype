package ags.edu.cu.oca.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.util.Global;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        User reqItem = new User();
        reqItem.setUName(req.getParameter("username"));
        reqItem.setPassword(req.getParameter("password"));

        String url = "login.jsp?error=1";


        reqItem = Global.userService.verifyLogin(reqItem);

        if (reqItem.getUName() != null) {
            HttpSession session = req.getSession();

            session.setAttribute("user", reqItem);

            url = "main.jsp";
        }

        resp.sendRedirect(url);
    }
}
