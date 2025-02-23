package tr.edu.ozyegin.cs101.wordlesolver;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DictionaryLookup {

    public static String getDefinition(String word) {
        try {
            // Build the request URL
            String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

            // Create HttpClient and request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            // Send a synchronous request
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Parse the JSON
            String jsonResponse = response.body();
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class);

            if (jsonArray.size() > 0) {
                JsonObject wordData = jsonArray.get(0).getAsJsonObject();
                // (Optional) fetch the actual word from the response
                String actualWord = wordData.get("word").getAsString();

                JsonArray meanings = wordData.getAsJsonArray("meanings");
                if (meanings != null && meanings.size() > 0) {
                    JsonObject firstMeaning = meanings.get(0).getAsJsonObject();
                    JsonArray definitions = firstMeaning.getAsJsonArray("definitions");
                    if (definitions != null && definitions.size() > 0) {
                        JsonObject firstDefinition = definitions.get(0).getAsJsonObject();
                        String definition = firstDefinition.get("definition").getAsString();

                        // Return something like: "Word => Definition"
                        return actualWord + " => " + definition;
                    }
                }
            }

            return "No definition found for \"" + word + "\".";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving definition for \"" + word + "\".";
        }
    }
}
