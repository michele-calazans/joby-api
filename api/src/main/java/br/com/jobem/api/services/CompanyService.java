package br.com.jobem.api.services;

import br.com.jobem.api.dataStructure.ListaObj;
import br.com.jobem.api.dtos.requests.CompanyCreationRequest;
import br.com.jobem.api.models.Company;
import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.models.User;
import br.com.jobem.api.commons.Constants;
import br.com.jobem.api.repositories.CompanyRepository;
import br.com.jobem.api.repositories.InformationRepository;
import br.com.jobem.api.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    UserRepository userRepository;

    public ListaObj<Student> findStudentsByJob(int id) {
        List<Student> data = companyRepository.findStudentByJob(id);

        ListaObj<Student> listObj = new ListaObj(100);

        for (Student student : data) {
            listObj.adiciona(student);
        }

        return listObj;
    }

    public Company createCompany(CompanyCreationRequest companyRequest) {
        User user = new User(
                companyRequest.getUser().getEmail(),
                companyRequest.getUser().getPassword(),
                Constants.LEVEL_ACCESS_COMPANY
        );

        userRepository.save(user);

        Information information = new Information(
                companyRequest.getInformation().getStreetLocation(),
                companyRequest.getInformation().getComplement(),
                companyRequest.getInformation().getZipCode(),
                companyRequest.getInformation().getDistrict(),
                companyRequest.getInformation().getCity(),
                companyRequest.getInformation().getState(),
                companyRequest.getInformation().getNumber(),
                companyRequest.getInformation().getCountry(),
                companyRequest.getInformation().getCellNumber(),
                companyRequest.getInformation().getPhoneNumber(),
                user
        );

        informationRepository.save(information);

        Company company = new Company(
                companyRequest.getCompany().getName(),
                companyRequest.getCompany().getSector(),
                companyRequest.getCompany().getCnpj(),
                information
        );
        
        return companyRepository.save(company);
    }
}