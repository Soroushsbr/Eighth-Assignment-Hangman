package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;



public class RandWord {

    public String giveWord(){
        String apiUrl = "https://api.api-ninjas.com/v1/randomword";
        String apiKey = "DxPDkxjUL6bpYDurOQZwiw==1IjnRq8i7CSBVRPQ";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", apiKey);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(connection.getInputStream());
                String response = scanner.nextLine();
                scanner.close();
                JSONObject jsonData = new JSONObject(response);
                return jsonData.getString("word");
            } else {
                System.err.println("Error: " + connection.getResponseCode());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWord(String topic){
        String urlToRead = "https://api.datamuse.com/words?rel_trg=" + topic; // Replace with your desired URL

        try {
            URL url = new URL(urlToRead);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method (GET by default)
            connection.setRequestMethod("GET");

            // Read the response content
            String line;
            String data = "";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                while ((line = reader.readLine()) != null) {
                    data = data + line;
                }
            }
            data = data.substring(1 , data.length() - 1);
            String[] wordsList = data.split("},");
            Random randNum = new Random();
            String chosenData = wordsList[randNum.nextInt(wordsList.length)];
            System.out.println(chosenData);
            JSONObject jsonData = new JSONObject(chosenData + "}");
            // Close the connection
            connection.disconnect();
            return jsonData.getString("word");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
