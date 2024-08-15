package com.yourbutler.fpv6.service;

import com.yourbutler.fpv6.dao.UserDAO;
import com.yourbutler.fpv6.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) throws SQLException {
        userDAO.insertUser(user);
    }

    public User authenticateUser(String username, String password) throws SQLException {
        return userDAO.authenticateUser(username, password);
    }

    public User getUserById(int id) throws SQLException {
        return userDAO.selectUser(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.selectAllUsers();
    }

    public boolean updateUser(User user) throws SQLException {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int id) throws SQLException {
        return userDAO.deleteUser(id);
    }
}