package com.facegram.model.dataobject;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {

    /**
     * Atributos de user
     */
    protected int id;
    protected String name;
    protected String password;
    protected List<Post> posts;
    protected List<User> followereds;
    protected List<User> followers;

    /**
     * Constructores de User por defecto y fullBuild
     */
    public User(){}
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.posts = null;
        this.followereds = null;
        this.followers = null;
    }

    /**
     * Getters & Setters de User
     * @return
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //Setters de las listas de followers y followereds
    public void setFollowers(User u){
        this.followereds=u.followereds;
    }

    public void setFollowereds(User u){
        this.followers=u.followers;
    }

    /**
     * Método toString de User
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Método equals & hashCode de User
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getName().equals(user.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
