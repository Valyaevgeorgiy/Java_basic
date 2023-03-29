module com.example.speedometerfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.speedometerfx to javafx.fxml;
    exports com.example.speedometerfx;
}