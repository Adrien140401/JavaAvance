package com.example.javaavance.controller;

import java.util.Arrays;
import com.example.javaavance.model.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TableController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private Label lblListeTable;
    @FXML
    private Label lblAddTable;
    @FXML
    private TextField txtNumTable;
    @FXML
    private TextField txtTailleTable;
    @FXML
    private TextField txtEmplacementTable;

    String path = "./json/table.json";

    Table table = new Table(4, "Terrasse", true, 1, "Aucune");

    JSONArray list = new JSONArray();

    public void switchSceneToClientController(ActionEvent event) throws IOException {
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

        list.put(new Table(table.getPlaces(), table.getEmplacement(), table.isDisponibilite(), table.getNumeroTable(), table.getAssignation()).toJSON());

        try(FileWriter file = new FileWriter(path)) {
            file.write(list.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(list.toString());

        lblAddTable.setText("La table " + table.getNumeroTable() + " a été ajouté à la liste des tables");

        // Show the list of table.getNumberTable() in the lblListeTable


        Arrays.stream(list.toString().split(","))
                .forEach(lblListeTable::setText);

    }

}
