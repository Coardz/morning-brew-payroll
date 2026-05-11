package com.morningbrew.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertHelper {

    public static void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showValidationError(String content) {
        showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid Input or Empty Input", content);
    }
    
    public static void showSystemError(String content) {
        showAlert(Alert.AlertType.ERROR, "System Error", "Something went wrong", content);
    }

    public static boolean showConfirmation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
    
        return alert.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    public static void showLoginSuccess(String username) {
        showAlert(Alert.AlertType.INFORMATION, "Login Successful", 
        "Welcome back!", "Successfully logged in as: " + username);
}

    public static void showLoginFailed() {
        showAlert(Alert.AlertType.ERROR, "Login Failed", 
        "Invalid Credentials", "The username or password you entered is incorrect.");
}
}