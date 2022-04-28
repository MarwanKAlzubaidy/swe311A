module com.example.swe311projecta {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    opens com.example.swe311projecta to javafx.fxml;
    exports com.example.swe311projecta;
    exports com.example.swe311projecta.Controller;
    opens com.example.swe311projecta.Controller to javafx.fxml;
    exports com.example.swe311projecta.Model;
    opens com.example.swe311projecta.Model to javafx.fxml;
}
