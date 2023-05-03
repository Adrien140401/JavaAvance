package com.example.javaavance.model;

import org.json.JSONObject;

import java.util.List;

public class Client {

    // Attribut name de la Class Client
    public String name;

    // Constructeur
    public Client(String name) {
        this.name = name;
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
