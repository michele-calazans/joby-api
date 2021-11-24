package br.com.jobem.api.controllers;

import br.com.jobem.api.commons.CommuniqueBuilder;
import br.com.jobem.api.commons.CommuniqueModel;
import br.com.jobem.api.dtos.requests.ResumeRequest;
import br.com.jobem.api.dtos.requests.SaveImage;
import br.com.jobem.api.dtos.requests.StudentCreationRequest;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.models.StudentInstitute;
import br.com.jobem.api.repositories.StudentRepository;
import br.com.jobem.api.services.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    CommuniqueBuilder communiqueBuilder;

    @CrossOrigin
    @GetMapping
    public ResponseEntity index() {
        List<Student> list = studentRepository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }

        return noContent().build();
    }

    @CrossOrigin
    @PostMapping("/cpf")
    public ResponseEntity validateCpf(@RequestBody StudentInstitute studentCpf) {
        StudentInstitute result = studentService.validateCpf(studentCpf);

        if (result != null) {
            return status(200).body(result);
        } else {
            return status(404).build();
        }
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity create(@RequestBody StudentCreationRequest student) {
        if (studentService.createStudent(student) != null) {
            return status(201).build();
        } else {
            return status(422).build();
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id) {
        if (studentRepository.existsById(id)) {
            return of(studentRepository.findById(id));
        }

        return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ok().build();
        }

        return notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Student student, @PathVariable int id) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
            return ok().build();
        }

        return notFound().build();
    }

    @CrossOrigin
    @PutMapping("/{id}/{discordId}")
    public ResponseEntity updateDiscordId(@RequestBody Student student, @PathVariable int id, @PathVariable String discordId) {
        if (studentRepository.existsById(id)) {
            System.out.println("Entrou");
            student.setId(id);
            student.setDiscordId(discordId);
            studentRepository.save(student);
            return ok().build();
        }

        return notFound().build();
    }

    @CrossOrigin
    @PutMapping("/resume/{id}")
    public ResponseEntity createResume(@PathVariable int id, @RequestBody ResumeRequest resume) {
        if (studentService.createResume(id, resume)) {
            return ok().build();
        } else {
            return unprocessableEntity().build();
        }
    }

    @GetMapping("communiques/{id}")
    public ResponseEntity getCommunications(@PathVariable int id) {
        List<CommuniqueModel> communiques = communiqueBuilder.getCommunications(id);

        if (!communiques.isEmpty()) {
            return ok(communiques);
        } else {
            return noContent().build();
        }
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity getByUser(@PathVariable int id) {
        if (studentRepository.existsByInformationUserId(id)) {
            return ResponseEntity.status(200).body(studentRepository.findByInformationUserId(id));
        }

        return ResponseEntity.status(404).build();
    }
}
