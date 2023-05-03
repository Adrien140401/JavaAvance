package com.example.javaavance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}