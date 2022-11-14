package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.model.dataobject.Comment;

import java.sql.*;
import java.util.List;

public class CommentDAO extends Comment implements IDAO<Comment, Integer> {

    private final static String INSERT="INSERT INTO comment (text) VALUES (?))";
    private String getSQL="SELECT * FROM comment WHERE id=?";
    private String getAllSQL="SELECT * FROM comment WHERE id_user=?";
    private String deleteSQL="DELETE FROM comment WHERE id=?";

    @Override
    public boolean insert(Comment obj) {
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
                    e.printStackTrace();//Aquí va otra cosa
                }
            }
        }
        return result;
    }

    @Override
    public Comment get(Integer id) {

        return null;
    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public int update(Comment obj) {
        return 0;
    }

    @Override
    public int delete(Comment obj) {
        return 0;
    }
}
