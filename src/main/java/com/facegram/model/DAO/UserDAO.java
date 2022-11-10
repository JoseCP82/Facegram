package com.facegram.model.DAO;

import com.facegram.interfaces.IDAO;
import com.facegram.logging.Logging;
import com.facegram.model.dataobject.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User implements IDAO<User, Integer> {

    //Atributos de UserDAO
    public Connection miCon;

    //Sentencias de UserDAO
    private String insertSQL="INSERT INTO user (id,name,password) VALUES id=?, name=?, password=?";
    private String getSQL="SELECT * FROM user WHERE id=?";
    private String getAllSQL="SELECT * FROM user";
    private String updateSQL="UPDATE user SET id=?, name=?, password=? WHERE id=?";
    private String deleteSQL="DELETE FROM user WHERE id=?";

    //Métodos de UserDAO
    @Override
    public boolean insert(User obj) {
        boolean result = false;
        try{
            PreparedStatement ps = miCon.prepareStatement(insertSQL);
            ps.setInt(1,obj.getId());
            ps.setString(2,obj.getName());
            ps.setString(3,obj.getPassword());
            ps.executeUpdate();
            result=true;
         } catch(SQLException e){
            Logging.warningLogging(e+"");
            result=false;
         }
        return result;
    }

    @Override
    public User get(Integer id) {
        User aux=null;
        try {
            PreparedStatement ps = miCon.prepareStatement(getSQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            aux=new User();
            rs.next();
            aux.setId(rs.getInt(1));
            aux.setName(rs.getString(2));
            aux.setPassword(rs.getString(3));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logging.warningLogging(e+"");
            aux=null;
        }
        return aux;
    }

    @Override
    public List getAll() {
        List<User> result = new ArrayList<User>();
        try{
            Statement st = miCon.createStatement();
            ResultSet rs = st.executeQuery(getAllSQL);
            while(rs.next()){
                User aux = new User();
                aux.setId(rs.getInt("id"));
                aux.setName(rs.getString("name"));
                aux.setPassword(rs.getString("password"));
                result.add(aux);
            }
        }catch (SQLException e){
            Logging.warningLogging(e+"");
            result=null;
        }
        return result;
    }

    @Override
    public int update(User obj) {
        int i;
        try {
            PreparedStatement ps = miCon.prepareStatement(updateSQL);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getPassword());
            ps.executeUpdate();
            i=1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logging.warningLogging(e+"");
            i=0;
        }
        return i;
    }

    @Override
    public int delete(User obj) {
        int i;
        try {
            PreparedStatement ps=miCon.prepareStatement(deleteSQL);
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            i=1;
        }catch(SQLException e) {
            Logging.warningLogging(e+"");
            i=0;
        }
        return i;
    }

}
