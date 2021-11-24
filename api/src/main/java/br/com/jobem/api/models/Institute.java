package br.com.jobem.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String teachingModality;
    private String codInep;

    @OneToOne
    @JoinColumn(name = "information_fk")
    private Information information;

    public Institute(
            String name,
            String teachingModality,
            String codInep,
            Information information
    ) {
        this.name = name;
        this.teachingModality = teachingModality;
        this.codInep = codInep;
        this.information = information;
    }

    public Institute() {
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

    public String getTeachingModality() {
        return teachingModality;
    }

    public void setTeachingModality(String teachingModality) {
        this.teachingModality = teachingModality;
    }

    public String getCodInep() {
        return codInep;
    }

    public void setCodInep(String codInep) {
        this.codInep = codInep;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}
