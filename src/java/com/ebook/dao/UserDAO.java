package com.ebook.dao;

import com.ebook.common.ICommonInterface;
import com.ebook.model.User;
import com.ebook.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements ICommonInterface<User> {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public int save(User t) {
        String sql = "insert into user (name, email, phone, password) values(?,?,?,?)";
        int status = 0;
        try {
            con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getName());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPhone());
            ps.setString(4, t.getPassword());
            //System.out.println(t.getName());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return status;
    }
    
    public boolean login(String email, String password){
        
//        boolean f =false;
        String sql = "select * from user where email =? and password =?";
        try {
             con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
//            ps.setString(3, name);
          
            //System.out.println(email);
            //System.out.println(password);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                con.close();
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
       
    }

    @Override
    public User edit(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
