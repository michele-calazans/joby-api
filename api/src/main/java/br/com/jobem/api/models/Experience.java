package br.com.jobem.api.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String company;
    private String localization;
    private String roleExperience;
    private String timeExperience;
    private LocalDate dateExperience;
    private String overview;

    @ManyToOne
    @JoinColumn(name = "student_fk")
    private Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public LocalDate getDateExperience() {
        return dateExperience;
    }

    public void setDateExperience(LocalDate dateExperience) {
        this.dateExperience = dateExperience;
    }

    public String getRoleExperience() {
        return roleExperience;
    }

    public void setRoleExperience(String roleExperience) {
        this.roleExperience = roleExperience;
    }

    public String getTimeExperience() {
        return timeExperience;
    }

    public void setTimeExperience(String timeExperience) {
        this.timeExperience = timeExperience;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
