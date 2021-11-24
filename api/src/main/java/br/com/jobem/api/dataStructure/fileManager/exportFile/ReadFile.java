package br.com.jobem.api.dataStructure.fileManager.exportFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class ReadFile {

    public void leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        int contRegistro = 0;
        String studentName, instituteName, jobName, studenteGender;
        int studentAge;

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(2, 21));
                    System.out.println("Tipo de arquivo: " + registro.substring(21, 31));
                    System.out.println("Data de cadastro: " + registro.substring(31, 41));
                    System.out.println("Versão do layout: " + registro.substring(41, 43));

                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2, 12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    } else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else if (tipoRegistro.equals("01")) {

                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-50s %-30s %-20s %-2s %3s\n",
                                "NOME DO CANDIDATO",
                                "NOME DA INSTITUIÇÃO",
                                "NOME DA VAGA",
                                "IDADE DO CANDIDATO",
                                "GÊNERO");
                    }

                    studentName = registro.substring(2, 52);
                    instituteName = registro.substring(52, 82);
                    jobName = registro.substring(82, 102);
                    studentAge = Integer.parseInt(registro.substring(102, 104));
                    studenteGender = registro.substring(104, 114);

                    System.out.printf("%-50s %-30s %-20s %2d %3s\n",
                            studentName,
                            instituteName,
                            jobName,
                            studentAge,
                            studenteGender);

                    contRegistro++;
                } else {
                    System.out.println("Tipo de registro inválido");
                }
                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
    }
}