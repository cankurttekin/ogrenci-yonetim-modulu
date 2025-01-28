package com.kurttekin.can.bean;

import com.kurttekin.can.model.Student;
import com.kurttekin.can.service.StudentService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "studentBean")
@ViewScoped
public class StudentBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private StudentService studentService = new StudentService();
    private Student student;
    private List<Student> students;
    private String searchTerm;
    private List<Student> filteredStudents;

    public StudentBean() {
        this.student = new Student();
        loadStudents();
    }

    public void loadStudents() {
        try {
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                students = studentService.searchStudents(searchTerm);
            } else {
                students = studentService.getAllStudents();
            }
            filteredStudents = new ArrayList<>(students);
        } catch (Exception e) {
            e.printStackTrace();
            filteredStudents = new ArrayList<>();
        }
    }

    public void saveStudent() {
        try {
            studentService.saveStudent(student);
            this.student = new Student(); // Reset form
            loadStudents(); // Refresh list
        } catch (Exception e) {
            e.printStackTrace();
            // Add proper error handling
        }
    }

    public void deleteStudent(Long id) {
        try {
            studentService.deleteStudent(id);
            loadStudents(); // Refresh the list after deletion
            
            // Reset the form if the deleted student was being edited
            if (student != null && student.getId() != null && student.getId().equals(id)) {
                student = new Student();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Öğrenci silindi."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Öğrenci silinemedi."));
            e.printStackTrace();
        }
    }

    public void search() {
        loadStudents();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student selectedStudent) {
        try {
            this.student = new Student();
            this.student.setId(selectedStudent.getId());
            this.student.setFirstName(selectedStudent.getFirstName());
            this.student.setLastName(selectedStudent.getLastName());
            this.student.setStudentNumber(selectedStudent.getStudentNumber());
            this.student.setDepartment(selectedStudent.getDepartment());
            this.student.setEnrollmentDate(selectedStudent.getEnrollmentDate());
        } catch (Exception e) {
            e.printStackTrace();
            // Add proper error handling
        }
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getFilteredStudents() {
        return filteredStudents;
    }

    public void setFilteredStudents(List<Student> filteredStudents) {
        this.filteredStudents = filteredStudents;
    }
}
