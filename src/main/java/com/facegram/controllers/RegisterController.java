package com.facegram.controllers;

import com.facegram.logging.Logging;
import com.facegram.model.DAO.UserDAO;
import com.facegram.model.dataobject.User;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.ErrorMessage;
import com.facegram.utils.message.InfoMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterController {

    /**
     * Atributos de la clase Register
     */
    private Stage stage;

    /**
     * Elementos FXML de la clase Register
     */
    @FXML private Button btnRegister;
    @FXML private Button btnLogIn;
    @FXML private Button btnClose;
    @FXML private Button btnMinimize;
    @FXML private TextField tfName;
    @FXML private TextField tfPassword;

    /**
     * Encripta cualquier cadena usando SHA256
     */
    public static String encrypt(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(s.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<b.length; i++){
                String t = Integer.toHexString(0xff & b[i]);
                if(t.length() == 1){
                    sb.append('0');
                    sb.append(t);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            Logging.warningLogging(e+"");
            return null;
        }

    }
    @FXML public void register(){
        if(!tfName.getText().equals("") && !tfPassword.getText().equals("")){
                String name = tfName.getText();
                String password = tfPassword.getText();
                encrypt(password);
                User u = new User(name,password);
                UserDAO uDAO = new UserDAO(u);
                uDAO.insert();
                if(!uDAO.get(name).equals(name)){
                    Message m = new InfoMessage("Usuario creado");
                    m.showMessage();
                }else{
                    Message m = new ErrorMessage("Los parámetros establecidos no son válidos, puebe de nuevo");
                    m.showMessage();
                }
        }else{
            Message m = new ErrorMessage("Error");
        }
    }

    /**
     * Minimiza la aplicación a la barra de tareas
     */
    @FXML private void minimizeWindow() {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.setIconified(true);
    }

    /**
     * Finaliza la aplicación
     */
    @FXML private void closeApp() {
        Message ms = new ConfirmMessage("¿Seguro que desea salir?");
        ms.showMessage();
        if(((ConfirmMessage) ms).getBt() == ButtonType.OK) {
            Logging.infoLogging("Aplicación finalizada.");
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
