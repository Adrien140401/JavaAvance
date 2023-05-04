package com.example.javaavance.controller;

import java.net.URL;
import java.util.*;

import com.example.javaavance.model.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TableController{
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private ListView lvListTable;
    @FXML
    private Label lblAddTable;
    @FXML
    private TextField txtNumTable;
    @FXML
    private TextField txtTailleTable;
    @FXML
    private TextField txtEmplacementTable;

    String path = "./json/table.json";

    Table table = new Table(0, 0, "", true);

    JSONArray list = new JSONArray();

    String dataJson = "[]";

    public void switchSceneToClientController(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/com/example/javaavance/view/user-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createTable(ActionEvent actionEvent) {
        table.setNumeroTable(Integer.parseInt(txtNumTable.getText()));
        table.setPlaces(Integer.parseInt(txtTailleTable.getText()));
        table.setEmplacement(txtEmplacementTable.getText());

        list.put(new Table(table.getNumeroTable(), table.getPlaces(), table.getEmplacement(), table.isDisponibilite()).toJSON());

        try(FileWriter file = new FileWriter(path)) {
            file.write(list.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(list.toString());

        lblAddTable.setText("La table " + table.getNumeroTable() + " a été ajouté à la liste des tables");

        try {
            dataJson = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONArray json = new JSONArray(dataJson);
        List<Table> tableList = new ArrayList<>();

        for(int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            Table table = new Table(obj.getInt("numeroTable"), obj.getInt("places"), obj.getString("emplacement"), obj.getBoolean("disponibilite"));
            tableList.add(table);
        }

        ObservableList<Table> observableTableList = FXCollections.observableArrayList(tableList);

        lvListTable.setItems(observableTableList);

        // Créer un bouton "Supprimer" pour chaque élément de la liste
        Callback<ListView<Table>, ListCell<Table>> cellFactory = new Callback<>() {
            public ListCell<Table> call(ListView<Table> lvListTable) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(Table table, boolean empty) {
                        super.updateItem(table, empty);
                        if (!empty) {
                            Button deleteBtn = new Button("Supprimer");
                            deleteBtn.setOnAction(event -> {
                                // Récupérer l'élément sélectionné
                                Table selectedTable = lvListTable.getSelectionModel().getSelectedItem();
                                // Supprimer l'élément de la liste
                                lvListTable.getItems().remove(selectedTable);
                                // Mettre à jour le fichier JSON
                                JSONArray json = new JSONArray(lvListTable.getItems());
                                try (FileWriter file = new FileWriter(path)) {
                                    file.write(json.toString());
                                    file.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            setGraphic(deleteBtn);
                            setText(table.toString());
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
            }
        };
        // Appliquer la factory de cellule à la ListView
        lvListTable.setCellFactory(cellFactory);
    }
}
