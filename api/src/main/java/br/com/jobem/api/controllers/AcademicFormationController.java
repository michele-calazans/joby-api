package br.com.jobem.api.controllers;

import br.com.jobem.api.models.AcademicFormation;
import br.com.jobem.api.repositories.AcademicFormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/academic-formation")
public class AcademicFormationController {

    @Autowired
    private AcademicFormationRepository repository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity index(){
        List<AcademicFormation> list = repository.findAll();
        if(!list.isEmpty()) {
            return ok(list);
        }
        return status(204).build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody AcademicFormation academicFormation){
        repository.save(academicFormation);
        return status(201).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id){
        if (id > 0){
            return of(repository.findById(id));
        }
        return status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return status(200).build();
        }
        return status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody  AcademicFormation academicFormation){
        if (repository.existsById(id)){
            academicFormation.setId(id);
            repository.save(academicFormation);
            return status(200).build();
        }
        return status(404).build();

    }
}
