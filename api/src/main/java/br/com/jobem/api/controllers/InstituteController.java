package br.com.jobem.api.controllers;

import br.com.jobem.api.dataStructure.fileManager.importFile.ImportFile;
import br.com.jobem.api.dataStructure.fileManager.importFile.ImportFileService;
import br.com.jobem.api.dtos.requests.ImportFileRequest;
import br.com.jobem.api.dtos.requests.InstituteCreationRequest;
import br.com.jobem.api.models.Institute;
import br.com.jobem.api.repositories.InstituteRepository;
import br.com.jobem.api.services.InstituteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/institute")
public class InstituteController {

    @Autowired
    private InstituteRepository instituteRepository;
    @Autowired
    private InstituteService instituteService;
    @Autowired
    private ImportFileService importFileService;

    @GetMapping
    public ResponseEntity index() {
        List<Institute> list = instituteRepository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }

        return noContent().build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody InstituteCreationRequest institute) {
        instituteService.createInstitute(institute);
        return status(201).build();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id) {
        if (instituteRepository.existsById(id)) {
            return of(instituteRepository.findById(id));
        }

        return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (instituteRepository.existsById(id)) {
            instituteRepository.deleteById(id);
            return ok().build();
        }

        return notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Institute institute, @PathVariable int id) {
        if (instituteRepository.existsById(id)) {
            institute.setId(id);
            instituteRepository.save(institute);
            return ok().build();
        }

        return notFound().build();
    }

    @CrossOrigin
    @PostMapping("/import-file")
    public ResponseEntity saveDataImported(@RequestBody ImportFileRequest archive) {
        if (importFileService.createFile(archive)) {
            return ok().build();
        } else {
            return badRequest().build();
        }
    }

    @CrossOrigin
    @GetMapping("/institute-user/{id}")
    public ResponseEntity getInstituteId(@PathVariable int id) {
        Optional<Institute> institute = instituteRepository.findByInformation_User_Id(id);

        if (institute.isPresent()) {
            return ResponseEntity.status(200).body(institute.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
