package com.kurttekin.can.dao;

import com.kurttekin.can.model.Student;

import java.util.List;

public interface StudentDAO {
    //void addStudent(Student student);
    void saveStudent(Student student); // Save and Update
    void deleteStudent(Long id);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    List<Student> searchStudents(String searchName);
}
