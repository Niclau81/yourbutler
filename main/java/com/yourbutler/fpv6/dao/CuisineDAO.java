package com.yourbutler.fpv6.dao;
import com.yourbutler.fpv6.model.Cuisine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuisineDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/recipe_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "P@ss2468";

    public List<Cuisine> getAllCuisines() {
        List<Cuisine> cuisines = new ArrayList<>();
        String sql = "SELECT * FROM cuisine ORDER BY cuisine_name";

        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            System.out.println("Database connection established successfully");
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("cuisine_id");
                        String name = rs.getString("cuisine_name");
                        cuisines.add(new Cuisine(id, name));
                        System.out.println("Added cuisine: ID=" + id + ", Name=" + name);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getAllCuisines: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error in getAllCuisines: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Total cuisines retrieved: " + cuisines.size());
        return cuisines;
    }
}