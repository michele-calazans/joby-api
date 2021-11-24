package br.com.jobem.api.controllers;

import br.com.jobem.api.models.CourseInstitute;
import br.com.jobem.api.repositories.CourseInstituteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/institute-course")
public class CourseInstituteController {
    @Autowired
    CourseInstituteRepository courseInstituteRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity index() {
        List<CourseInstitute> list = courseInstituteRepository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }

        return noContent().build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody CourseInstitute company) {
        courseInstituteRepository.save(company);
        return status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id) {
        if (courseInstituteRepository.existsById(id)) {
            return of(courseInstituteRepository.findById(id));
        }

        return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (courseInstituteRepository.existsById(id)) {
            courseInstituteRepository.deleteById(id);
            return ok().build();
        }

        return notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody CourseInstitute courseInstitute,
                                 @PathVariable int id) {
        if (courseInstituteRepository.existsById(id)) {
            courseInstitute.setId(id);
            courseInstituteRepository.save(courseInstitute);
            return ok().build();
        }

        return notFound().build();
    }

    @CrossOrigin
    @GetMapping("/courses-institute/{id}")
    public ResponseEntity getCoursesByInstitute(@PathVariable int id) {
        List<CourseInstitute> courses = courseInstituteRepository.findByInstituteId(id);

        if (!courses.isEmpty()) {
            return ok(courses);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
