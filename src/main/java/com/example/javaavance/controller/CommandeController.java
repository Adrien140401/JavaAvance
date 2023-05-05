package com.example.javaavance.controller;

import com.example.javaavance.model.Commande;
import com.example.javaavance.model.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandeController implements Initializable {
    @FXML
    private TextField txtNumTable;
    @FXML
    private TextField txtNumComm;
    @FXML
    private Label lbDateComm;
    @FXML
    private Label lblResult;
    @FXML
    private ListView lvListComm;
    Commande commande = new Commande(0, 0, false);
    JSONArray list = new JSONArray();
    String dataJson = "[]";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate date = commande.getDate();
        lbDateComm.setText(date.toString());
    }

    public void createComm(ActionEvent actionEvent) {
        commande.setNumber(Integer.parseInt(txtNumComm.getText()));
        commande.setTable(Integer.parseInt(txtNumTable.getText()));

        JSONArray json = new JSONArray(dataJson);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            if (obj.getInt("table") == commande.getTable()) {
                // Une commande existe déjà pour cette table, ne rien faire
                lblResult.setText("Une commande est déjà assignée à cette table.");
                return;
            }
        }

        list.put(new Commande(commande.getNumber(), commande.getTable(), commande.isStatus()).toJSON());

        try(FileWriter file = new FileWriter("./json/commande.json")) {
            file.write(list.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lblResult.setText("Commande : " + commande.getNumber() + " - Table :" + commande.getTable() + " Enregistrée");

        try {
            dataJson = new String(Files.readAllBytes(Paths.get("./json/commande.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        json = new JSONArray(dataJson);

        List<Commande> tableList = IntStream.range(0, json.length())
                .mapToObj(json::getJSONObject)
                .map(obj -> new Commande(obj.getInt("number"), obj.getInt("table"), obj.getBoolean("status")))
                .collect(Collectors.toList());

        ObservableList<Commande> observableTableList = FXCollections.observableArrayList(tableList);

        lvListComm.setItems(observableTableList);

        // Créer un bouton "Supprimer" pour chaque élément de la liste
        Callback<ListView<Commande>, ListCell<Commande>> cellFactory = new Callback<>() {

            public ListCell<Commande> call(ListView<Commande> lvListComm) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Commande commande, boolean empty) {
                        super.updateItem(commande, empty);
                        if (!empty) {
                            Button deleteBtn = new Button("Supprimer");
                            deleteBtn.setOnAction(event -> {
                                // Récupérer l'élément sélectionné
                                Commande selectedTable = lvListComm.getSelectionModel().getSelectedItem();
                                // Supprimer l'élément de la liste
                                lvListComm.getItems().remove(selectedTable);
                                // Mettre à jour le fichier JSON
                                JSONArray json = new JSONArray(lvListComm.getItems());
                                try (FileWriter file = new FileWriter("./json/commande.json")) {
                                    file.write(json.toString());
                                    file.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                lblResult.setText("La table " + commande.getNumber() + " a été supprimé de la liste des tables");
                            });
                            setGraphic(deleteBtn);
                            setText(commande.toString());
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
            }
        };
        // Appliquer la factory de cellule à la ListView
        lvListComm.setCellFactory(cellFactory);

    }

    // Changer le Status d'une commande
    public void changeStatus(ActionEvent actionEvent) {
        Commande selectedCommande = (Commande) lvListComm.getSelectionModel().getSelectedItem();
        if (selectedCommande == null) {
            lblResult.setText("Veuillez sélectionner une commande.");
            return;
        }
        selectedCommande.setStatus(!selectedCommande.isStatus());
        try (FileWriter file = new FileWriter("./json/commande.json")) {
            file.write(list.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lblResult.setText("Statut de la commande " + selectedCommande.getNumber() + " modifié.");
    }
}
