package com.morningbrew.Controller;

import com.morningbrew.Utils.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {
    @FXML private Button btnHome, btnEmployeeManager;


    @FXML
    private void switchToEmployeeManager() {
        SceneHelper.switchScene("employeeManager");
    }

    @FXML
    private void switchToDashboard() {
        SceneHelper.switchScene("dashboard");
    }
}
