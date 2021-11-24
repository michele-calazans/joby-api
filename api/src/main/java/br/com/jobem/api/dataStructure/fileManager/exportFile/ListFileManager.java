package br.com.jobem.api.dataStructure.fileManager.exportFile;

import br.com.jobem.api.dataStructure.ListaObj;
import br.com.jobem.api.models.Student;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.springframework.stereotype.Service;

public class ListFileManager {

    public void gravaLista(ListaObj<Student> lista, boolean isCSV, String nomeArquivo) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;

        if (isCSV) {
            nomeArquivo += ".csv";
        } else {
            nomeArquivo += ".txt";
        }

        try {
            arq = new FileWriter(nomeArquivo, true);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Student student = lista.getElemento(i);
                if (isCSV) {
                    saida.format("%d;%s;%s;%s;%s;%s%n",
                            student.getId(),
                            student.getName(),
                            student.getBirthDate(),
                            student.getGender(),
                            student.getMaritalStatus(),
                            student.isDisabledPerson()
                    );
                } else {
                    saida.format("%d %s %s %s %s %s%n",
                            student.getId(),
                            student.getName(),
                            student.getBirthDate(),
                            student.getGender(),
                            student.getMaritalStatus(),
                            student.isDisabledPerson());
                }
            }
        } catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public void leExibeArquivo(boolean isCSV, String nomeArquivo) {
        FileReader arq = null;
        Scanner in = null;
        boolean deuRuim = false;

        if (isCSV) {
            nomeArquivo += ".csv";
        } else {
            nomeArquivo += ".txt";
        }

        try {
            arq = new FileReader(nomeArquivo);
            if (isCSV) {
                in = new Scanner(arq).useDelimiter(";|\\n");
            } else {
                in = new Scanner(arq);
            }
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%-4s%-20s%-11s%-10s%-9s%-11s\n",
                    "ID", "NOME", "DT. NASC", "GÊNERO", "ESTADO CIVIL", "DEFICIENTE");
            while (in.hasNext()) {

                Integer id = in.nextInt();
                String nome = in.next();
                String dataNasc = in.next();
                String genero = in.next();
                String estCivil = in.next();
                String deficiente = in.next();

                System.out.printf("%-4d%-20s%-11s%-10s%-9s%-11s\n",
                        id, nome, dataNasc, genero, estCivil, deficiente);
            }
        } catch (NoSuchElementException erro) {
            System.err.println("Arquivo com problemas.");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.err.println("Erro na leitura do arquivo.");
            deuRuim = true;
        } finally {
            in.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
