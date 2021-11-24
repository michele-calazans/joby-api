package br.com.jobem.api.dtos.requests;

import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.Institute;
import br.com.jobem.api.models.User;

public class InstituteCreationRequest {
    private User user;
    private Information information;
    private Institute institute;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}
