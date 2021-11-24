package br.com.jobem.api.controllers;

import br.com.jobem.api.dtos.responses.JobDTO;
import br.com.jobem.api.models.Job;
import br.com.jobem.api.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobRepository repository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity index() {
        List<Job> list = repository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }
        return status(204).build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody Job job) {
        repository.save(job);
        return status(201).build();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id) {

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

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Job job) {
        if (repository.existsById(id)) {
            job.setId(id);
            repository.save(job);
            return status(200).build();
        }
        return status(404).build();
    }

    @CrossOrigin
    @GetMapping("/carousel/{idKnowledge}/{idAcademic}")
    public ResponseEntity carousel(@PathVariable int idKnowledge, @PathVariable int idAcademic) {
        List<Job> jobs = repository.findByKnowledgeAreaIdAndAcademicFormationId(idKnowledge, idAcademic);

        if (!jobs.isEmpty()) {
            return ok(jobs);
        } else {
            return noContent().build();
        }
    }

    @CrossOrigin
    @GetMapping("/jobs-company/{id}")
    public ResponseEntity getJobsByCompanyId(@PathVariable int id) {
        List<Job> jobs = repository.findByCompany_Id(id);

        if (!jobs.isEmpty()) {
            return ok(jobs);
        } else {
            return noContent().build();
        }
    }

    @CrossOrigin
    @GetMapping("/jobs-dto-company/{id}")
    public ResponseEntity getJobsDtoByCompanyId(@PathVariable int id) {
        List<JobDTO> jobs = repository.listJobDto(id);

        if (!jobs.isEmpty()) {
            return ok(jobs);
        } else {
            return noContent().build();
        }
    }


    @CrossOrigin
    @GetMapping("/count/{id}")
    public ResponseEntity countJobs(@PathVariable int id) {
         return ok(repository.countByCompany_Id(id));
    }
}
