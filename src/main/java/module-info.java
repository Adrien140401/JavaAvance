module com.example.javaavance {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javaavance to javafx.fxml;
    exports com.example.javaavance;
}