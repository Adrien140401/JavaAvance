package com.example.javaavance.controller;

import com.example.javaavance.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.*;

import java.io.FileWriter;
import java.io.IOException;

public class HomeController {

    @FXML
    private TextField txtClientName;
    @FXML
    private Button btnNavigateTable;
    @FXML
    private Label lblName;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String path = "./json/client.json";

    Client client = new Client("", 0);

    JSONArray list = new JSONArray();

    public void switchSceneToTableController(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/javaavance/view/table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onPressedValidate() throws IOException {

        client.name = txtClientName.getText();
        list.put(new Client(client.name, client.assigne).toJSON());

        try(FileWriter file = new FileWriter(path)) {
            file.write(list.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lblName.setText(client.name + " a été ajouté à la liste des clients");

        //List<Client> clients = List.of(client).stream()
    }

}
