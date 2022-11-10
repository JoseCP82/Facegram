package com.facegram.connection;

import com.facegram.logging.Logging;
import com.facegram.utils.message.ErrorMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    /**
     * Atributos de clase
     */
    private static Connection con;
    private static DBConnection _newInstance;

    /**
     * Metodo para realizar la conexion
     */
    private DBConnection() {
        try {
            DataConnection dc = load();
            con= DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(), dc.getUsername(), dc.getPassword());
        } catch (SQLException e) {
            new ErrorMessage("No se pudo crear la conexion").showMessage();
            Logging.warningLogging(e+"");
            con=null;
        }
    }

    /**
     * Metodo que realiza la instancia de la clase
     * @return Devuelve el objeto con inicializado
     */
    public static Connection getConnect() {
        if(_newInstance==null) {
            _newInstance=new DBConnection();
        }
        return con;
    }

    /**
     * Obtiene de un archivo xml los datos para necesario para realizar la conexion con la bbdd
     * @return Objeto con los datos para realizar la conexion
     */
    public DataConnection load() {
        DataConnection dc = new DataConnection();
        JAXBContext context;
        try {
            context=JAXBContext.newInstance(DataConnection.class);
            Unmarshaller um = context.createUnmarshaller();
            dc = (DataConnection) um.unmarshal(Connection.class.getResource("/connectiondata/connectionData.xml"));
        } catch (JAXBException e) {
           Logging.warningLogging(e+"");
        }
        return dc;
    }
}
