package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.log.Log;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDAO extends Post implements IDAO<Post,Integer>  {

    /**
     * Consultas MySQL
     */
    private final static String INSERT = "INSERT INTO post (id_user, date, edit_Date, text) VALUES (?,?,?,?)";
    private final static String SELECTALLBYUSER = "SELECT id, date, edit_date, text FROM post WHERE id_user=?";
    private final static String SELECTALL = "SELECT id, date, edit_date, text, id_user FROM post";
    private final static String SELECTBYID = "SELECT id, id_user, date, edit_date, text FROM post WHERE id=?";
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
    public PostDAO(Post post){
        this(post.getId(),post.getText(), post.getDate(), post.getEditDate());
        this.owner = post.getOwner();
    }

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
        System.out.println(this.getOwner().getId()+" "+this.getDate()+" " + this.getText());
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
                    Log.warningLogging(e + "");
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
        Connection conn = DBConnection.getConnect();
        if(conn != null) {
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(SELECTBYID);
                ps.setInt(1, id);
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    if(rs.next()) {
                        this.id = rs.getInt("id");
                        this.date = rs.getDate("date");
                        this.editDate = rs.getDate("edit_date");
                        int id_user = rs.getInt("id_user");
                        this.owner = new UserDAO(id_user);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                Log.warningLogging(e+"");
            }
        }
        return this;
    }

    /**
     * Obtiene todos los post del usuario
     * @return Lista de post o null si no existe ninguno.
     */
    public List<Post> getAll() {
        List<Post> result = new ArrayList<Post>();
        User user = null;
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
                                rs.getDate("edit_date"));
                        p.setOwner(new User(rs.getInt("id_user")));
                        result.add(p);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                Log.warningLogging(e+"");
            }
        }
        return result;
    }

    /**
     * Obiene todos los post de un usuario
     * @param user Usuario del que queremos sus post
     * @return Lista de post
     */
    public static List<Post> getPostOfUser(User user){
        List<Post> result = new ArrayList<Post>();
        Connection conn = DBConnection.getConnect();
        if(conn != null) {
            PreparedStatement ps;
            try {
                ps = conn.prepareStatement(SELECTALLBYUSER);
                ps.setInt(1, user.getId());
                if(ps.execute()) {
                    ResultSet rs = ps.getResultSet();
                    while(rs.next()) {
                        Post post = new Post(rs.getInt("id"),
                                rs.getString("text"),
                                rs.getDate("date"),
                                rs.getDate("edit_date"));
                        result.add(post);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                Log.warningLogging(e+"");
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
                    Log.warningLogging(e + "");
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
                    Log.warningLogging(e+"");
                    result = 0;
                }
            }
        }
        return result;
    }
}