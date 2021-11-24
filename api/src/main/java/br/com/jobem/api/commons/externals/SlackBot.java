package br.com.jobem.api.commons.externals;

import br.com.jobem.api.commons.Constants;
import br.com.jobem.api.models.FavoriteJob;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class SlackBot {

    public void sendMessage(JSONObject message) throws Exception {
        URL obj = new URL(Constants.SLACK_URL);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(message.toString());

        wr.flush();
        wr.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        reader.close();
    }

    public void sendNotification(FavoriteJob infosMatch) throws Exception {
        String name = infosMatch.getStudent().getName();

        JSONObject json =  new JSONObject();

        json.put("text", String.format("%s, seu perfil acabou de dar match! :heart:" +
                "\n Entre na nossa plataforma, sua vaga te espera :woman-running: :runner: ", name));

        this.sendMessage(json);
    }
}
