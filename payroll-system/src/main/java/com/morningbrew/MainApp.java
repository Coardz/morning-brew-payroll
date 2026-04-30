package com.morningbrew;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        
        scene = new Scene(loadFXML("login"), 750, 560);
        
        stage.setTitle("Morning Brew | Payroll System");
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void applyStyles(String fxml) {
        scene.getStylesheets().clear();

        scene.getStylesheets().add(MainApp.class.getResource("style/global.css").toExternalForm());

        String specificStyle = "style/" + fxml + ".css";

        if (MainApp.class.getResource(specificStyle) != null) {
            scene.getStylesheets().add(MainApp.class.getResource(specificStyle).toExternalForm());
        }
    }


    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
        
        applyStyles(fxml);

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
       // createNewUser("bisakols", "saging", "Administrator");
        launch();
    }
}