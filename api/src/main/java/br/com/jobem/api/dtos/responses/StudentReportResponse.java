package br.com.jobem.api.dtos.responses;

import java.time.LocalDate;

public class StudentReportResponse {
    private String nameStudent;
    private String nameInstitute;
    private String nameJob;
    private LocalDate age;
    private String gender;
    private LocalDate postDate;

    public StudentReportResponse(
            String nameStudent,
            String nameInstitute,
            String nameJob,
            LocalDate age,
            String gender,
            LocalDate postDate
    ) {
        this.nameStudent = nameStudent;
        this.nameInstitute = nameInstitute;
        this.nameJob = nameJob;
        this.age = age;
        this.gender = gender;
        this.postDate = postDate;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getNameInstitute() {
        return nameInstitute;
    }

    public void setNameInstitute(String nameInstitute) {
        this.nameInstitute = nameInstitute;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public LocalDate getAge() {
        return age;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }
}
