package br.com.jobem.api.dataStructure.fileManager.importFile;

import br.com.jobem.api.dataStructure.FilaObjCircular;
import br.com.jobem.api.dtos.requests.ImportFileRequest;
import br.com.jobem.api.models.CourseInstitute;
import br.com.jobem.api.models.StudentInstitute;
import br.com.jobem.api.repositories.StudentInstituteRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportFileService {
    @Autowired
    StudentInstituteRepository repository;
    @Autowired
    private ImportFile importFile;

    public CourseInstitute getStudentInstitute(String courseName, String instituteName) {
        Optional<CourseInstitute> courseInstitute = repository.getStudentInstitute(
                courseName,
                instituteName);
        return courseInstitute.orElse(null);
    }

    public void saveData(FilaObjCircular<StudentInstitute> dataList) {
        while (!dataList.isEmpty()) {
                StudentInstitute studentInstitute = dataList.pool();

                if(studentInstitute != null && !repository.existsByCpf(studentInstitute.getCpf())) {
                    repository.save(studentInstitute);
                }
        }
    }

    public boolean createFile(ImportFileRequest archive) {
        try {
            String content = archive.getFile();
            String name = archive.getFileName();

            Path path = Paths.get(name);
            Files.write(path, content.getBytes());

            importFile.importFile(Objects.requireNonNull(name));

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
