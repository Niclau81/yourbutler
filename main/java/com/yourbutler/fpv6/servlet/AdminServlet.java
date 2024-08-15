package com.yourbutler.fpv6.servlet;
import com.yourbutler.fpv6.model.User;
import com.yourbutler.fpv6.dao.CuisineDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdministrator()) {
            response.sendRedirect("login.jsp");
            return;
        }

        CuisineDAO cuisineDAO = new CuisineDAO();
        request.setAttribute("cuisines", cuisineDAO.getAllCuisines());

        request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}