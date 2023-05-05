package com.example.javaavance.model;

import org.json.JSONObject;

import java.util.List;

public class Client {

    // Attribut name de la Class Client
    public String name;
    public int assigne;

    public int getAssigne() {
        return assigne;
    }

    public void setAssigne(int assigne) {
        this.assigne = assigne;
    }

    // Constructeur
    public Client(String name, int assigne) {
        this.name = name;
        this.assigne = assigne;
    }

    // Getter de l'attribut String name
    public String getName() {
        return name;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
