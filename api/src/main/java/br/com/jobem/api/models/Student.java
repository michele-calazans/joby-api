package br.com.jobem.api.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String maritalStatus;
    private boolean disabledPerson;
    private String discordId;

    @OneToOne
    @JoinColumn(name = "information_fk")
    private Information information;

    @ManyToOne
    @JoinColumn(name = "academic_formation_fk")
    private AcademicFormation academicFormation;

    @ManyToOne
    @JoinColumn(name = "knowledge_area_fk")
    private KnowledgeArea knowledgeArea;

    @ManyToOne
    @JoinColumn(name = "student_institute_fk")
    private StudentInstitute studentInstitute;

    public Student(
            String name,
            LocalDate birthDate,
            String gender,
            String maritalStatus,
            boolean disabledPerson,
            Information information,
            StudentInstitute studentInstitute,
            String discordId
    ) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.disabledPerson = disabledPerson;
        this.information = information;
        this.studentInstitute = studentInstitute;
        this.discordId = discordId;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean isDisabledPerson() {
        return disabledPerson;
    }

    public void setDisabledPerson(boolean disabledPerson) {
        this.disabledPerson = disabledPerson;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public StudentInstitute getStudentInstitute() {
        return studentInstitute;
    }

    public void setStudentInstitute(StudentInstitute studentInstitute) {
        this.studentInstitute = studentInstitute;
    }

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }
}

