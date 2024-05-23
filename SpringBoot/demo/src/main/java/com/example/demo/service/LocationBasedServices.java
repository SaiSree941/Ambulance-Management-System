package com.example.demo.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;

@SpringBootApplication
@RestController
public class LocationBasedServices {

    private String apiKey;

    public LocationBasedServices(String apiKey) {
        this.apiKey = apiKey;
    }

    @GetMapping("/geocode")
    public String geocode(@RequestParam String address) throws IOException {
        address = address.replaceAll(" ", "+");
        String url = "https://api.tomtom.com/search/2/geocode/" + address + ".json?key=" + apiKey;
        String response = sendHttpRequest(url);
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        return jsonResponse.getAsJsonArray("results").get(0).getAsJsonObject().get("address").getAsJsonObject()
                .get("freeformAddress").getAsString();
    }

    @GetMapping("/reverseGeocode")
    public String reverseGeocode(@RequestParam double latitude, @RequestParam double longitude) throws IOException {
        String url = "https://api.tomtom.com/search/2/reverseGeocode/" + latitude + "," + longitude + ".json?key="
                + apiKey;
        String response = sendHttpRequest(url);
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        return jsonResponse.getAsJsonArray("addresses").get(0).getAsJsonObject().get("address").getAsJsonObject()
                .get("freeformAddress").getAsString();
    }

    private String sendHttpRequest(String urlString) throws IOException {
        try {
            URI uri = URI.create(urlString);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return response.body();
            } else {
                throw new IOException("HTTP request failed with error code: " + statusCode);
            }
        } catch (InterruptedException e) {
            throw new IOException("HTTP request was interrupted", e);
        }
    }

    public static void main(String[] args) {
        String apiKey = "Fr2KNLtGCw9nislPdWrsOm1BxenP9XNX"; // Replace with your actual TomTom API key
        LocationBasedServices service = new LocationBasedServices(apiKey);
        SpringApplication.run(LocationBasedServices.class, args);
    }
}
