package com.yourbutler.fpv6.dao;

import com.yourbutler.fpv6.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/recipe_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "P@ss2468";

    private static final String INSERT_USER_SQL = "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER_SQL = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?;";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";


    public UserDAO() {
        // Load the JDBC driver when the DAO is instantiated
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the MySQL driver in the classpath!", e);
        }
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database:");
            e.printStackTrace();
            throw e;  // Re-throw the exception to be handled by the caller
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.executeUpdate();
        }
    }

    public User selectUser(int id) throws SQLException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String userType = rs.getString("user_type");
                Timestamp createdAt = rs.getTimestamp("created_at");
                user = new User(id, username, password, email, userType, createdAt);
            }
        }
        return user;
    }

    public List<User> selectAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String userType = rs.getString("user_type");
                Timestamp createdAt = rs.getTimestamp("created_at");
                users.add(new User(id, username, password, email, userType, createdAt));
            }
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            statement.setString(3, hashedPassword);
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public User authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    boolean passwordMatches = false;

                    try {
                        if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
                            // The password is hashed with BCrypt
                            passwordMatches = BCrypt.checkpw(password, storedPassword);
                        } else {
                            // Legacy password (not hashed)
                            passwordMatches = password.equals(storedPassword);
                            if (passwordMatches) {
                                // Upgrade the password to BCrypt
                                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                                updateUser(new User(rs.getInt("id"), rs.getString("username"), hashedPassword, rs.getString("email"), rs.getString("user_type"), rs.getTimestamp("created_at")));
                                storedPassword = hashedPassword;
                            }
                        }

                        if (passwordMatches) {
                            return new User(
                                    rs.getInt("id"),
                                    rs.getString("username"),
                                    storedPassword,
                                    rs.getString("email"),
                                    rs.getString("user_type"),
                                    rs.getTimestamp("created_at")
                            );
                        }
                    } catch (IllegalArgumentException e) {
                        // Log the error for debugging
                        System.err.println("Error checking password: " + e.getMessage());
                        // Consider logging the username (but not the password) for debugging
                        System.err.println("Username: " + username);
                        // You might want to throw a custom exception here instead of returning null
                    }
                }
            }
        }
        return null;
    }

}