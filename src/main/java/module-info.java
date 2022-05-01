module com.example.swe311projecta {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    opens com.example.swe311projecta to javafx.fxml;
    exports com.example.swe311projecta;
    opens com.example.swe311projecta.ViewModel to javafx.base, javafx.fxml;
    opens com.example.swe311projecta.Controller to javafx.fxml;
    exports com.example.swe311projecta.Controller;
    
}
