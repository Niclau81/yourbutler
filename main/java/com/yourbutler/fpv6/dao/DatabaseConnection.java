package com.yourbutler.fpv6.dao;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static BasicDataSource dataSource = null;

    static {
        try {
            System.out.println("Initializing DatabaseConnection...");
            Properties props = new Properties();
            InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");

            if (inputStream != null) {
                props.load(inputStream);
                System.out.println("db.properties file loaded successfully");

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                System.out.println("Database URL: " + url);
                System.out.println("Database User: " + user);
                // Don't print the password for security reasons

                if (url == null || user == null || password == null) {
                    throw new IllegalStateException("db.properties file is missing required properties");
                }

                dataSource = new BasicDataSource();
                dataSource.setUrl(url);
                dataSource.setUsername(user);
                dataSource.setPassword(password);

                // Configure the connection pool
                dataSource.setMinIdle(5);
                dataSource.setMaxIdle(10);
                dataSource.setMaxOpenPreparedStatements(100);

                System.out.println("Database connection pool initialized successfully");
            } else {
                throw new IllegalStateException("db.properties file not found in the classpath");
            }
        } catch (IOException e) {
            System.err.println("Error loading db.properties file: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        } catch (Exception e) {
            System.err.println("Error initializing database connection pool: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Database connection pool has not been initialized");
        }
        return dataSource.getConnection();
    }
}
