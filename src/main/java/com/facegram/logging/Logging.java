package com.facegram.logging;

import com.facegram.utils.message.ErrorMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Logging {

    /**
     * Atributos de clase
     */
    static Logger logger = Logger.getLogger(Logging.class.getName());

    /**
     * Genera el log con nivel Info y muestra la información
     * @param message Mensaje a mostrar
     */
    public static void infoLogging(String message) {
        //saveLog();
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO,message);
        //logger.info(message);
    }

    /**
     * Genera el log con nivel Warning y muestra la información
     * @param message Mensaje a mostrar
     */
    public static void warningLogging(String message) {
        //saveLog();
        logger.setLevel(Level.WARNING);
        logger.log(Level.WARNING,message);
       //logger.info(message);
    }

    private static void saveLog() {
        try {
            InputStream configFile=Logging.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
        }catch(SecurityException | IOException | NullPointerException e) {
            //new ErrorMessage("Logging system not initialized").showMessage();
            System.out.println("Error en archivo log");
        }
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }
}
