package com.facegram.model.DAO;

import com.facegram.connection.DBConnection;
import com.facegram.interfaces.IDAO;
import com.facegram.logging.Logging;
import com.facegram.model.dataobject.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends User implements IDAO<User, Integer> {

    /**
     * Atributos de UserDAO
     */

    private Connection miCon;

    public UserDAO(User u){
        super(u.getId(),u.getName(),u.getPassword());
        this.miCon = DBConnection.getConnect();
        System.out.println("Hola");
    }

    /**
     * Sentencias de UserDAO
     */
    private String insertSQL="INSERT INTO user (id,name,password) VALUES (?,?,?))";
    private String getSQL="SELECT * FROM user WHERE id=?";
    private String getAllSQL="SELECT * FROM user";
    private String updateSQL="UPDATE user SET id=?, name=?, password=? WHERE id=?";
    private String deleteSQL="DELETE FROM user WHERE id=?";

    /**
     * Mátodo que crea un User en la base de datos
     * @param obj Objeto a insertar
     * @return true o false si lo inserta o no
     */
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

    /**
     * Método que busca un User por su id en la base de datos
     * @param id Id del objeto a buscar
     * @return el User o null si lo ha encontrado o no
     */
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

    /**
     * Método que busca todos los Users de la base de datos
     * @return la lista de Users o null si los ha encontrado o no
     */
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

    /**
     * Método que edita los campos de la tabla User en la base de datos
     * @param obj Objeto a modificar
     * @return 1 o 0 si los ha editado o no
     */
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

    /**
     * Método que borra la tabla User de la base de datos
     * @param obj Objeto a modificar
     * @return 1 o 0 si la ha borrado o no
     */
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
