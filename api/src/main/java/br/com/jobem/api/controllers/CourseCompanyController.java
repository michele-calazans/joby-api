package br.com.jobem.api.controllers;

import br.com.jobem.api.models.CourseCompany;
import br.com.jobem.api.models.CourseInstitute;
import br.com.jobem.api.repositories.CourseCompanyRepository;
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

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/company-course")
public class CourseCompanyController {

    @Autowired
    private CourseCompanyRepository courseCompanyRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity index() {
        List<CourseCompany> list = courseCompanyRepository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }

        return noContent().build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody CourseCompany courseCompany) {
        courseCompanyRepository.save(courseCompany);
        return status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseCompany> showById(@PathVariable int id) {
        if (courseCompanyRepository.existsById(id)) {
            return of(courseCompanyRepository.findById(id));
        }

        return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (courseCompanyRepository.existsById(id)) {
            courseCompanyRepository.deleteById(id);
            return ok().build();
        }

        return notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody CourseCompany courseCompany, @PathVariable int id) {
        if (courseCompanyRepository.existsById(id)) {
            courseCompany.setId(id);
            courseCompanyRepository.save(courseCompany);
            return ok().build();
        }

        return notFound().build();
    }

    @CrossOrigin
    @GetMapping("/courses-company/{id}")
    public ResponseEntity getCoursesByCompany(@PathVariable int id) {
        List<CourseCompany> courses = courseCompanyRepository.findByCompanyId(id);

        if (!courses.isEmpty()) {
            return ok(courses);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
