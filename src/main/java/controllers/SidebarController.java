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
import org.example.App;

import org.tinylog.Logger;


public class SidebarController {

    @FXML
    private BorderPane window;

    @FXML
    private ImageView btnExit;

    @FXML
    private Button btnGondozok;

    @FXML
    private Button btnGondozotak;

    @FXML
    private Button btnGondozasok;

    @FXML
    private Button btnStatisztikak;

    Stage stage = null;


    @FXML
    void handleClose(MouseEvent event) {

        if(event.getSource() == btnExit){
            System.exit(0);
        }
    }

    @FXML
    void handleClicks(MouseEvent event) throws IOException {
        if(event.getSource() == btnGondozok){
            loadFXML("gondozok");
        }
        else if(event.getSource() == btnGondozotak){
            loadFXML("gondozotak");
        }
        else if(event.getSource() == btnGondozasok){
            loadFXML("gondozasok");
        }
        else if(event.getSource() == btnStatisztikak){
            loadFXML("statisztikak");
        }
    }

    private void loadFXML(String Tab){
        AnchorPane root = null;
        try{
            root = FXMLLoader.load(App.class.getResource(Tab+".fxml"));
            Logger.info("FXML loaded.");
        } catch (IOException ex ) {
            Logger.error("FXML loading failed.");
        }
        window.setCenter(root);
    }


    public void handleFullscreen(MouseEvent event) {
        stage = (Stage) window.getScene().getWindow();
        if(stage.isMaximized()) {
            stage.setMaximized(false);
        }
        else{
            stage.setMaximized(true);
        }
    }
}


