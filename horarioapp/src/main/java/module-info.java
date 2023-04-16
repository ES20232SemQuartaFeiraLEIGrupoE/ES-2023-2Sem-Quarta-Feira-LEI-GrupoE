module com.example.horarioapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.horarioapp to javafx.fxml;
    exports com.example.horarioapp;
}