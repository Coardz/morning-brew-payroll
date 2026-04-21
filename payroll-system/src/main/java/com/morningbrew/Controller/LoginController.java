package com.morningbrew.Controller;

import java.io.IOException;

import com.morningbrew.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {
    
    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {
        System.out.println("Button clicked!");
    }

    @FXML
    private void handleSwitchScene(ActionEvent event){
        try {
            MainApp.setRoot("dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
