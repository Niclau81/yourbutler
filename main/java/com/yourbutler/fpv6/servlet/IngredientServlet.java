package com.yourbutler.fpv6.servlet;

import com.yourbutler.fpv6.model.Cuisine;
import com.yourbutler.fpv6.model.Ingredient;
import com.yourbutler.fpv6.service.IngredientService;
import com.yourbutler.fpv6.service.RecipeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ingredient")
public class IngredientServlet extends HttpServlet {
    private IngredientService ingredientService = new IngredientService();
    private RecipeService recipeService = new RecipeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        List<Cuisine> cuisines = recipeService.getAllCuisines();
        request.setAttribute("ingredients", ingredients);
        request.setAttribute("cuisines", cuisines);
        request.getRequestDispatcher("/WEB-INF/jsp/ingredientList.jsp").forward(request, response);
    }
}
