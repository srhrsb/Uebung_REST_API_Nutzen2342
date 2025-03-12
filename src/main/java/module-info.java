module com.brh.uebung_rest_api_nutzen2342 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.brh.uebung_rest_api_nutzen2342 to javafx.fxml;
    exports com.brh.uebung_rest_api_nutzen2342;
}