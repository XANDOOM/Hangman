package com.example.demo5;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WinnerController {

    public void onRestartButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        HelloApplication.game(stage);
    }
    public void onExitButton(ActionEvent event) {
        Platform.exit();
    }
}
