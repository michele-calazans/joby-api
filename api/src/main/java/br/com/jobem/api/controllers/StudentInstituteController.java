package br.com.jobem.api.controllers;

import br.com.jobem.api.models.StudentInstitute;
import br.com.jobem.api.repositories.StudentInstituteRepository;
import br.com.jobem.api.services.StudentInstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/student-institute")
public class StudentInstituteController {

    @Autowired
    private StudentInstituteRepository repository;

    @Autowired
    private StudentInstituteService service;

    @GetMapping()
    public ResponseEntity index() {
        List<StudentInstitute> list = repository.findAll();

        if(list.isEmpty()) {
            return status(204).build();
        }

        return ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        if(repository.existsById(id)) {
            return of(repository.findById(id));
        }

        return status(404).build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity postStudentInstitute(@RequestBody StudentInstitute studentInstitute) {
        if (service.verifyCpf(studentInstitute)) {
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentInstitute(@PathVariable int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ok().build();
        }

        return status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudentInstitute(@PathVariable int id,
                                                 @RequestBody StudentInstitute studentInstitute) {
        if(repository.existsById(id)) {
            studentInstitute.setId(id);
            repository.save(studentInstitute);
            return ok().build();
        }

        return status(404).build();
    }

    @CrossOrigin
    @GetMapping("/count/{id}")
    public ResponseEntity countStudents(@PathVariable int id) {
        return ok(repository.countByCourseInstitute_InstituteId(id));
    }
}
