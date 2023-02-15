package com.example.augustino.DS;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Projects implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String projectName;
    private String projectDesc;
    private LocalDate dateCreated;
    @OneToMany(mappedBy = "parentCourse", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Projects> subProjects;
    @ManyToOne
    private Projects parentCourse;//
    @ManyToOne
    private Student student;//
    @ManyToOne
    private User creator;//
    @ManyToOne
    private Course nCourse;//

    public Projects(int id, String projectName, String projectDesc, LocalDate dateCreated, List<Projects> subProjects, Projects parentCourse, Student student, User creator) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.dateCreated = dateCreated;
        this.subProjects = subProjects;
        this.parentCourse = parentCourse;
        this.student = student;
        this.creator = creator;
    }

    public Projects(String projectName, String projectDesc, Student student, User creator) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.student = student;
        this.creator = creator;
    }

    public Projects(String projectName, String projectDesc, LocalDate dateCreated, Student student, User creator, Course nCourse) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.dateCreated = LocalDate.now();
        this.student = student;
        this.creator = creator;
        this.nCourse = nCourse;
    }

    public Projects(int id, String projectName, String projectDesc, LocalDate dateCreated, Projects parentCourse, Student student, User creator) {
        this.id = id;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.dateCreated = dateCreated;
        this.parentCourse = parentCourse;
        this.student = student;
        this.creator = creator;
    }

    public Projects() {
    }

    public Projects(String projectName, String projectDesc, Projects parentCourse, Student student, User creator) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.parentCourse = parentCourse;
        this.student = student;
        this.creator = creator;
    }

    public Projects(String projectName, String projectDesc,  Student student, User creator, Course nCourse) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.student = student;
        this.creator = creator;
        this.nCourse = nCourse;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Projects> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<Projects> subProjects) {
        this.subProjects = subProjects;
    }

    public Projects getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(Projects parentCourse) {
        this.parentCourse = parentCourse;
    }

    public Course getCourse() {
        return nCourse;
    }

    public void setCourse(Course nCourse) {
        this.nCourse = nCourse;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
