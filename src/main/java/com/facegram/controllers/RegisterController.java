package com.facegram.controllers;

import com.facegram.App;
import com.facegram.log.Log;
import com.facegram.model.DAO.UserDAO;
import com.facegram.model.dataobject.User;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.ErrorMessage;
import com.facegram.utils.message.InfoMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.MessageDigest;

public class RegisterController {

    /**
     * Atributos de la clase Register
     */
    private Stage stage;

    /**
     * Elementos FXML de la clase Register
     */
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
            Log.warningLogging(e+"");
            return null;
        }
    }
    @FXML public void register() throws IOException {
        if(!tfName.getText().equals("") && !tfPassword.getText().equals("")){
            String name = tfName.getText();
            String password = tfPassword.getText();
            encrypt(password);
            User u = new User(name,password);
            UserDAO uDAO = new UserDAO(u);
            if(uDAO.get(name).getId()==-1){
                Message m = new ConfirmMessage("El usuario no existe.\n ¿Desea crearlo?");
                m.showMessage();
                if(((ConfirmMessage)m).getBt()==ButtonType.OK){
                    uDAO.insert();
                    m = new InfoMessage("Usuario añadido");
                    m.showMessage();
                    FeedController fc =new FeedController(uDAO.get(name));
                    changeFeed();
                }
            }else{
                Message m = new InfoMessage("Sesión iniciada");
                m.showMessage();
                FeedController fc =new FeedController(uDAO.get(name));
                changeFeed();
            }
        }else{
            Message m = new ErrorMessage("Los campos no pueden estar vacíos, introduzcalos");
        }
    }

    @FXML public void changeFeed() throws IOException {
        this.stage = (Stage) this.btnClose.getScene().getWindow();
        this.stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("feed.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 480);
        Stage s = new Stage();
        s.setScene(scene);
        s.setResizable(false);
        s.initStyle(StageStyle.UNDECORATED);
        s.show();
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
            //this.chronometer.interrupt();
            //new InfoMessage("Duración de la sesión:\n"+this.chronometer.getSessionTime()).showMessage();
            Log.infoLogging("Aplicación finalizada.");
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
