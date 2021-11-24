package br.com.jobem.api.dataStructure.fileManager.exportFile;

import br.com.jobem.api.dtos.responses.StudentReportResponse;
import br.com.jobem.api.repositories.CompanyRepository;
import br.com.jobem.api.commons.CalculateAge;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteFile {
    @Autowired
    CompanyRepository companyRepository;
    CalculateAge calculateAge = new CalculateAge();

    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public void montaRegistro(int id) {

        List<StudentReportResponse> dataJob = companyRepository.findStudentAndInstituteByJob(id);

        String nomeArq = "CandidatosVagas"+ id +".txt";
        String header = "";
        String trailer = "";
        int contRegDados = 0;

        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00";
        header += formatter.format(dataDeHoje);
        header += "CANDIDATOS";
        header += dataJob.get(0).getPostDate();
        header += "01";

        gravaRegistro(nomeArq, header);


        for (StudentReportResponse studentReportResponse : dataJob) {
            String corpo = "";
            int age = calculateAge.calculateAge(studentReportResponse.getAge());
            corpo += "01";
            corpo += String.format("%-50s", studentReportResponse.getNameStudent());
            corpo += String.format("%-30s", studentReportResponse.getNameInstitute());
            corpo += String.format("%-20s", studentReportResponse.getNameJob());
            corpo += String.format("%2s", age);
            corpo += String.format("%-9s", studentReportResponse.getGender());
            gravaRegistro(nomeArq, corpo);
            contRegDados++;
        }

        trailer += "02";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq, trailer);
    }
}
