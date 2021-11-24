package br.com.jobem.api.dtos.requests;

import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.models.StudentInstitute;
import br.com.jobem.api.models.User;

public class StudentCreationRequest {
    private User user;
    private Information information;
    private Student student;
    private StudentInstitute studentInstitute;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentInstitute getStudentInstitute() {
        return studentInstitute;
    }

    public void setStudentInstitute(StudentInstitute studentInstitute) {
        this.studentInstitute = studentInstitute;
    }
}
