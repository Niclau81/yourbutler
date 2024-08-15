package com.yourbutler.fpv6.servlet;
import com.yourbutler.fpv6.model.Recipe;
import com.yourbutler.fpv6.service.RecipeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {
    private RecipeService recipeService = new RecipeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] ingredientIdsStr = request.getParameterValues("ingredientIds");
            String cuisineIdStr = request.getParameter("cuisineId");

            System.out.println("Recipe search request - Ingredients: " + Arrays.toString(ingredientIdsStr) + ", Cuisine: " + cuisineIdStr);

            if (ingredientIdsStr == null || ingredientIdsStr.length == 0) {
                System.out.println("No ingredients selected");
                request.setAttribute("error", "Please select at least one ingredient.");
                request.getRequestDispatcher("/WEB-INF/jsp/ingredientList.jsp").forward(request, response);
                return;
            }

            List<Integer> ingredientIds = Arrays.stream(ingredientIdsStr)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            Integer cuisineId = cuisineIdStr != null && !cuisineIdStr.isEmpty() ? Integer.parseInt(cuisineIdStr) : null;

            System.out.println("Parsed ingredient IDs: " + ingredientIds + ", Cuisine ID: " + cuisineId);

            List<Recipe> recipes = recipeService.searchRecipesByIngredientsAndCuisine(ingredientIds, cuisineId);

            System.out.println("Recipe search results count: " + recipes.size());

            if (recipes.isEmpty()) {
                System.out.println("No recipes found");
                request.setAttribute("message", "No recipes found with the selected ingredients and cuisine.");
            } else {
                request.setAttribute("recipes", recipes);
            }

            request.getRequestDispatcher("/WEB-INF/jsp/recipeDetails.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ingredient or cuisine IDs: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Invalid ingredient or cuisine selection.");
            request.getRequestDispatcher("/WEB-INF/jsp/ingredientList.jsp").forward(request, response);
        } catch (RuntimeException e) {
            System.err.println("Runtime error in RecipeServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while searching for recipes: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/ingredientList.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Unexpected error in RecipeServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/ingredientList.jsp").forward(request, response);
        }
    }
}