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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.*;

import java.io.FileWriter;
import java.io.IOException;

public class HomeController {

    @FXML
    private TextField txtClientName;
    @FXML
    private Button btnNavigateTable;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String path = "./json/client.json";

    Client client = new Client("Adrien");

    JSONArray list = new JSONArray();

    public void onPressedValidate() throws IOException {

        client.name = txtClientName.getText();
        list.put(new Client(client.name).toJSON());

        try(FileWriter file = new FileWriter(path)) {
            file.write(list.toString());
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(list);
        //List<Client> clients = List.of(client).stream()
    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/javaavance/view/table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
