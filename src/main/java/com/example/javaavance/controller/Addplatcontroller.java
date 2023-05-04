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
import models.Plat;
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

    public class PlatManager {
        public void ajouterPlat(String image, String nomPlat, String description, int prix, int stock) throws Exception {
            // Charger le fichier JSON existant
            String datajson = new String(Files.readAllBytes(Paths.get("./json/plat.json")));
            JSONArray jsonArray = new JSONArray(datajson);

            // Créer un nouvel objet Plat avec les données des champs
            Plat plat = new Plat(image, nomPlat, description, prix, stock);


            // Convertir l'objet Plat en un objet JSON
            JSONObject platObject = new JSONObject();
            platObject.put("image", plat.getImage());
            platObject.put("plat", plat.getPlat());
            platObject.put("description", plat.getDescription());
            platObject.put("prix", plat.getPrix());
            platObject.put("stock", plat.getStock());

            // Ajouter le nouvel objet JSON au tableau JSON existant
            jsonArray.put(platObject);

            // Enregistrer les modifications dans le fichier JSON
            Files.write(Paths.get("./json/plat.json"), jsonArray.toString().getBytes());
        }
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

    @FXML
    private void savePlat(ActionEvent event) throws Exception {
        String nomPlat = platField.getText();
        String description = descField.getText();
        int prix = Integer.parseInt(prixField.getText());
        int stock = Integer.parseInt(stockField.getText());

        PlatManager platManager = new PlatManager();
        platManager.ajouterPlat("https://example.com/images/nouveau-plat.jpg", nomPlat, description, prix, stock);

        // Retourner à la vue précédente
        comeback(event);
    }


}