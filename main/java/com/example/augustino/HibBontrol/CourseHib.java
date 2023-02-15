package com.example.augustino.HibBontrol;

import com.example.augustino.DS.Course;
import com.example.augustino.DS.Projects;
import com.example.augustino.DS.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CourseHib {
    private static EntityManagerFactory emf = null;

    public CourseHib(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private static EntityManager getEntityManager(){return emf.createEntityManager();}

    public void createCourse(Course course) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public static Course getCourseById(int projectId) {
        EntityManager em;
        Course course = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            course = em.find(Course.class, projectId);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("neveikia idk XD");
        }
        return course;
    }

    public List<Course> getCourseByUserId(int id){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> q = cb.createQuery(Course.class);

        Root<Course> root = q.from(Course.class);
        CriteriaBuilder.In<Integer> inClause = cb.in(root.get("id"));
        User user = em.getReference(User.class, id);
        for (Course p : user.getMyCourse()) {
            inClause.value(p.getId());
        }
        q.select(root).where(inClause);
        Query query;
        try {
            query = em.createQuery(q);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> getUserByCourse(int id){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);

        Root<User> root = q.from(User.class);
        CriteriaBuilder.In<Integer> inClause = cb.in(root.get("id"));
        Course course= em.getReference(Course.class, id);
        for (User p : course.getRespLearners()) {
            inClause.value(p.getId());
        }
        q.select(root).where(inClause);
        Query query;
        try {
            query = em.createQuery(q);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void editCourse(Course course) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Course> getAllCourses() {
        return getAllCourse(true, -1, -1);
    }

    public List<Course> getAllCourse(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Course.class));
            Query q = em.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public static void removeCourse(int id) {
        ProjectHib projectHib = new ProjectHib(emf);
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Course course = null;
            try {
                course = em.getReference(Course.class, id);

                for (Projects t : course.getCourseProjects()) {
                    projectHib.remove(t.getId());
                }

                course.getCourseProjects().clear();
                em.merge(course);

                for (User u : course.getRespLearners()) {
                    u.getMyCourse().remove(course);
                    em.merge(u);
                }

            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }


            em.remove(course);
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
