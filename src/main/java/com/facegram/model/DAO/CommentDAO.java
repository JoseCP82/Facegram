package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.controllers.PostController;
import com.facegram.interfaces.IDAO;
import com.facegram.log.Log;
import com.facegram.model.dataobject.Comment;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAO extends Comment implements IDAO<Comment, Integer> {

    /**
     * Consultas MySQL
     */
    private final static String INSERT="INSERT INTO comment (text) VALUES (?))";
    private final static String SELECTBYPOST="SELECT comment.id, user.name, comment.text, comment.fecha FROM comment JOIN post ON post.id = comment.id_post JOIN user ON comment.id_user = user.id WHERE id_post=?";
    private final static String DELETE="DELETE FROM comment WHERE id=?";

    /**
     * Constructor parametrizado
     * @param id Id del Comentario
     * @param text Texto del Comentario
     * @param date Fecha de creación del comentario
     */
    public CommentDAO(int id, String text, Date date) {
        super(id, text, date);
    }


    public CommentDAO(int id, User user, String text, Date date) {
        super(id, user, text, date);
    }
    /**
     *Consturctor con parámetro Comment
     * @param comment Comment a instanciar
     */
    public CommentDAO(Comment comment) {
        this(comment.getId(),comment.getText(),comment.getDate());
        this.post = comment.getPost();
    }

    /**
     * Constructor con parámetro id
     * @param id Id
     */
    public CommentDAO(int id){ this.get(id); }

    /**
     * Constructor por defecto
     */
    public CommentDAO() { super(); }

    /**
     * Inserta un nuevo comentario en la base de datos
     * @return True o false si la operación se realizón con éxito
     */
    @Override
    public boolean insert() {
        boolean result = false;
        if(id==-1) {
            Connection conn = DBConnection.getConnect();
            if (conn != null) {
                PreparedStatement ps;
                try {
                    ps = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,this.text);
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        this.id = rs.getInt(1);
                    }
                    ps.close();
                    rs.close();
                } catch (SQLException e) {
                    Log.warningLogging(e+"");
                }
            }
        }
        return result;
    }

    /**
     * Método de obtención de un comentario, en este caso
     * es innecesario para el programa
     * @param id Id del objeto a buscar
     * @return
     */
    @Override
    public Comment get(Integer id) {
        return null;
    }

    /**
     * Obtiene todos los comentarios asociados a un post
     * @param post Post al cuál estan asociados los comentarios
     * @return Lista de comentarios
     */
    public List<Comment> getCommentsofPost(Post post) {
        List<Comment> result = new ArrayList<Comment>();
        Connection conn = DBConnection.getConnect();
        User u = new User();
        System.out.println("Aquí funciona");
        if(post.getId()!=0){
            System.out.println(post.getId());
            System.out.println("Aqui está la id");
        } else {
            System.out.println("Recibe null la id del post");
        }
        if(conn!=null){
            PreparedStatement ps;
            try{
                ps = conn.prepareStatement(SELECTBYPOST);
                ps.setInt(1, post.getId());

                if(ps.execute()){
                    ResultSet rs = ps.getResultSet();
                    while(rs.next()){
                        Comment c = new Comment(rs.getInt("id"),
                                (User) rs.getObject("name"),
                                rs.getString("text"),
                                rs.getDate("date")
                                );
                        result.add(c);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e){
                Log.warningLogging(e+"");
            }
        }
        return result;
    }

    /**
     * Método para actualizar los comentarios,
     * en este caso es innecesario
     * @return
     */
    @Override
    public int update() {
        //Los comentarios no pueden ser actualizados
        return 0;
    }

    /**
     * Elimina un comentario
     * @return 1 o 0 si se eliminó con éxito o no
     */
    @Override
    public int delete() {
        int result = 0;
        if (id!=-1){
            Connection conn = DBConnection.getConnect();
            if (conn != null) {
                PreparedStatement ps;
                try {
                    ps = conn.prepareStatement(DELETE);
                    ps.setInt(1,this.id);
                    if(ps.executeUpdate()==1){
                        this.id = -1;
                    }
                    ps.close();
                    result = 1;
                } catch (SQLException e) {
                    Log.warningLogging(e+"");
                }
            }
        }
        return result;
    }
}
