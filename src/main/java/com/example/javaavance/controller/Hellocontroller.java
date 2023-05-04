package com.example.javaavance.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EventObject;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.*;

public class Hellocontroller {

    @FXML
    private Label platsListLabel;

    @FXML
    private Label platslistprix;

    @FXML
    private Label platsliststock;

    @FXML
    private Label platslistdesc;

    @FXML
    private Button createPlatButton;

    public void initialize() {
        String datajson = "[]";
        try {
            datajson = new String(Files.readAllBytes(Paths.get("./json/plat.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONArray myArray = new JSONArray(datajson);
        List<String> plats = new ArrayList<>();
        List<String> prix1 = new ArrayList<>();
        List<String> stocks = new ArrayList<>();
        List<String> descs = new ArrayList<>();
        myArray.forEach(object -> {
            JSONObject myjsonobject = (JSONObject) object;
            int stock = myjsonobject.getInt("stock");
            if (stock > 0) {
                plats.add(myjsonobject.getString("plat"));
                prix1.add(String.valueOf(myjsonobject.getInt("prix")));
                stocks.add(String.valueOf(stock));
                descs.add(myjsonobject.getString("description"));
            } else {
                plats.add(myjsonobject.getString("plat") + " (indisponible)");
            }
        });

        String platNames = plats.stream()
                .reduce("", (acc, plat) -> acc + "\n" + plat);
        platsListLabel.setText(platNames);

        int prixPlat = Integer.parseInt(prix1.stream()
                .reduce("", (acc, prix) -> acc + "\n" + prix)
                .replaceAll("[^\\d]", ""));
        platslistprix.setText(prixPlat + " euros\n");

        String platdesc = descs.stream()
                .reduce("", (acc, desc) -> acc + "\n" + "\n" + desc);
        platslistdesc.setText(platdesc);

        int platstock = Integer.parseInt(stocks.stream()
                .reduce("", (acc, stock) -> acc + "\n" + stock)
                .replaceAll("[^\\d]", ""));
        platsliststock.setText(platstock + "\n");
    }
}




