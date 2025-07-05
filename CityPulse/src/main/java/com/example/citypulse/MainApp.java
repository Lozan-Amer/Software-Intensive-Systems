package com.example.citypulse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/citypulse/welcome_screen.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("City Pulse");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene(); // מתאים לגודל המדויק של ה-FXML

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
