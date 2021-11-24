package br.com.jobem.api.commons;

import br.com.jobem.api.models.FavoriteJob;
import br.com.jobem.api.repositories.FavoriteJobRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommuniqueBuilder {
    @Autowired
    private FavoriteJobRepository favoriteJobRepository;

    public List<CommuniqueModel> getCommunications(int id) {
        try {
            List<FavoriteJob> matches = favoriteJobRepository.findByStudentIdAndMatchIsTrue(id);
            List<CommuniqueModel> communicationBodies = new ArrayList<>();

            for (FavoriteJob match : matches) {
                CommuniqueModel communication = new CommuniqueModel();

                communication.setHeader(String.format("Olá %s! Rolou o tão sonhado match!",
                        match.getStudent().getName()));

                communication.setBody(String.format("Seu perfil combina com a vaga %s, da empresa %s, localizada na %s, CEP: %s. " +
                                "Para mais informações entre em contato e-mail: %s e telefone: %s",
                        match.getJob().getName(),
                        match.getJob().getCompany().getName(),
                        match.getJob().getCompany().getInformation().getStreetLocation(),
                        match.getJob().getCompany().getInformation().getZipCode(),
                        match.getJob().getCompany().getInformation().getUser().getEmail(),
                        match.getJob().getCompany().getInformation().getPhoneNumber()));

                communicationBodies.add(communication);
            }

            return communicationBodies;
        } catch (Exception e) {
            return null;
        }
    }
}
