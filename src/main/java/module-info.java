module com.example.demoapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.demoapp to javafx.fxml;
    exports com.example.demoapp;
}