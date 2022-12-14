package com.facegram.model.dataobject;

import java.util.ArrayList;
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
    public User(){
        this(-1,"","");
    }
    public User(int id) {
        this.id = id;
        this.name = null;
        this.password = null;
        this.posts = null;
        this.followereds = null;
        this.followers = null;
    }
    public User(String name, String password) {
        this.id = -1;
        this.name = name;
        this.password = password;
        this.posts = null;
        this.followereds = null;
        this.followers = null;
    }
    public User(int id, String name, String password){
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
    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void addPosts(Post p){
        if(this.posts==null) {
            this.posts=new ArrayList<Post>();
            this.posts.add(p);
        }
    }
    public List<User> getFollowereds() {
        return followereds;
    }
    public void setFollowereds(User u){
        this.followers=u.followers;
    }
    public void addFollowereds(User u){
        if(this.followereds==null) {
            this.followereds=new ArrayList<User>();
            this.followereds.add(u);
        }
    }
    public List<User> getFollowers() {
        return followers;
    }
    public void setFollowers(User u){
        this.followereds=u.followereds;
    }
    public void addFollowers(User u){
        if(this.followers==null) {
            this.followers=new ArrayList<User>();
            this.followers.add(u);
        }
    }

    /**
     * M??todo toString de User
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * M??todo equals & hashCode de User
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
