package br.com.jobem.api.services;

import br.com.jobem.api.models.StudentInstitute;
import br.com.jobem.api.repositories.StudentInstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentInstituteService {
    @Autowired
    private StudentInstituteRepository instituteRepository;

    public boolean verifyCpf(StudentInstitute studentInstitute) {
        if (!instituteRepository.existsByCpf(studentInstitute.getCpf())) {
            instituteRepository.save(studentInstitute);
            return true;
        } else {
            return false;
        }
    }
}
