package com.yourbutler.fpv6.filter;

import com.yourbutler.fpv6.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAdminPage = request.getRequestURI().startsWith(request.getContextPath() + "/admin");

        if (isAdminPage) {
            if (isLoggedIn && ((User) session.getAttribute("user")).isAdministrator()) {
                chain.doFilter(req, res);
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig config) throws ServletException {}

    public void destroy() {}
}