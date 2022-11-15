package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.model.dataobject.Comment;
import com.facegram.model.dataobject.Post;
import com.facegram.model.dataobject.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAO extends Comment implements IDAO<Comment, Integer> {

    private final static String INSERT="INSERT INTO comment (text) VALUES (?))";
    private final static String SELECTBYID="SELECT * FROM comment WHERE id=?";
    private final static String SELECTALL="SELECT * FROM comment WHERE id_user=?";
    private final static String DELETE="DELETE FROM comment WHERE id=?";

    public CommentDAO(int id, User user, Post post, String text, Date date) {
        super(id, user, post, text, date);
    }
    public CommentDAO() { super(); }



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
                    e.printStackTrace();//????
                }
            }
        }
        return result;
    }

    @Override
    public Comment get(Integer id) { //?????
        return null;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> result = new ArrayList<Comment>();
        Connection conn = DBConnection.getConnect();
        if(conn!=null){
            PreparedStatement ps;
            try{
                ps = conn.prepareStatement(SELECTALL);
                if(ps.execute()){
                    ResultSet rs = ps.getResultSet();
                    while(rs.next()){
                        Comment c = new Comment(rs.getInt("id"),
                                rs.getString("Post"));
                        result.add(c);
                    }
                    rs.close();
                }
                ps.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int update() {
        //Los comentarios no pueden ser actualizados
        return 0;
    }

    @Override
    public int delete() {
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
                } catch (SQLException e) {
                    e.printStackTrace();//???
                }
            }
        }
        return 0;
    }
}
