package br.com.jobem.api.services;

import br.com.jobem.api.dtos.requests.ResumeRequest;
import br.com.jobem.api.dtos.requests.SaveImage;
import br.com.jobem.api.dtos.requests.StudentCreationRequest;
import br.com.jobem.api.models.AcademicFormation;
import br.com.jobem.api.models.Experience;
import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.KnowledgeArea;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.models.StudentInstitute;
import br.com.jobem.api.models.User;
import br.com.jobem.api.commons.Constants;
import br.com.jobem.api.repositories.ExperienceRepository;
import br.com.jobem.api.repositories.InformationRepository;
import br.com.jobem.api.repositories.StudentInstituteRepository;
import br.com.jobem.api.repositories.StudentRepository;
import br.com.jobem.api.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentInstituteRepository instituteRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    public Student createStudent(StudentCreationRequest studentRequest) {
        User user = new User(
                studentRequest.getUser().getEmail(),
                studentRequest.getUser().getPassword(),
                Constants.LEVEL_ACCESS_STUDENT
        );

        userRepository.save(user);

        Information information = new Information(
                studentRequest.getInformation().getStreetLocation(),
                studentRequest.getInformation().getComplement(),
                studentRequest.getInformation().getZipCode(),
                studentRequest.getInformation().getDistrict(),
                studentRequest.getInformation().getCity(),
                studentRequest.getInformation().getState(),
                studentRequest.getInformation().getCountry(),
                studentRequest.getInformation().getNumber(),
                studentRequest.getInformation().getCellNumber(),
                studentRequest.getInformation().getPhoneNumber(),
                user
        );

        informationRepository.save(information);


        Student student = new Student(
                studentRequest.getStudent().getName(),
                studentRequest.getStudent().getBirthDate(),
                studentRequest.getStudent().getGender(),
                studentRequest.getStudent().getMaritalStatus(),
                studentRequest.getStudent().isDisabledPerson(),
                information,
                studentRequest.getStudentInstitute(),
                studentRequest.getStudent().getDiscordId()
        );

        return studentRepository.save(student);
    }

    public StudentInstitute validateCpf(StudentInstitute creationRequest) {
        Optional<StudentInstitute> studentExistent = instituteRepository.findByCpf(
                creationRequest.getCpf());

        if (studentExistent.isPresent()) {
            boolean cpfInUse = studentRepository.existsByStudentInstituteId(studentExistent.get().getId());

            if (!cpfInUse) {
                return studentExistent.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean createResume(int id, ResumeRequest resume) {
        try {
            if (studentRepository.existsById(id)) {
                Student student = studentRepository.findById(id).get();
                Information information = student.getInformation();
                List<Experience> experiences = resume.getExperiences();

                for (Experience experience : experiences) {
                    experience.setStudent(student);
                    experienceRepository.save(experience);
                }

                information.setImageContent(resume.getImage());
                information.setImageType(resume.getImageType());

                student.setAcademicFormation(getAcademicFormation(resume));
                student.setKnowledgeArea(getKnowledgeArea(resume));

                studentRepository.save(student);

                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public Boolean saveImages(int id, SaveImage saveImage) {
        Optional<Information> infoOptional = informationRepository.findById(id);
        if (infoOptional.isPresent()) {
            Information information = infoOptional.get();

            information.setImageContent(saveImage.getImage());
            information.setImageType(saveImage.getImageType());

            informationRepository.save(information);
            return true;
        } else {
            return false;
        }
    }

    public Boolean saveImageBackground(int id, SaveImage saveImage) {
        Optional<Information> infoOptional = informationRepository.findById(id);
        if (infoOptional.isPresent()) {
            Information information = infoOptional.get();

            information.setImageBackground(saveImage.getImage());

            informationRepository.save(information);
            return true;
        } else {
            return false;
        }
    }

    private KnowledgeArea getKnowledgeArea(ResumeRequest resume) {
        return resume.getKnowledgeArea();
    }

    private AcademicFormation getAcademicFormation(ResumeRequest resume) {
        return resume.getAcademicFormation();
    }
}
