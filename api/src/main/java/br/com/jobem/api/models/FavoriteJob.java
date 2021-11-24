package br.com.jobem.api.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "favorite_job")
public class FavoriteJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "job_fk")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "student_fk")
    private Student student;

    private LocalDate applicationDate = LocalDate.now();

    @JoinColumn(name = "is_match")
    private boolean is_match = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public boolean isMatch() {
        return is_match;
    }

    public void setMatch(boolean match) {
        this.is_match = match;
    }
}
