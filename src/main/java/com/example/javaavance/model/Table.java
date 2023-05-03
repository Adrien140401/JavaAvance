package com.example.javaavance.model;

import org.json.JSONObject;

public class Table {

    private int numeroTable;
    private int places;
    private String emplacement;
    private boolean disponibilite;
    private String assignation;

    public String getAssignation() {
        return assignation;
    }

    public void setAssignation(String assignation) {
        this.assignation = assignation;
    }

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

    public Table(int places, String emplacement, boolean disponibilite, int numeroTable, String assignation) {
        this.places = places;
        this.emplacement = emplacement;
        this.disponibilite = disponibilite;
        this.numeroTable = numeroTable;
        this.assignation = assignation;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("places", places);
        json.put("emplacement", emplacement);
        json.put("disponibilite", disponibilite);
        json.put("numeroTable", numeroTable);
        return json;
    }
}
