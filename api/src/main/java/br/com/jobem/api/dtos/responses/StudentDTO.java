package br.com.jobem.api.dtos.responses;

import java.time.LocalDate;

public class StudentDTO {
    private Integer id;
    private String district;
    private String city;
    private String email;
    private LocalDate birthday;
    private String institute;
    private String courseTime;
    private String image;

    public StudentDTO(
            Integer id,
            String district,
            String city,
            String email,
            LocalDate birthday,
            String institute,
            String courseTime,
            String image) {
        this.id = id;
        this.district = district;
        this.city = city;
        this.email = email;
        this.birthday = birthday;
        this.institute = institute;
        this.courseTime = courseTime;
        this.image = image;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
