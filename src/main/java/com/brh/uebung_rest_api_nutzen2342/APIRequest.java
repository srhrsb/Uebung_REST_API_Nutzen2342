package com.brh.uebung_rest_api_nutzen2342;

import java.util.function.Consumer;

public class APIRequest {

     private Consumer<String> onSuccessCallback;

     public void getData( String name, String firstname, Consumer<String> callback){
         onSuccessCallback = callback;


     }

     private void sendRequest( String name, String firstname ) {

         String requestURL = "https://ws-public.interpol.int/notices/v1/red?page=1&resultPerPage=200";

         //ToDO: name und nachname an Url "dran machen", falls nicht leer
         if( !name.isEmpty() ){
             requestURL +="&name="+ name;
         }

         if( !firstname.isEmpty() ){
             requestURL +="&forename="+ firstname;
         }

         try{




             



         }catch(Exception e ){
              throw new RuntimeException(e);
         }





     }
}