package br.com.jobem.api.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String overview;
    private String journey;
    private String location;
    private String image;
    private double salary;
    private LocalDate postDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "company_fk")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "academic_formation_fk")
    private AcademicFormation academicFormation;

    @ManyToOne
    @JoinColumn(name = "knowledge_area_fk")
    private KnowledgeArea knowledgeArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String locate) {
        this.location = locate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AcademicFormation getAcademicFormation() {
        return academicFormation;
    }

    public void setAcademicFormation(AcademicFormation academicFormation) {
        this.academicFormation = academicFormation;
    }

    public KnowledgeArea getKnowledgeArea() {
        return knowledgeArea;
    }

    public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
        this.knowledgeArea = knowledgeArea;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}