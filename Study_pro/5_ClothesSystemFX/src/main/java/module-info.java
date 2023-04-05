module com.example.clothessystemfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clothessystemfx to javafx.fxml;
    exports com.example.clothessystemfx;
}