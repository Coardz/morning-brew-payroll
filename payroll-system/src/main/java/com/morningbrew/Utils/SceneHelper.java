package com.morningbrew.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneHelper {

    private static Stage primaryStage;

    // Call this once in MainApp's start() method
    public static void setStage(Stage stage) {
        primaryStage = stage;
    }
    
    public static void switchScene(String fxmlName) {
        try {
            String path = "/com/morningbrew/view/" + fxmlName + ".fxml";
            java.net.URL fxmlLocation = SceneHelper.class.getResource(path);

            if (fxmlLocation == null) {
                throw new IOException("Could not find FXML: " + path);
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            if (primaryStage.getScene() == null) {
                primaryStage.setScene(new Scene(root, 810, 560));
            } else {
                primaryStage.getScene().setRoot(root);
            }

            applyStyles(primaryStage.getScene(), fxmlName);

            primaryStage.sizeToScene();
            primaryStage.centerOnScreen();

        } catch (IOException e) {
            AlertHelper.showSystemError("Navigation Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void applyStyles(Scene scene, String fxmlName) {
        scene.getStylesheets().clear();
    
        String styleBase = "/com/morningbrew/style/";
    
        java.net.URL globalRes = SceneHelper.class.getResource(styleBase + "global.css");
        if (globalRes != null) {
            scene.getStylesheets().add(globalRes.toExternalForm());
        }

        java.net.URL specificRes = SceneHelper.class.getResource(styleBase + fxmlName + ".css");
        if (specificRes != null) {
            scene.getStylesheets().add(specificRes.toExternalForm());
        }
    }
}