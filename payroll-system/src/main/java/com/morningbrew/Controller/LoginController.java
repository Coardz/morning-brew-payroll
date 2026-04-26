package com.morningbrew.Controller;

import java.io.IOException;
import java.sql.*;
import com.morningbrew.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML 
    private TextField usernameField;

    @FXML 
    private PasswordField passwordField;

    private static final String DB_URL = "jdbc:sqlite:morning-brew.db";

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password)) {
            System.out.println("Login successful!");
            switchToDashboard();
        } else {
            showError("Invalid Credentials", "The username or password you entered is incorrect.");
        }
    }

    private boolean authenticate(String user, String pass) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a match is found
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void switchToDashboard() {
        try {
            MainApp.setRoot("dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}