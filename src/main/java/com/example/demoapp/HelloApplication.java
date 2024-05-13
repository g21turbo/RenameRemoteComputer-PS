package com.example.demoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("renameRemoteComputer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 195);
        stage.setTitle("Rename Remote Computer");
        stage.setScene(scene);
        // test

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}