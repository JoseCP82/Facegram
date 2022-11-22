package com.facegram.controllers;

import com.facegram.model.DAO.PostDAO;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;
import com.facegram.utils.message.ConfirmMessage;
import com.facegram.utils.message.ErrorMessage;
import com.facegram.utils.message.InfoMessage;
import com.facegram.utils.message.Message;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewPostController extends Controller {

    @FXML
    private TextArea txtContent;

    @FXML
    private void publishPost() {
        Message ms = new ConfirmMessage("¿Desea confirmar la publicación?");
        ms.showMessage();
        if(((ConfirmMessage) ms).getBt() == ButtonType.OK) {
            if(!this.txtContent.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar calendar = Calendar.getInstance();
                Date dateNow = calendar.getTime();
                User u = permanentUser;
                PostDAO pdao = new PostDAO(new Post(this.txtContent.getText(), dateNow, dateNow, permanentUser));
                if(pdao.insert()){
                    new InfoMessage("Has realizado una nueva publicación!!!").showMessage();
                    txtContent.setText("");
                    closePublishWindow();
                }
                else {
                    new ErrorMessage("Ocurrió un error inesperado.").showMessage();
                }
            }
            else {
                new InfoMessage("No ha añadido ningún contenido a la publicación!!!").showMessage();
            }
        }
    }

    @FXML
    private void closePublishWindow()  {

    }
}
