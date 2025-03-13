package com.brh.uebung_rest_api_nutzen2342;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class APIRequest {

    private Consumer<String> onSuccessCallback;

    public void getData(String name, String firstName, Consumer<String> callback) {
        onSuccessCallback = callback;
    }

    public void sendRequest(String name, String firstName) {

        //Request URL für GET-Abfrage (Parameter in URL)
        String requestURL = "https://ws-public.interpol.int/notices/v1/red?page=1&resultPerPage=200";
        if (!name.isEmpty()) {

            requestURL += "&name=" + name;
        }
        if (!firstName.isEmpty()) {
            requestURL += "&forename=" + firstName;
        }

        try {
            //HttpRequest asynchron, also nicht zur Laufzeit returnbar
            CompletableFuture<HttpResponse<String>> future; //lokale Variable ist noch null
            try(HttpClient client = HttpClient.newHttpClient()) {
                HttpRequest request = HttpRequest.newBuilder(URI.create(requestURL))
                        .setHeader("accept-encoding", "gzip, deflate, br")
                        .setHeader("Accept", "text/html")
                        .setHeader("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36")

                        .build();
                future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            }

            future.thenAccept(this::handleAPIResponse);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void handleAPIResponse(HttpResponse<String> response) {
        if (response.statusCode() == 200) { //Antwort ok
            System.out.println(response.body());

        } else {
            System.err.println("Antowrt fehlgeschlagen: " + response.statusCode());
        }
    }


}
