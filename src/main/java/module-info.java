module com.facegram.facegram {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;


    opens com.facegram to javafx.fxml;
    opens com.facegram.connection to java.xml.bind;
    exports com.facegram.connection;
    //opens com.facegram.model.DAO;
    exports com.facegram;
}