package com.example.augustino.DS;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String pswd;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    @ManyToMany(mappedBy = "respLearners", cascade = {CascadeType.PERSIST, CascadeType.MERGE})//
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> myCourse;//
    @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Projects> projectsCreated;

    public User(String login, String pswd, LocalDate dateCreated, LocalDate dateModified) {
        this.login = login;
        this.pswd = pswd;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public User( String login, String pswd) {
        this.login = login;
        this.pswd = pswd;
        this.dateCreated = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public User(int id, String login, String pswd, LocalDate dateCreated, LocalDate dateModified, List<Course> myCourse, List<Projects> projectsCreated) {
        this.id = id;
        this.login = login;
        this.pswd = pswd;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.myCourse = myCourse;
        this.projectsCreated = projectsCreated;
    }

    public User() {
    }

    public List<Projects> getProjectsCreated() {
        return projectsCreated;
    }

    public void setProjectsCreated(List<Projects> projectsCreated) {
        this.projectsCreated = projectsCreated;
    }

    public List<Course> getMyCourse() {
        return myCourse;
    }

    public void setMyCourse(List<Course> myCourse) {
        this.myCourse = myCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }


}
