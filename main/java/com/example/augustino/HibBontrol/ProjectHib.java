package com.example.augustino.HibBontrol;

import com.example.augustino.DS.Course;
import com.example.augustino.DS.Projects;
import com.example.augustino.DS.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PostRemove;

public class ProjectHib {
    private EntityManagerFactory emf = null;
    public ProjectHib(EntityManagerFactory emf) {this.emf = emf;}
    private EntityManager getEntityManager(){return emf.createEntityManager();}


    public void remove(int id) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Projects projects = null;
            try {
                projects = em.getReference(Projects.class, id);
                projects.getId();
            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }

            Course course = projects.getCourse();
            if (course != null) {
                course.getCourseProjects().remove(projects);
                em.merge(course);
            }

            for (Projects t : projects.getSubProjects()) {
                remove(t.getId());
            }

            projects.getSubProjects().clear();
            em.merge(projects);

            Student responsible = projects.getStudent();
            responsible.getMyProjects().remove(projects);
            em.merge(responsible);

            em.remove(projects);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Projects getProjectById(int id) {
        EntityManager em;
        Projects projects = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            projects = em.find(Projects.class, id);
            em.getTransaction().commit();
        } catch (Exception e){
            System.out.println("neveikia lol xd");
        }
        return projects;
    }

    public void editProject(Projects parent) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(parent);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
