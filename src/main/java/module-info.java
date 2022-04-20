module com.example.swe311projecta {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.example.swe311projecta to javafx.fxml;
    exports com.example.swe311projecta;

}
