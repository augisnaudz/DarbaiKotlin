package com.example.augustino.DS;

import java.util.ArrayList;

public class CourseSys {
    private int id;
    private String version;
    private ArrayList<User> allUsers;
    private ArrayList<Projects> allProjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<Projects> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(ArrayList<Projects> allProjects) {
        this.allProjects = allProjects;
    }

    public CourseSys(int id, String version, ArrayList<User> allUsers, ArrayList<Projects> allProjects) {
        this.id = id;
        this.version = version;
        this.allUsers = allUsers;
        this.allProjects = allProjects;
    }
}
