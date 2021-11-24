package br.com.jobem.api.commons.externals;


import br.com.jobem.api.models.FavoriteJob;
import br.com.jobem.api.models.Information;
import br.com.jobem.api.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMail {
    private JavaMailSender javaMailSender;

    @Autowired
    public JavaMail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Student student, FavoriteJob favoriteJob) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();

        Information info = favoriteJob.getJob().getCompany().getInformation();

        String message = String.format("Seu perfil combina com a vaga %s da empresa %s. \n" +
                        "Localizada na %s, CEP: %s. \n" +
                        "Para mais informações entre em contato e-mail: %s ou telefone: %s.",
                favoriteJob.getJob().getName(),
                favoriteJob.getJob().getCompany().getName(),
                info.getStreetLocation(),
                info.getZipCode(),
                info.getUser().getEmail(),
                info.getCellNumber()
        );

        mail.setTo(student.getInformation().getUser().getEmail());
        mail.setSubject(String.format("Olá, %s rolou o tão sonhado match!", student.getName()));
        mail.setText(message);

        javaMailSender.send(mail);
    }
}
