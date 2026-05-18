package com.morningbrew;

import java.io.IOException;

<<<<<<< HEAD
import com.morningbrew.Utils.SceneHelper;

=======
>>>>>>> e3a8b41513683ada5190423496f6758f85fe95ed
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD
        SceneHelper.setStage(stage);
        stage.setTitle("Morning Brew");
        stage.initStyle(StageStyle.UNDECORATED);
        SceneHelper.switchScene("login"); // XXX CHANGE THE STARTING UI HERE WHEN DEBUGGING/TESTING
        stage.show();
    }

=======
        primaryStage = stage;
        
        scene = new Scene(loadFXML("login"), 750, 560);
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
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

>>>>>>> e3a8b41513683ada5190423496f6758f85fe95ed
    public static void main(String[] args) {
        launch();
    }
}