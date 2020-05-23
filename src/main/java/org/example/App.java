package org.example;

import database.EmfGetter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void start(Stage stage) throws IOException {

        BorderPane root = FXMLLoader.load(getClass().getResource("sidebar.fxml"));
        FXMLLoader listLoader = new FXMLLoader(getClass().getResource("statistics.fxml"));
        root.setCenter(listLoader.load());

        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);

            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("icons/AppIcon.png"));
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            EmfGetter.closeEmf();
        });


    }

}
