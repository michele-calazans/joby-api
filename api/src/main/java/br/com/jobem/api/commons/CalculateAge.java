package br.com.jobem.api.commons;

import java.time.LocalDate;

public class CalculateAge {
    public int calculateAge(LocalDate birthDate) {

        LocalDate today = LocalDate.now();

        int idade = today.getYear() - birthDate.getYear();

        if (today.getMonthValue() < birthDate.getMonthValue()) {
            idade--;
        } else {
            if (today.getMonthValue() == birthDate.getMonthValue() 
                    && today.getDayOfMonth() < birthDate.getDayOfMonth()) {
                idade--;
            }
        }

        return idade;
    }
}
