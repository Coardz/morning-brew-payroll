package com.morningbrew;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        
        scene = new Scene(loadFXML("login"), 645, 446);
        
        stage.setTitle("Morning Brew | Payroll System");
        
        stage.setScene(scene);
        stage.show();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        String path = "view/" + fxml + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(path));
        
        Parent root = fxmlLoader.load();
        
        if (root == null) {
            throw new IOException("FXML file not found at: " + path);
        }
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}