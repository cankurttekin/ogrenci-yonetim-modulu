package com.kurttekin.can.dao;

import com.kurttekin.can.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    // Hibernate session factory for database operations
    private final SessionFactory sessionFactory;

    // Constructor injection of session factory
    public StudentDAOImpl() {
        try {
            sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Hibernate SessionFactory", e);
        }
    }

    // Save or update student
    @Override
    public void saveStudent(Student student) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Error saving student", e);
        }
    }

    // Get student by ID
    @Override
    public Student getStudentById(Long id) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Error retrieving student with id: " + id, e);
        }
    }

    // Get all students
    @Override
    public List<Student> getAllStudents() {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student order by lastName", 
                                                       Student.class).getResultList();
            session.getTransaction().commit();
            return students;
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Error retrieving all students", e);
        }
    }

    // Delete student by ID
    @Override
    public void deleteStudent(Long id) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query<?> query = session.createQuery("delete from Student where id=:studentId");
            query.setParameter("studentId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting student with id: " + id, e);
        }
    }

    // Search students by name
    @Override
    public List<Student> searchStudents(String searchTerm) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query<Student> query;
            if (searchTerm != null && searchTerm.trim().length() > 0) {
                String searchPattern = "%" + searchTerm.toLowerCase() + "%";
                query = session.createQuery(
                    "from Student where lower(firstName) like :term " +
                    "or lower(lastName) like :term " +
                    "or lower(studentNumber) like :term " +
                    "or lower(department) like :term " +
                    "or cast(enrollmentDate as string) like :term " +
                    "order by lastName", 
                    Student.class);
                query.setParameter("term", searchPattern);
            } else {
                query = session.createQuery("from Student order by lastName", Student.class);
            }
            
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Error searching students", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
