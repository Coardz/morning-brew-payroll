package com.morningbrew.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    public static void createNewUser(String user, String pass, String role) {
        String sql = "INSERT INTO users(username, password, role) VALUES(?,?,?)";
        
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:morningbrew.db");
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
