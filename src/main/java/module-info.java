module com.example.autoecole {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.autoecole.models to javafx.base;
    opens com.example.autoecole to javafx.fxml;
    exports com.example.autoecole;
}