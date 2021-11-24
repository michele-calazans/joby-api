package br.com.jobem.api.controllers;

import br.com.jobem.api.models.Experience;
import br.com.jobem.api.repositories.ExperienceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {
    @Autowired
    private ExperienceRepository experienceRepository;

    @GetMapping
    public ResponseEntity index() {
        List<Experience> list = experienceRepository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }

        return noContent().build();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Experience experience) {
        experienceRepository.save(experience);
        return status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id) {
        if (experienceRepository.existsById(id)) {
            return of(experienceRepository.findById(id));
        }

        return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (experienceRepository.existsById(id)) {
            experienceRepository.deleteById(id);
            return ok().build();
        }

        return notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Experience experience, @PathVariable int id) {
        if (experienceRepository.existsById(id)) {
            experience.setId(id);
            experienceRepository.save(experience);
            return ok().build();
        }

        return notFound().build();
    }

    @CrossOrigin
    @GetMapping("/student/{id}")
    public ResponseEntity showExperienceByStudent(@PathVariable int id) {
        if (experienceRepository.existsExperienceByStudentId(id)) {
            return of(experienceRepository.findExperienceByStudentId(id));
        }

        return notFound().build();
    }

    @CrossOrigin
    @GetMapping("/student/user/{id}")
    public ResponseEntity showExperienceByUser(@PathVariable int id) {
        if (experienceRepository.existsExperienceByStudentInformationUserId(id)) {
            return of(experienceRepository.findExperienceByStudentInformationUserId(id));
        }

        return notFound().build();
    }
}
