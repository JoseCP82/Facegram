package com.facegram.controllers;

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

public class RegisterController extends Controller {

    /**
     * Atributos de la clase Register
     */
    private Stage stage;

    /**
     * Elementos FXML de la clase Register
     */
    @FXML private Button btnClose;
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

    /**
     * Permite al usuario registrarse si no existe o iniciar sesión si ya existe
     * @throws IOException
     */
    @FXML public void register() throws IOException {
        String name = tfName.getText();
        String password = tfPassword.getText();
        if(!name.equals("") && !password.equals("")){
            User u = new User(name,password);
            UserDAO uDAO = new UserDAO(u);
            uDAO.setPassword(encrypt(password));
            if(uDAO.get(name).getId()==-1){
                Message m = new ConfirmMessage("El usuario no existe.\n ¿Desea crearlo?");
                m.showMessage();
                if(((ConfirmMessage)m).getBt()==ButtonType.OK){
                    uDAO.insert();
                    new InfoMessage("Usuario añadido").showMessage();
                    changeFeed();
                }
            }else{
                if(uDAO.get(name).getName().equals(name) && uDAO.get(name).getPassword().equals(password)){
                    Message m = new InfoMessage("Sesión iniciada");
                    m.showMessage();
                    changeFeed();
                }else{
                    new ErrorMessage("El nombre o contraseña son incorrectos").showMessage();
                }
            }
            permanentUser=u;
        }else{
            new ErrorMessage("Los campos no pueden estar vacíos, introduzca información").showMessage();
        }
    }

    /**
     * Redirige a la pantalla del Feed
     * @throws IOException
     */
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
            Log.infoLogging("Aplicación finalizada.");
            this.stage = (Stage) this.btnClose.getScene().getWindow();
            this.stage.close();
        }
    }
}
