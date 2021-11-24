package br.com.jobem.api.commons.externals;

import br.com.jobem.api.commons.Constants;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class DiscordBot {
    public static void sendMessage(String userId, String userName, String jobName) throws LoginException {
        JDA jda = JDABuilder.createDefault(Constants.DISCORD_TOKEN)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .build();

        String message = String.format("%s, seu perfil acabou de dar match com a vaga %s! :heart: \n" +
                "Entre na nossa plataforma, sua vaga te espera :woman_running: :man_running:",
                userName, jobName);

        jda.retrieveUserById(userId).queue(user -> user.openPrivateChannel().
                queue(channel -> channel.sendMessage(message).queue()));

    }
}