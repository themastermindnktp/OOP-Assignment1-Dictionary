package extension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;

public class Translator
{
    public static String parseResult(String inputJson) throws Exception {
        JSONArray jsonArray1     = new JSONArray(inputJson);
        JSONArray jsonArray2    = (JSONArray) jsonArray1.get(0);
        JSONArray jsonArray3    = (JSONArray) jsonArray2.get(0);

        return jsonArray3.get(0).toString();
    }

    public static String Translate(String language1, String language2, String string) throws Exception {

        String urlString = "https://translate.googleapis.com/translate_a/single?"+
                "client=gtx&"+
                "sl=" + language1 +
                "&tl=" + language2 +
                "&dt=t&q=" + URLEncoder.encode(string, "UTF-8");

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        StringBuilder response;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null)
                response.append(inputLine);
        }

        return parseResult(response.toString());
    }

}






