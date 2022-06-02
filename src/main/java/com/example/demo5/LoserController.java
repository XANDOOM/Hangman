package com.example.demo5;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoserController {

    @FXML
    private Button revealButton;
    public void onRestartButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        HelloApplication.game(stage);
    }

    public void onExitButton(ActionEvent event) {
        Platform.exit();
    }


    public void onRevealButton(ActionEvent event) throws IOException {
        revealButton.setText(HelloController.show_ans());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ad.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Advertisement");
        stage.setScene(scene);
        stage.show();
    }
}
