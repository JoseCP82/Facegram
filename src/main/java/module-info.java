module com.facegram.facegram {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.facegram to javafx.fxml;
    exports com.facegram;
}