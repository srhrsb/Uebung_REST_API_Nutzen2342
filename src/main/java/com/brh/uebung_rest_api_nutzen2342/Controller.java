package com.brh.uebung_rest_api_nutzen2342;



import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        APIRequest apiRequest = new APIRequest();
        apiRequest.sendRequest("M", "G");
    }
}