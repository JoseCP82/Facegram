package com.facegram.controllers;

import com.facegram.model.DAO.CommentDAO;
import com.facegram.model.DAO.PostDAO;
import com.facegram.utils.message.ErrorMessage;
import com.facegram.utils.message.InfoMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NewCommentController extends Controller {

    /**
     * Atributos bindeados con javafx
     */
    @FXML private TextArea textComment;

    /**
     * Crea un comentario nuevo
     */
    @FXML
    private void addComment() {
        CommentDAO cDao = new CommentDAO();
        String text = textComment.getText();
        if(!text.equals("")) {
            cDao.setText(text);
            cDao.setUser(permanentUser);
            cDao.setPost(new PostDAO().get(permanentIdPost));
            if(cDao.insert()){
                new InfoMessage("Comentario publicado.").showMessage();
            }
            else{
                new ErrorMessage("No se pudo publicar el comentario.").showMessage();
            }
            textComment.setText("");
        }
        else{
            new InfoMessage("El comentario no puede estar vacío.").showMessage();
        }
    }
}
