package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.model.dataobject.Post;

import java.sql.Connection;
import java.util.List;

public class PostDAO extends Post implements IDAO<Post,Integer>  {

    /**
     * Atributos de clase
     */


    /**
     * Constructor con parametro Post
     * @param post Post a instanciar
     */
    public PostDAO(Post post){	super(post.getId(),post.getText(), post.getDate(), post.getEditDate());}

    /**
     * Constructor con parametro id
     * @param id Id
     */
    public PostDAO(int id){
        this.get(id);
    }

    /**
     * Constructor por defecto
     */
    public PostDAO(){
        super();
    }

    @Override
    public boolean insert(Post obj) {
        Connection conn = DBConnection.getConnect();
        return false;
    }

    @Override
    public Post get(Integer id) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public int update(Post obj) {
        return 0;
    }

    @Override
    public int delete(Post obj) {
        return 0;
    }
}
