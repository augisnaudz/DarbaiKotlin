package com.example.augustino.fxcontrol;

public class ModelTable {
    int userId;
    String name;
    String surname;
    String email;
    String dateCreated;

    public ModelTable(int userId, String name, String surname, String email, String dateCreated) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateCreated = dateCreated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCrated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
