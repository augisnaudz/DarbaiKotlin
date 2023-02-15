package com.example.augustino.DS;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Company extends User implements Serializable {
    private String groupName;
    private String groupRep;
    private String groupEmail;
    private String groupAdress;

    public Company(String login, String pswd, LocalDate dateCreated, LocalDate dateModified, String groupName, String groupRep, String groupEmail, String groupAdress) {
        super(login, pswd, dateCreated, dateModified);
        this.groupName = groupName;
        this.groupRep = groupRep;
        this.groupEmail = groupEmail;
        this.groupAdress = groupAdress;
    }

    public Company(String login, String pswd, String groupName, String groupRep, String groupEmail, String groupAdress) {
        super(login, pswd);
        this.groupName = groupName;
        this.groupRep = groupRep;
        this.groupEmail = groupEmail;
        this.groupAdress = groupAdress;
    }

    public Company(){

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupRep() {
        return groupRep;
    }

    public void setGroupRep(String groupRep) {
        this.groupRep = groupRep;
    }

    public String getGroupEmail() {
        return groupEmail;
    }

    public void setGroupEmail(String groupEmail) {
        this.groupEmail = groupEmail;
    }

    public String getGroupAdress() {
        return groupAdress;
    }

    public void setGroupAdress(String groupAdress) {
        this.groupAdress = groupAdress;
    }

}
