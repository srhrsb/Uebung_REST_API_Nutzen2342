package com.brh.uebung_rest_api_nutzen2342;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.net.http.HttpResponse;

public class APIRequest {

     private Consumer<String> onSuccessCallback;

     public void getData( String name, String firstname, Consumer<String> callback){
         onSuccessCallback = callback;


     }


     public void sendRequest( String name, String firstname ){

         //Request Url für GET-Abfrage (Parameter in URL)
         String requestURL = "https://ws-public.interpol.int/notices/v1/red?page=1&resultPerPage=200";

         //ToDO: name und nachname an Url "dran machen", falls nicht leer
         if( !name.isEmpty() ){
             requestURL +="&name="+ name;
         }

         if( !firstname.isEmpty() ){
             requestURL +="&forename="+ firstname;
         }

         try{
           //HttpRequest asynchron, also nicht zur Laufzeit zurückgebar

             CompletableFuture<HttpResponse<String>> future; //lokale Variable ist noch null

             try( HttpClient client = HttpClient.newHttpClient() ){

                 HttpRequest request = HttpRequest.newBuilder( URI.create(requestURL) )
                         .setHeader("accept-encoding", "gzip, deflate, br, zstd")
                         .setHeader("Accept", "text/html")
                         .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36")
                         .build();

                 future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString() );
             }
             future.thenAccept( this::handleAPIResponse);

         }catch(Exception e ){
              throw new RuntimeException(e);
         }
     }

     private void handleAPIResponse( HttpResponse<String> response ){

         if(response.statusCode() == 200){

             // Antwort ok
             System.out.println( response.body() );




         }
         else{
             System.err.println("Antwort fehlerhaft: " + response.statusCode() );
         }


     }
}