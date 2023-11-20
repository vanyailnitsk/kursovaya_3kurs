package com.example.java_lab4.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Expense {
    private int id;
    private int userId;
    private int amount;
    private String source;
    private int categoryId;
    private String category;
    private Timestamp timestamp;
    private String dateFormatted;

    public Expense(int id, int user_id, int amount, String source, int categoryId, Timestamp timestamp) {
        this.id = id;
        this.userId = user_id;
        this.amount = amount;
        this.source = source;
        this.categoryId = categoryId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", source='" + source + '\'' +
                ", categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", timestamp=" + timestamp +
                ", dateFormatted='" + dateFormatted + '\'' +
                '}';
    }
}
