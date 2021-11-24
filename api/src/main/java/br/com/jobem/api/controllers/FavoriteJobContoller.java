package br.com.jobem.api.controllers;

import br.com.jobem.api.dtos.responses.StudentApplicationDTO;
import br.com.jobem.api.dtos.responses.StudentDTO;
import br.com.jobem.api.models.FavoriteJob;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.repositories.FavoriteJobRepository;
import br.com.jobem.api.services.FavoriteJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/favorite-job")
public class FavoriteJobContoller {

    @Autowired
    private FavoriteJobRepository repository;
    @Autowired
    private FavoriteJobService favoriteJobService;

    @GetMapping
    public ResponseEntity index() {
        List<FavoriteJob> list = repository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }
        return status(204).build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody FavoriteJob favorite) {
        if (favoriteJobService.save(favorite)) {
            return status(201).build();
        } else {
            return badRequest().build();
        }
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
    public ResponseEntity putFavorite(@PathVariable int id, @RequestBody FavoriteJob favorite) {
        if (repository.existsById(id)) {
            favorite.setId(id);
            repository.save(favorite);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @CrossOrigin
    @PostMapping("/matches")
    public ResponseEntity matchFlow(@RequestBody FavoriteJob infosMatch) {
        if (favoriteJobService.matchFlow(infosMatch)) {
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @CrossOrigin
    @GetMapping("/likes/student/{id}")
    public ResponseEntity getLikes(@PathVariable int id) {
        List<FavoriteJob> jobs = repository.findByStudentId(id);

        if (!jobs.isEmpty()) {
            return ResponseEntity.status(200).body(jobs);
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @CrossOrigin
    @GetMapping("/matches/student/{id}")
    public ResponseEntity getMatches(@PathVariable int id) {
        List<FavoriteJob> jobs = repository.findByStudentIdAndMatchIsTrue(id);

        if (!jobs.isEmpty()) {
            return ResponseEntity.status(200).body(jobs);
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @PutMapping("/undo-like/{id}")
    public ResponseEntity removeLike(@PathVariable int id) {
        if (favoriteJobService.removeLastLike(id)) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @CrossOrigin
    @GetMapping("/applications/{id}")
    public ResponseEntity getApplications(@PathVariable int id) {
        List<FavoriteJob> students = repository.findByJobId(id);

        if (!students.isEmpty()) {
            return ok(students);
        } else {
            return noContent().build();
        }
    }

    @CrossOrigin
    @GetMapping("/applications-dto/{id}")
    public ResponseEntity getApplicationsDto(@PathVariable int id) {
        List<StudentApplicationDTO> students = repository.listStudentApplicationDto(id);

        if (!students.isEmpty()) {
            return ok(students);
        } else {
            return noContent().build();
        }
    }


    @CrossOrigin
    @GetMapping("/student-dto/{id}")
    public ResponseEntity getStudentDto(@PathVariable int id) {
        StudentDTO student = repository.listStudentDto(id);
        return ok(student);
    }

    @CrossOrigin
    @GetMapping("/count/{id}")
    public ResponseEntity countByStudentId(@PathVariable int id) {
        if (repository.existsFavoriteJobByStudentId(id)) {
            return ResponseEntity.status(200).body(repository.countByStudentId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
