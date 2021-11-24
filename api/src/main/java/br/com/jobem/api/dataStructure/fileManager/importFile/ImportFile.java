package br.com.jobem.api.dataStructure.fileManager.importFile;

import br.com.jobem.api.dataStructure.FilaObjCircular;
import br.com.jobem.api.models.CourseInstitute;
import br.com.jobem.api.models.StudentInstitute;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportFile {
    @Autowired
    private ImportFileService importFileService;
    StudentInstitute studentInstitute;
    FilaObjCircular<StudentInstitute> queue = new FilaObjCircular<>(30);

    public void importFile(String nomeArq) {
        BufferedReader entrada = null;
        CourseInstitute courseInstitute;
        String registro, tipoRegistro, instituteName, courseName, studentName, cpfStudent;

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            registro = entrada.readLine();

            while (registro != null && !registro.equals("")) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("01")) {
                    studentInstitute = new StudentInstitute();

                    //studentName = registro.substring(16, 66).trim();

                    studentInstitute.setCpf(registro.substring(2, 16));

                } else if (tipoRegistro.equals("03")) {
                    courseName = registro.substring(2, 22).replace("|", "");
                    instituteName = registro.substring(22, 52).replace("|", "");

                    courseInstitute = importFileService.getStudentInstitute(courseName, instituteName);

                    studentInstitute.setCourseInstitute(courseInstitute);

                    queue.insert(studentInstitute);
                } else {
                    System.out.println("Irrelevant failure");
                }
                registro = entrada.readLine();
            }

            entrada.close();

            importFileService.saveData(queue);

        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
    }
}
