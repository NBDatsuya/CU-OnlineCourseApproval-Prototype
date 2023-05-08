package ags.edu.cu.oca.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ags.edu.cu.oca.bean.User;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class PermissionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        User user = (User)request.getSession().getAttribute("user");

        if(request.getRequestURI().contains("course.jsp")){
            if(request.getSession() != null
                    && user!= null
                    && user.getRole().equals("tea")){
                chain.doFilter(req, resp);
            }else{
                HttpServletResponse hresp = (HttpServletResponse) resp;
                hresp.setCharacterEncoding("UTF-8");
                hresp.sendRedirect("PermissionDenied.html");
            }
        }else{
            chain.doFilter(req, resp);
        }
    }
}
