package com.example.javaavance.model;

import org.json.JSONObject;

public class Table{

    //Constructeur
    public Table(int numeroTable, int places, String emplacement, boolean disponibilite) {
        this.numeroTable = numeroTable;
        this.places = places;
        this.emplacement = emplacement;
        this.disponibilite = disponibilite;
    }

    // Attributes
    private int numeroTable;
    private int places;
    private String emplacement;
    private boolean disponibilite;

    // Getter & Setter
    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getNumeroTable() {
        return numeroTable;
    }

    public void setNumeroTable(int numeroTable) {
        this.numeroTable = numeroTable;
    }

    // Méthode toJSON() qui retourne un objet JSON
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("places", places);
        json.put("emplacement", emplacement);
        json.put("disponibilite", disponibilite);
        json.put("numeroTable", numeroTable);
        return json;
    }

    // Redéfinition de la méthode toString() pour afficher les valeurs de l'objet
    @Override
    public String toString() {
        return "Table " + numeroTable + " (" + places + " places, " + emplacement + ")";
    }
}
