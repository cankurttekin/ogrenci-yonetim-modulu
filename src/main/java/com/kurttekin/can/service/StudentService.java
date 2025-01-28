package com.kurttekin.can.service;

import com.kurttekin.can.dao.StudentDAO;
import com.kurttekin.can.dao.StudentDAOImpl;
import com.kurttekin.can.model.Student;

import java.util.List;

/*
 * This layer acts as an intermediary between the DAO and the UI.
 */
public class StudentService {

    private StudentDAO studentDAO = new StudentDAOImpl();

    public void saveStudent(Student student) {
        studentDAO.saveStudent(student);
    }

    public void deleteStudent(Long id) {
        studentDAO.deleteStudent(id);
    }

    public Student getStudentById(Long id) {
        return studentDAO.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public List<Student> searchStudents(String searchName) {
        return studentDAO.searchStudents(searchName);
    }

}
