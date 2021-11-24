package br.com.jobem.api.controllers;

import br.com.jobem.api.dataStructure.fileManager.exportFile.ListFileManager;
import br.com.jobem.api.dataStructure.fileManager.exportFile.ReadFile;
import br.com.jobem.api.dataStructure.fileManager.exportFile.WriteFile;
import br.com.jobem.api.dtos.requests.CompanyCreationRequest;
import br.com.jobem.api.models.Company;
import br.com.jobem.api.repositories.CompanyRepository;
import br.com.jobem.api.services.CompanyService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    WriteFile writeFile;
    @Autowired
    ReadFile readFile;
    @Autowired
    CompanyService companyService;
    ListFileManager fileManager = new ListFileManager();
    HttpHeaders headers = new HttpHeaders();
    HttpHeaders headersTwo = new HttpHeaders();

    @CrossOrigin
    @GetMapping
    public ResponseEntity index() {
        List<Company> list = companyRepository.findAll();
        if (!list.isEmpty()) {
            return ok(list);
        }

        return noContent().build();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody CompanyCreationRequest company) {
        companyService.createCompany(company);
        return status(201).build();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity showById(@PathVariable int id) {
        if (companyRepository.existsById(id)) {
            return of(companyRepository.findById(id));
        }

        return notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return ok().build();
        }

        return notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Company company, @PathVariable int id) {
        if (companyRepository.existsById(id)) {
            company.setId(id);
            companyRepository.save(company);
            return ok().build();
        }

        return notFound().build();
    }


    @GetMapping("/students-by-job/{id}")
    public ResponseEntity findStudents(@PathVariable int id) {
        return ResponseEntity.status(200).body(companyRepository.findStudentByJob(id));
    }

    @CrossOrigin
    @GetMapping("/reports/infos-job/{id}")
    public ResponseEntity writeReportJob(@PathVariable int id) {
        writeFile.montaRegistro(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/reports/infos-students/{id}")
    public ResponseEntity writeReportStudent(@PathVariable int id) {
        fileManager.gravaLista(
                companyService.findStudentsByJob(id),
                true,
                "CandidatosInfos"
        );

        return ok().build();
    }

    @CrossOrigin
    @GetMapping("/reports/download/csv/{csvName}")
    public HttpEntity<byte[]> downloadCsvFile(@PathVariable String csvName) throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(csvName + ".csv"));
        headers.add("Content-Disposition", "attachment;filename=\"" + csvName + ".csv\"");

        return new HttpEntity<>(file, headers);
    }

    @CrossOrigin
    @GetMapping("/reports/download/txt/{txtName}")
    public HttpEntity<byte[]> downloadTxtFile(@PathVariable String txtName) throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(txtName + ".txt"));
        headersTwo.add("Content-Disposition", "attachment;filename=\"" + txtName + ".txt\"");
        return new HttpEntity<>(file, headersTwo);
    }

    @CrossOrigin
    @GetMapping("/company-user/{id}")
    public ResponseEntity getCompanyId(@PathVariable int id) {
        Optional<Company> company = companyRepository.findByInformation_User_Id(id);

        if (company.isPresent()) {
            return ResponseEntity.status(200).body(company.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
