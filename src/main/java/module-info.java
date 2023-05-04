module com.example.javaavance {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.javaavance to javafx.fxml;
    exports com.example.javaavance;
    opens com.example.javaavance.controller to javafx.fxml;
}