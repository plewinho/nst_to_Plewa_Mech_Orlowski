module com.main.casinoapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires javafx.media;
    requires jsr250.api;

    opens com.main.casinoapp to javafx.fxml;
    exports com.main.casinoapp;
}