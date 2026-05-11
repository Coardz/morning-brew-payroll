package com.morningbrew.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {
    private static final String URL = "jdbc:sqlite:morning-brew.db";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    
    public static boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a record exists
            
        } catch (SQLException e) {
            System.err.println("Authentication error: " + e.getMessage());
            return false;
        }
    }

    // Account Creation
    public static void createNewUser(String user, String pass, String role) {
        String sql = "INSERT INTO users(username, password, role) VALUES(?,?,?)";
        
        try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            System.out.println("User " + user + " created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
}
    
}
