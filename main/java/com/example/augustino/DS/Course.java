package com.example.augustino.DS;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String descCourse;
    private Date dateCreated;
    @ManyToMany
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> respLearners = new ArrayList();
    @OneToMany(mappedBy = "nCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Projects> courseProjects = new ArrayList();

    public Course(int id, String title, String descCourse, Date dateCreated, List<User> respLearners, List<Projects> courseProjects) {
        this.id = id;
        this.title = title;
        this.descCourse = descCourse;
        this.dateCreated = dateCreated;
        this.respLearners = respLearners;
        this.courseProjects= courseProjects;

    }
    public Course( String title, String descCourse, Date dateCreated) {
        this.title = title;
        this.descCourse = descCourse;
        this.dateCreated = dateCreated;
        this.courseProjects = new ArrayList<>();
        this.respLearners = new ArrayList<>();
    }

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return descCourse;
    }

    public void setDesc(String desc) {
        this.descCourse = desc;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Projects> getCourseProjects() {
        return courseProjects;
    }

    public void setCourseProjects(List<Projects> courseProjects) {
        this.courseProjects = courseProjects;
    }

    public List<User> getRespLearners() {
        return respLearners;
    }

    public void setRespLearners(List<User> respLearners) {
        this.respLearners = respLearners;
    }

}
