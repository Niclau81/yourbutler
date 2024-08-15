package com.yourbutler.fpv6.dao;

import com.yourbutler.fpv6.model.CookingStep;
import com.yourbutler.fpv6.model.Cuisine;
import com.yourbutler.fpv6.model.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    public List<Recipe> searchRecipesByIngredientsAndCuisine(List<Integer> ingredientIds, Integer cuisineId) {
        List<Recipe> recipes = new ArrayList<>();
        String placeholders = String.join(",", java.util.Collections.nCopies(ingredientIds.size(), "?"));
        String sql = "SELECT DISTINCT r.* FROM recipe r " +
                "JOIN recipe_ingredient ri ON r.recipe_id = ri.recipe_id " +
                "WHERE ri.ingredient_id IN (" + placeholders + ") " +
                (cuisineId != null ? "AND r.cuisine_id = ? " : "") +
                "GROUP BY r.recipe_id " +
                "HAVING COUNT(DISTINCT ri.ingredient_id) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            for (Integer ingredientId : ingredientIds) {
                pstmt.setInt(paramIndex++, ingredientId);
            }
            if (cuisineId != null) {
                pstmt.setInt(paramIndex++, cuisineId);
            }
            pstmt.setInt(paramIndex, ingredientIds.size());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Recipe recipe = new Recipe(
                            rs.getInt("recipe_id"),
                            rs.getString("recipe_name"),
                            rs.getInt("cuisine_id"),
                            rs.getString("cooking_time"),
                            rs.getString("photo")
                    );
                    recipe.setCookingSteps(getCookingSteps(rs.getInt("recipe_id")));
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public List<Cuisine> getAllCuisines() {
        List<Cuisine> cuisines = new ArrayList<>();
        String sql = "SELECT * FROM cuisine ORDER BY cuisine_name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                cuisines.add(new Cuisine(
                        rs.getInt("cuisine_id"),
                        rs.getString("cuisine_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuisines;
    }
    private List<CookingStep> getCookingSteps(int recipeId) {
        List<CookingStep> steps = new ArrayList<>();
        String sql = "SELECT * FROM recipe_step WHERE recipe_id = ? ORDER BY step_number";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, recipeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CookingStep step = new CookingStep(
                            rs.getInt("step_id"),
                            rs.getInt("recipe_id"),
                            rs.getInt("step_number"),
                            rs.getString("step_description")
                    );
                    steps.add(step);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return steps;
    }public void insertRecipe(Recipe recipe) throws SQLException {
        String insertRecipeSql = "INSERT INTO recipe (recipe_name, cuisine_id, cooking_time, photo) VALUES (?, ?, ?, ?)";
        String insertStepSql = "INSERT INTO recipe_step (recipe_id, step_number, step_description) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtRecipe = conn.prepareStatement(insertRecipeSql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            pstmtRecipe.setString(1, recipe.getRecipeName());
            pstmtRecipe.setInt(2, recipe.getCuisineId());
            pstmtRecipe.setString(3, recipe.getCookingTime());
            pstmtRecipe.setString(4, recipe.getPhoto());

            int affectedRows = pstmtRecipe.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating recipe failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmtRecipe.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int recipeId = generatedKeys.getInt(1);

                    try (PreparedStatement pstmtStep = conn.prepareStatement(insertStepSql)) {
                        for (CookingStep step : recipe.getCookingSteps()) {
                            pstmtStep.setInt(1, recipeId);
                            pstmtStep.setInt(2, step.getStepNumber());
                            pstmtStep.setString(3, step.getStepDescription());
                            pstmtStep.addBatch();
                        }
                        pstmtStep.executeBatch();
                    }
                } else {
                    throw new SQLException("Creating recipe failed, no ID obtained.");
                }
            }

            conn.commit();
        } catch (SQLException e) {
            throw e;
        }
    }
}