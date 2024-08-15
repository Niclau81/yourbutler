package com.yourbutler.fpv6.servlet;
import com.yourbutler.fpv6.dao.RecipeDAO;
import com.yourbutler.fpv6.model.Recipe;
import com.yourbutler.fpv6.model.CookingStep;
import com.yourbutler.fpv6.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addRecipe")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddRecipeServlet extends HttpServlet {
    private RecipeDAO recipeDAO;

    public void init() {
        recipeDAO = new RecipeDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdministrator()) {
            response.sendRedirect("login.jsp");
            return;
        }

        String recipeName = request.getParameter("recipeName");
        int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
        String cookingTime = request.getParameter("cookingTime");
        Part filePart = request.getPart("recipeImage");
        String[] cookingStepsArray = request.getParameterValues("cookingSteps");

        // Handle file upload
        String fileName = getSubmittedFileName(filePart);
        String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }
        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);

        // Create Recipe object
        Recipe newRecipe = new Recipe(0, recipeName, cuisineId, cookingTime, filePath);

        // Create CookingStep objects
        List<CookingStep> cookingSteps = new ArrayList<>();
        for (int i = 0; i < cookingStepsArray.length; i++) {
            CookingStep step = new CookingStep(0, 0, i + 1, cookingStepsArray[i]);
            cookingSteps.add(step);
        }
        newRecipe.setCookingSteps(cookingSteps);

        try {
            // Add method to insert recipe and its steps
            recipeDAO.insertRecipe(newRecipe);
            response.sendRedirect(request.getContextPath() + "/admin");
        } catch (Exception e) {
            request.setAttribute("error", "Error adding recipe: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
