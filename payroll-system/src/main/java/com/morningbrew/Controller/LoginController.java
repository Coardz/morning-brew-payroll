package com.morningbrew.Controller;

import com.morningbrew.Utils.AlertHelper;
import com.morningbrew.Utils.Database;
import com.morningbrew.Utils.SceneHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Form Incomplete", null, "Please fill in all fields.");
            return;
        }

        if (Database.authenticate(username, password)) {
            AlertHelper.showLoginSuccess(username);
            switchToDashboard();
        } else {
            AlertHelper.showLoginFailed();
        }
    }

    @FXML
    private void switchToDashboard() {
        SceneHelper.switchScene("dashboard");
    }
    @FXML
    private void closeApp() {
        System.exit(0);
    }


    //please fix this if possible HAHAHA my show password shit hAHAHHA
    @FXML
    private void showpassword() {
       if (passwordField.isVisible()) {


            passwordField.setVisible(false);
            passwordField.setManaged(false);

        } else {

            passwordField.setVisible(true);
            passwordField.setManaged(true);
        }
       
    }

    
     
    
}