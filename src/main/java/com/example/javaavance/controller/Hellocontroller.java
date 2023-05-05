package com.example.javaavance.controller;

// Importation des classes nécessaires
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

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.*;

// Définition de la classe Hellocontroller
public class Hellocontroller {

    // Injection de dépendances des éléments définis dans le fichier FXML
    @FXML
    private Label platsListLabel;

    @FXML
    private Label platslistprix;

    @FXML
    private Label platsliststock;

    @FXML
    private Label platslistdesc;

    // Méthode d'initialisation appelée lorsque le contrôleur est chargé
    public void initialize() {
        // Lecture du fichier JSON contenant les données sur les plats
        String datajson = "[]";
        try {
            datajson = new String(Files.readAllBytes(Paths.get("./json/plat.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Transformation du contenu JSON en tableau d'objets JSON
        JSONArray myArray = new JSONArray(datajson);
        // Création de listes pour stocker les noms de plats, les prix, les stocks et les descriptions
        List<String> plats = new ArrayList<>();
        List<String> prix1 = new ArrayList<>();
        List<String> stocks = new ArrayList<>();
        List<String> descs = new ArrayList<>();
        // Parcours de chaque objet JSON du tableau
        myArray.forEach(object -> {
            // Conversion de l'objet en objet JSON
            JSONObject myjsonobject = (JSONObject) object;
            // Récupération de la valeur de stock de chaque plat
            int stock = myjsonobject.getInt("stock");
            // Si le stock est supérieur à zéro, on ajoute le plat aux listes correspondantes, sinon on l'ajoute avec la mention "indisponible"
            if (stock > 0) {
                plats.add(myjsonobject.getString("plat"));
                prix1.add(String.valueOf(myjsonobject.getInt("prix")));
                stocks.add(String.valueOf(stock));
                descs.add(myjsonobject.getString("description"));
            } else {
                plats.add(myjsonobject.getString("plat") + " (indisponible)");
            }
        });

        // Concaténation des noms de plats pour les afficher dans l'interface graphique
        String platNames = plats.stream()
                .reduce("", (acc, plat) -> acc + "\n" + plat);
        platsListLabel.setText(platNames);

        // Calcul du prix total des plats sélectionnés pour les afficher dans l'interface graphique
        int prixPlat = Integer.parseInt(prix1.stream()
                .reduce("", (acc, prix) -> acc + "\n" + prix)
                .replaceAll("[^\\d]", ""));
        platslistprix.setText(prixPlat + " euros\n");

        // Concaténation des descriptions de plats pour les afficher dans l'interface graphique
        String platdesc = descs.stream()
                .reduce("", (acc, desc) -> acc + "\n" + "\n" + desc);
        platslistdesc.setText(platdesc);

        // Concaténation des stocks de plats pour les afficher dans l'interface graphique
        int platstock = Integer.parseInt(stocks.stream()
                .reduce("", (acc, stock) -> acc + "\n" + stock)
                .replaceAll("[^\\d]", ""));
        platsliststock.setText(platstock + "\n");
    }

    // Définition des variables pour la scène
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Cette méthode permet de changer la scène affichée
     *
     * @param event événement de type ActionEvent
     * @throws IOException si le chargement de la nouvelle scène échoue
     */
    public void switchScene(ActionEvent event) throws IOException {
        // On charge la nouvelle scène depuis le fichier FXML correspondant
        root = FXMLLoader.load(getClass().getResource("/com/example/javaavance/view/createplat.fxml"));

        // On récupère la fenêtre actuelle depuis l'événement
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // On crée une nouvelle scène avec le contenu chargé depuis le fichier FXML
        scene = new Scene(root);

        // On affecte la nouvelle scène à la fenêtre et on l'affiche
        stage.setScene(scene);
        stage.show();
    }
}




