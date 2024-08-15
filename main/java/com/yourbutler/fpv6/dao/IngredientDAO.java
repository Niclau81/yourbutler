package com.yourbutler.fpv6.dao;


import com.yourbutler.fpv6.model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngredientDAO {
    private static final Logger LOGGER = Logger.getLogger(IngredientDAO.class.getName());

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredient ORDER BY ingredient_name";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ingredient ingredient = new Ingredient(
                        rs.getInt("ingredient_id"),
                        rs.getString("ingredient_name")
                );
                ingredients.add(ingredient);
                LOGGER.info("Retrieved ingredient: " + ingredient.getIngredientName());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving ingredients", e);
        }
        LOGGER.info("Total ingredients retrieved: " + ingredients.size());
        return ingredients;
    }
}
