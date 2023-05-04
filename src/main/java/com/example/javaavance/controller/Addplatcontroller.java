package com.example.javaavance.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Addplatcontroller {

    @FXML
    private Button saveButton;

    @FXML
    private TextField platField;

    @FXML
    private TextField prixField;

    @FXML
    private TextField stockField;

    @FXML
    private TextField descField;

    public void savePlat(ActionEvent event) throws JSONException, IOException {
        // Récupérer les données des champs
        String plat = platField.getText();
        int prix = Integer.parseInt(prixField.getText());
        int stock = Integer.parseInt(stockField.getText());
        String desc = descField.getText();

        // Charger le fichier JSON existant
        String datajson = new String(Files.readAllBytes(Paths.get("./json/plat.json")));
        JSONArray jsonArray = new JSONArray(datajson);

        // Créer un nouvel objet JSON avec les données des champs
        JSONObject platObject = new JSONObject();
        platObject.put("plat", plat);
        platObject.put("prix", prix);
        platObject.put("stock", stock);
        platObject.put("description", desc);

        // Ajouter le nouvel objet JSON au tableau JSON existant
        jsonArray.put(platObject);
        System.out.println(platObject);

        // Enregistrer les modifications dans le fichier JSON
        Files.write(Paths.get("./json/plat.json"), jsonArray.toString().getBytes());
    }

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void comeback(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/javaavance/vue/hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


}
