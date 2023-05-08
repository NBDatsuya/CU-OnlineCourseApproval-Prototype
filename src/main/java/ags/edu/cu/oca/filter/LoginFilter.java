package ags.edu.cu.oca.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest)request;
        if(req.getSession().getAttribute("user") != null
                || req.getRequestURI().endsWith("log")
                || req.getRequestURI().endsWith("login")
                || req.getRequestURI().endsWith("login.jsp")) {
            chain.doFilter(req, response);
        }else{
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.sendRedirect("login.jsp");
        }
    }
}
