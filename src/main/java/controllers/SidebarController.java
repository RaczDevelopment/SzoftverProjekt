package controllers;

import java.io.IOException;


import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.Main;

import org.tinylog.Logger;


public class SidebarController {

    @FXML
    private BorderPane window;

    @FXML
    private ImageView btnExit;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnElders;

    @FXML
    private Button btnCaretake;

    @FXML
    private Button btnStatistics;

    Stage stage = null;


    @FXML
    void handleClose(MouseEvent event) {

        if(event.getSource() == btnExit){
            System.exit(0);
        }
    }

    @FXML
    void handleClicks(MouseEvent event) {
        if(event.getSource() == btnEmployee){
            loadFXML("employee");
        }
        else if(event.getSource() == btnElders){
            loadFXML("elders");
        }
        else if(event.getSource() == btnCaretake){
            loadFXML("caretake");
        }
        else if(event.getSource() == btnStatistics){
            loadFXML("statistics");
        }
    }

    private void loadFXML(String Tab){
        AnchorPane root = null;
        try{
            root = FXMLLoader.load(Main.class.getResource(Tab+".fxml"));
            Logger.info("FXML loaded.");
        } catch (IOException ex ) {
            Logger.error("FXML loading failed.");
        }
        window.setCenter(root);
    }


    public void handleFullscreen() {
        stage = (Stage) window.getScene().getWindow();
        if(stage.isMaximized()) {
            stage.setMaximized(false);
        }
        else{
            stage.setMaximized(true);
        }
    }
}


