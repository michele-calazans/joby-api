package br.com.jobem.api.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "student_institute")
public class StudentInstitute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CPF
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "course_institute_fk")
    private CourseInstitute courseInstitute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public CourseInstitute getCourseInstitute() {
        return courseInstitute;
    }

    public void setCourseInstitute(CourseInstitute courseInstitute) {
        this.courseInstitute = courseInstitute;
    }
}
