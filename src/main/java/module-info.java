module com.facegram.facegram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;


    opens com.facegram to javafx.fxml;
    //opens com.facegram.model.DAO;
    exports com.facegram;
}