module com.example.swe311projecta {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    opens com.example.swe311projecta to javafx.fxml;
    exports com.example.swe311projecta;
    opens com.example.swe311projecta.View.StartUp to javafx.fxml;
    opens com.example.swe311projecta.View to javafx.base;

}
