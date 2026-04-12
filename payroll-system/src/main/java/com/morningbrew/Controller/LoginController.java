package com.morningbrew.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController {
    
    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {
        System.out.println("Button clicked!");
    }
}
