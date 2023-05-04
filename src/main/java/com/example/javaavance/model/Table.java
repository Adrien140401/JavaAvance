package com.example.javaavance.model;

import org.json.JSONObject;

public class Table{

    public Table(int numeroTable, int places, String emplacement, boolean disponibilite) {
        this.numeroTable = numeroTable;
        this.places = places;
        this.emplacement = emplacement;
        this.disponibilite = disponibilite;
    }

    private int numeroTable;
    private int places;
    private String emplacement;
    private boolean disponibilite;

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

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("places", places);
        json.put("emplacement", emplacement);
        json.put("disponibilite", disponibilite);
        json.put("numeroTable", numeroTable);
        return json;
    }
    @Override
    public String toString() {
        return "Table " + numeroTable + " (" + places + " places, " + emplacement + ")";
    }
}
