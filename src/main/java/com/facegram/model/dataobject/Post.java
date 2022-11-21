package com.facegram.model.dataobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {

    /**
     * Atributos de clase
     */
    protected int id;
    protected String text;
    protected Date date;
    protected Date editDate;
    protected User owner;
    protected List<Comment> comments;

    public Post(int id, String text, Date date, Date editDate, User user) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.editDate = editDate;
        this.owner = user;
    }

    public Post(String text, Date date, Date editDate, User user) {
        this.id = -1;
        this.text = text;
        this.date = date;
        this.editDate = editDate;
        this.owner = user;
    }

    /**
     * Constructor parametrizado
     * @param id Identificación asignada al post
     * @param text Texto que tiene el post
     * @param date Fecha de creación del post
     * @param editDate Fecha de actualización del post
     */
    public Post(int id, String text, Date date, Date editDate) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.editDate = editDate;
    }

    /**
     * Constructor por defecto
     */
    public Post() {
        this(-1,"",null,null);
    }

    /**
     * Obtiene el id del post
     * @return Id del post
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
     * Obtiene el text o mensaje del post
     * @return Texto del posteado
     */
    public String getText() {
        return text;
    }

    /**
     * Setea el texto a postear
     * @param text Texto del post
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Obtiene la fecha de creacion del post
     * @return Fecha del post
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setea la fecha de creación del post
     * @param date Fecha a postear
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Obtiene la fecha en que se actualizó el post
     * @return Fecha actualizada del post
     */
    public Date getEditDate() {
        return editDate;
    }

    /**
     * Setea la fecha de actualización del post
     * @param editDate Fecha a setear
     */
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    /**
     * Obtiene el usuario que creó el post
     * @return Usuario
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Setea usuario que crea el post
     * @param owner Usuario
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Obtiene la lista de comentarios
     * @return Lista de comentarios
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Setea la lista de comentarios
     * @param comments Lista de comentarios
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Añade al array de Comments un nuevo comentario en el caso de que esté vacio
     * @param c Comentairio a añadir
     */
    public void addComment(Comment c) {
        if(this.comments==null) this.comments = new ArrayList<Comment>();
        this.comments.add(c);
    }

    @Override
    public String toString() {
        String result = "Publicado por: " + owner.getName()+ "\n" +
                text + "\n" +
                "Publicado el " + date + "\n";
        if(comments==null) result+="No existen comentarios.\n";
        else result+=comments;
        return result;
    }
}
