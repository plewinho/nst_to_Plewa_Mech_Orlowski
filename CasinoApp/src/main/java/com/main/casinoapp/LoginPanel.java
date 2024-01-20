package com.main.casinoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;

public class LoginPanel extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    Connection con = null;

    @Override
    public void start(Stage stage) throws Exception {
        con = db.mycon();
        Parent root = FXMLLoader.load(getClass().getResource("loginpanel-view.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);

        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
           stage.setX(event.getScreenX() - xOffset);
           stage.setY(event.getScreenY() - yOffset);
        });

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}