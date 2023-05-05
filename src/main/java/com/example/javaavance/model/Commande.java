package com.example.javaavance.model;

import org.json.JSONObject;

import java.time.LocalDate;

public class Commande {

    private int number;
    //private List<> plat;
    private int table;
    private LocalDate date;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Commande(int number, int table, boolean status) {
        this.number = number;
        this.table = table;
        this.date = LocalDate.now();
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("number", number);
        json.put("table", table);
        json.put("date", date);
        json.put("status", status);
        return json;
    }
}
