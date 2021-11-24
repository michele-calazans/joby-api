package br.com.jobem.api.services;

import br.com.jobem.api.dtos.requests.InstituteCreationRequest;
import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.Institute;
import br.com.jobem.api.models.User;
import br.com.jobem.api.commons.Constants;
import br.com.jobem.api.repositories.InformationRepository;
import br.com.jobem.api.repositories.InstituteRepository;
import br.com.jobem.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {
    @Autowired
    InstituteRepository instituteRepository;
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    UserRepository userRepository;

    public Institute createInstitute(InstituteCreationRequest instituteRequest) {
        User user = new User(
                instituteRequest.getUser().getEmail(),
                instituteRequest.getUser().getPassword(),
                Constants.LEVEL_ACCESS_INSTITUTE
        );

        userRepository.save(user);

        Information information = new Information(
                instituteRequest.getInformation().getStreetLocation(),
                instituteRequest.getInformation().getComplement(),
                instituteRequest.getInformation().getZipCode(),
                instituteRequest.getInformation().getDistrict(),
                instituteRequest.getInformation().getCity(),
                instituteRequest.getInformation().getState(),
                instituteRequest.getInformation().getCountry(),
                instituteRequest.getInformation().getNumber(),
                instituteRequest.getInformation().getCellNumber(),
                instituteRequest.getInformation().getPhoneNumber(),
                user
        );

        informationRepository.save(information);

        Institute institute = new Institute(
                instituteRequest.getInstitute().getName(),
                instituteRequest.getInstitute().getTeachingModality(),
                instituteRequest.getInstitute().getCodInep(),
                information
        );

        return instituteRepository.save(institute);
    }
}
