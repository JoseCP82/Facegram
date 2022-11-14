package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.logging.Logging;
import com.facegram.model.dataobject.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAO extends Post implements IDAO<Post,Integer>  {

    /**
     * Consultas MySQL
     */
    private final static String INSERT = "INSERT INTO post (id_user, date, edit_Date, text) VALUES (?,?,?,?)";
    private final static String SELECTALL = "SELECT id, date, edit_date, text FROM Post";

    private final static String UPDATE = "UPDATE post SET edit_date=?, text=? WHERE id=?";
    private final static String DELETE = "DELETE FROM post WHERE id=?";

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
     * @return True o false si la operación se realizó con éxito o no
     */
    @Override
    public boolean insert() {
        boolean result = false;
        if(id==-1) {
            Connection conn = DBConnection.getConnect();
            if (conn != null) {
                try {
                    PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, this.getOwner().getId());
                    ps.setObject(2, this.getDate());
                    ps.setObject(3, this.getEditDate());
                    ps.setString(4, this.getText());
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

    /**
     * Obtiene un post dado por su id
     * @param id Id del objeto a buscar
     * @return Post o null si no existe
     */
    @Override
    public Post get(Integer id) {
        return null;
    }

    /**
     * Obtiene todos los post del usuario
     * @return Lista de post o null si no existe ninguno.
     */
    @Override
    public List<Post> getAll() {
        List<Post> result = new ArrayList<Post>();
        Connection conn = DBConnection.getConnect();
        if(conn != null) {
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(SELECTALL);
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while(rs.next()) {
                        Post p = new Post(rs.getInt("id"),
                                rs.getString("text"),
                                rs.getDate("date"),
                                rs.getDate("editDate"));
                        result.add(p);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Actualiza el post
     * @return 1 o 0 si la opoeración se realizó con éxito o no
     */
    @Override
    public int update() {
        int result = 0;
        if(id==-1) {
            Connection conn = DBConnection.getConnect();
            if (conn != null) {
                try {
                    PreparedStatement ps = conn.prepareStatement(UPDATE);
                    ps.setObject(1, this.getEditDate());
                    ps.setString(2, this.getText());
                    ps.executeUpdate();
                    ps.close();
                    result = 1;
                } catch (SQLException e) {
                    Logging.warningLogging(e + "");
                    result = 0;
                }
            }
        }
        return result;
    }

    /**
     * Elimina un post
     * @return 1 o 0 si la opoeración se realizó con éxito o no
     */
    @Override
    public int delete() {
        int result = 0;
        if(id!=-1) {
            Connection conn = DBConnection.getConnect();
            if(conn != null) {
                try {
                    PreparedStatement ps = conn.prepareStatement(DELETE);
                    ps.setInt(1, this.id);
                    if(ps.executeUpdate()==1) {
                        this.id=-1;
                    }
                    ps.close();
                    result = 1;
                } catch (SQLException e) {
                    e.printStackTrace();
                    result = 0;
                }
            }
        }
        return result;
    }
}