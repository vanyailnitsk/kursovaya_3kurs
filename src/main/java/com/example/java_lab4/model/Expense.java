package com.example.java_lab4.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Expense {
    private int id;
    private int user_id;
    private int amount;
    private String source;
    private int category;
    private Timestamp timestamp;
    private String dateFormatted;

    public Expense(int id, int user_id, int amount, String source, int category, Timestamp timestamp) {
        this.id = id;
        this.user_id = user_id;
        this.amount = amount;
        this.source = source;
        this.category = category;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getDateFormatted() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(timestamp);
    }
}
