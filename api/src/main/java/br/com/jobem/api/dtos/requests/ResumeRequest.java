package br.com.jobem.api.dtos.requests;

import br.com.jobem.api.models.AcademicFormation;
import br.com.jobem.api.models.Experience;
import br.com.jobem.api.models.KnowledgeArea;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ResumeRequest {
    private AcademicFormation academicFormation;
    private KnowledgeArea knowledgeArea;
    private List<Experience> experiences;
    private String image;
    private String imageType;

    public ResumeRequest(AcademicFormation academicFormation,
                         KnowledgeArea knowledgeArea,
                         List<Experience> experiences) {
        this.academicFormation = academicFormation;
        this.knowledgeArea = knowledgeArea;
        this.experiences = experiences;
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

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() { return imageType; }

    public void setImageType(String imageType) { this.imageType = imageType;    }
}
