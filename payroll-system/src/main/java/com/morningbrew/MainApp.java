package com.morningbrew;

import java.io.IOException;
import com.morningbrew.Utils.SceneHelper;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneHelper.setStage(stage);
        stage.setTitle("Morning Brew");
        SceneHelper.switchScene("login"); // XXX CHANGE THE STARTING UI HERE WHEN DEBUGGING/TESTING
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}