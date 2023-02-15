package com.example.augustino.DS;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Student extends User implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String card;
    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Projects> myProjects;

    public Student(String login, String pswd, LocalDate dateCreated, LocalDate dateModified, String name, String surname, String email, String card) {
        super(login, pswd, dateCreated, dateModified);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.card = card;
    }

    public Student(String name, String surname, String email, String card){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.card = card;
    }

    public Student(String login, String pswd, String name, String surname, String email, String card) {
        super(login, pswd);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.card = card;
    }

    public Student() {

    }
    public List<Projects> getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(List<Projects> myProjects) {
        this.myProjects = myProjects;
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

    public int getCard() {
        return Integer.parseInt(card);
    }

    public void setCard(String card) {
        this.card = card;
    }

}
