package com.facegram.model.dataobject;

<<<<<<< HEAD:src/main/java/com/facegram/model/dataobject/Commnent.java
import java.util.Date;

public class Commnent {
=======
public class Comment {
>>>>>>> bd53c2c1830b088776c545a9e4669e4562ca6980:src/main/java/com/facegram/model/dataobject/Comment.java

    protected int id;
    protected User user;
    protected Post post;
    protected String text;
    protected Date date;

    /**
     * Constructor parametrizado
     * @param id Identificacion asignada al comentario
     * @param user Usuario que publica el comentario
     * @param post Post en el que se publica el comentario
     * @param text Texto que contiene el comentario
     * @param date Fecha de publicación del comentario
     */
    public Commnent(int id, User user, Post post, String text, Date date) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.text = text;
        this.date = date;
    }

    /**
     * Constructor por defecto
     */
    public Commnent() {
        this.id=-1;
        this.user = null;
        this.post = null;
        this.text = "";
        this.date = null;
    }

    /**
     * Obtiene el id del comentario
     * @return Id del comentario
     */
    public int getId() {
        return id;
    }

    /**
     * Setea el id del post
     * @param id Id a setear
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el Usuario del comentario
     * @return el Usuario del comentario
     */
    public User getUser() {
        return user;
    }

    /**
     * Setea el Usuario del comentario
     * @param user Usuario a setear
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene el Post del comentario
     * @return el Post del comentario
     */
    public Post getPost() {
        return post;
    }

    /**
     * Setea el Post del comentario
     * @param post Post a setear
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Obtiene el Texto del comentario
     * @return el Texto del comentario
     */
    public String getText() {
        return text;
    }

    /**
     * Setea el Texto del comentario
     * @param text Texto a setear
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Obtiene la Fecha del comentario
     * @return la Fecha del comentario
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setea la Fecha del comentario
     * @param date Fecha a setear
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
