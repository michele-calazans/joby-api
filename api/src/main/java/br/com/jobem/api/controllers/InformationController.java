package br.com.jobem.api.controllers;

import br.com.jobem.api.dtos.requests.SaveImage;
import br.com.jobem.api.models.Information;
import br.com.jobem.api.repositories.InformationRepository;
import br.com.jobem.api.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationRepository repository;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity index() {
        List<Information> list = repository.findAll();
        if(!list.isEmpty()) {
            return ok(list);
        }

        return status(204).build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Information information) {

        repository.save(information);
        return status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showByID(@PathVariable int id) {

        if (id > 0) {
            return of(repository.findById(id));
        }

        return status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return status(200).build();
        }

        return status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Information information) {
        if (repository.existsById(id)) {
            information.setId(id);
            repository.save(information);
            return status(200).build();
        }

        return status(404).build();
    }

    @CrossOrigin
    @PutMapping("/images/{id}")
    public ResponseEntity saveImage(@PathVariable int id, @RequestBody SaveImage saveImage) {
        if (studentService.saveImages(id, saveImage)) {
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/images-back/{id}")
    public ResponseEntity saveImageBackground(@PathVariable int id, @RequestBody SaveImage saveImage) {
        if (studentService.saveImages(id, saveImage)) {
            return ok().build();
        } else {
            return notFound().build();
        }
    }
}
