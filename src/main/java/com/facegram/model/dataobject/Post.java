package com.facegram.model.dataobject;

import java.util.List;

public class Post {

    /**
     * Atributos de clase
     */
    private User owner;
    private List<Commnent> comments;

    /**
     * Constructor con parametros
     * @param owner Usuario que crea el post
     * @param comments Lista de comentaios que puede tener el post
     */
    public Post(User owner, List<Commnent> comments) {
        this.owner = owner;
        this.comments = comments;
    }

    /**
     * Constructor por defecto
     */
    public Post() {
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
    public List<Commnent> getComments() {
        return comments;
    }

    /**
     * Setea la lista de comentarios
     * @param comments Lista de comentarios
     */
    public void setComments(List<Commnent> comments) {
        this.comments = comments;
    }
}
