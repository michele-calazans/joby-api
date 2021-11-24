package br.com.jobem.api.dtos.responses;

public class StudentApplicationDTO {
    private Integer id;
    private String name;
    private String course;
    private String image;
    private Integer jobId;

    public StudentApplicationDTO(
            Integer id,
            String name,
            String course,
            Integer jobId) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.jobId = jobId;
    }

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
