package com.facegram.model.DAO;

import com.facegram.interfaces.IDAO;
import com.facegram.model.dataobject.User;

import java.util.List;

public class UserDAO extends User implements IDAO<User, Integer> {

    //Sentencias de UserDAO
    private String insertSQL="INSERT INTO user (id,name,password) VALUES id=?, name=?, password=?";
    private String getSQL="SELECT * FROM user WHERE id=?";
    private String getAllSQL="SELECT * FROM user";
    private String updateSQL="UPDATE user SET id=?, name=?, password=? WHERE id=?";
    private String deleteSQL="DELETE FROM user WHERE id=?";

    //Métodos de UserDAO
    /*@Override
    public boolean insert(User obj) {
        boolean result = false;
        try{
            PreparedStatement ps = ¿?.prepareStatement(insertSQL);
            ps.setInt(1,obj.getId());
            ps.setString(2,obj.getName());
            ps.setString(3,obj.getPassword());
            ps.executeUpdate();
            result=true
         } catch(SQLException e={
            Loging.¿?
         }
        return result;
    }

    @Override
    public Object get(User id) {
        return null;
    }

    @Override
    public List getAll() {
        Collection<User> result = new ArrayList<User>();
        try{
            Statement st = ¿?.createStatement();
            ResultSet rs = st.executeQuery(getAllSQL);
            while(rs.next()){
                User aux = new User();
                aux.setId();
                aux.setName();
                aux.setPassword();
                aux.set
            }
        }
        return result;
    }

    @Override
    public int update(User obj) {
        return 0;
    }

    @Override
    public int delete(User obj) {
        return 0;
    }*/
}
