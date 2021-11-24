package br.com.jobem.api.services;

import br.com.jobem.api.commons.externals.DiscordBot;
import br.com.jobem.api.commons.externals.JavaMail;
import br.com.jobem.api.commons.externals.SlackBot;
import br.com.jobem.api.dataStructure.PilhaObj;
import br.com.jobem.api.models.FavoriteJob;
import br.com.jobem.api.models.Student;
import br.com.jobem.api.repositories.FavoriteJobRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteJobService {
    @Autowired
    private FavoriteJobRepository favoriteJobRepository;
    @Autowired
    private JavaMail javaMail;
    SlackBot slackBot = new SlackBot();
    PilhaObj<FavoriteJob> likes = new PilhaObj<>(50);

    public boolean matchFlow(FavoriteJob infosMatch) {
        try {
            int studentId = infosMatch.getStudent().getId();
            int jobId = infosMatch.getJob().getId();

            Optional<FavoriteJob> match = favoriteJobRepository.findByStudentIdAndJobId(studentId, jobId);

            if (match.isPresent()) {
                match.get().setMatch(true);

                favoriteJobRepository.save(match.get());

                Student student = match.get().getStudent();

                buildNotifications(student, match.get());

                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    private void buildNotifications(Student student, FavoriteJob infosMatch) throws Exception {
        String discordId = student.getDiscordId();

        if (discordId != null) {
            DiscordBot.sendMessage(discordId, student.getName(), infosMatch.getJob().getName());
        }

        javaMail.sendEmail(student, infosMatch);

        slackBot.sendNotification(infosMatch);
    }

    public boolean save(FavoriteJob favoriteJob) {
        try {
            int studentId = favoriteJob.getStudent().getId();
            int jobId = favoriteJob.getJob().getId();

            Optional<FavoriteJob> data = favoriteJobRepository.findByStudentIdAndJobId(studentId, jobId);

            if (!data.isPresent()) {
                favoriteJobRepository.save(favoriteJob);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeLastLike(int id) {
        try {
            List<FavoriteJob> list = favoriteJobRepository.findByStudentIdOrderByIdDesc(id);
            for (FavoriteJob like : list) {
                likes.push(like);
            }

            favoriteJobRepository.deleteById(likes.pop().getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
