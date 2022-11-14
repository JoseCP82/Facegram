package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.logging.Logging;
import com.facegram.model.dataobject.Post;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class PostDAO extends Post implements IDAO<Post,Integer>  {

    /**
     * Consultas MySQL
     */
    private final static String INSERT = "INSERT INTO post (id_user, date, edit_Date, text) VALUES (?,?,?,?)";

    /**
     * Constructor parametrizado
     * @param id Id del post
     * @param text texto del post
     * @param date fecha de creacion del post
     * @param editDate fecha de edicion del post
     */
    public PostDAO(int id, String text, Date date, Date editDate){ super(id, text, date, editDate); }

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

    /**
     * Inserta un nuevo post en la base de datos
     * @param obj Objeto a insertar
     * @return True o false si la operacion se realizó con exito o no
     */
    @Override
    public boolean insert(Post obj) {
        boolean result = false;
        if(id==-1) {
            Connection conn = DBConnection.getConnect();
            if (conn != null) {
                try {
                    PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, obj.getOwner().getId());
                    ps.setObject(2, obj.getDate());
                    ps.setObject(3, obj.getEditDate());
                    ps.setString(4, obj.getText());
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        this.id=rs.getInt(1);
                    }
                    ps.close();
                    rs.close();
                    result = true;
                } catch (SQLException e) {
                    Logging.warningLogging(e + "");
                    result = false;
                }
            }
        }
        return result;
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
