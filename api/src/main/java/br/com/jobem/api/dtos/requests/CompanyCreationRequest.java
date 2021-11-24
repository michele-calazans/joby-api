package br.com.jobem.api.dtos.requests;

import br.com.jobem.api.models.Company;
import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.User;

public class CompanyCreationRequest {
    private User user;
    private Information information;
    private Company company;

    public CompanyCreationRequest(
            User user,
            Information information,
            Company company
    ) {
        this.user = user;
        this.information = information;
        this.company = company;
    }

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
