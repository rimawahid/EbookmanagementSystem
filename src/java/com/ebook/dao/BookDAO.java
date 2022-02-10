/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebook.dao;

import com.ebook.common.ICommonInterface;
import com.ebook.model.Book;
import com.ebook.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author USER
 */
@ManagedBean(name = "bookDAO")
public class BookDAO implements ICommonInterface<Book> {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    
    public int save(Book t) {
        String sql = "insert into book_dtls (book_name, author, price, book_category, status, photo, email) values(?,?,?,?,?,?,?)";
        int status = 0;
        try {
            con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getBookName());
            ps.setString(2, t.getAuthor());
            ps.setDouble(3, t.getPrice());
            ps.setString(4, t.getBookCategory());
            ps.setString(5, t.getStatus());
            ps.setString(6, t.getImg());
            ps.setString(7, t.getEmail());
            //System.out.println(t.getName());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return status;
    }

    @Override
    public Book edit(Book t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Book t) {
        String sql = "update book_dtls set book_name= ?, author =?, price =?, book_category=?, status=?,  email=? where id = ?";
        int status = 0;
        try {
            con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getBookName());
            ps.setString(2, t.getAuthor());
            ps.setDouble(3, t.getPrice());
            ps.setString(4, t.getBookCategory());
            ps.setString(5, t.getStatus());
            //ps.setString(6, t.getImg());
            ps.setString(6, t.getEmail());
            ps.setInt(7, t.getId());
           // System.out.println("DAO" + t.getBookName());
            ps.executeUpdate();
            // System.out.println("DAO prifh" +  t.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return status;
    }

    @Override
    public int delete(Book t) {
        String sql = "delete from book_dtls where id =?";
        int status = 0;
        try {
            con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            ps.setInt(1, t.getId());
            System.out.println(t.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Book getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> getAll() {
        String sql = "select * from book_dtls";
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                Book showBook = new Book();
//                System.out.println(rs.getString("name"));
                showBook.setBookName(rs.getString("book_name"));
                showBook.setAuthor(rs.getString("author"));
                showBook.setPrice(rs.getDouble("price"));
                showBook.setBookCategory(rs.getString("book_category"));
                showBook.setStatus(rs.getString("status"));
                showBook.setImg(rs.getString("photo"));
                showBook.setEmail(rs.getString("email"));
                showBook.setId(rs.getInt("id"));
                books.add(showBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return books;
    }
    
     public List<Book> newBook(Book t) {
        String sql = "select * from book_dtls  book_category=?, status=?";
        ArrayList<Book> newbooks = new ArrayList<Book>();
        try {
            con = DBConnection.getConnect();
            ps = con.prepareStatement(sql);
            ps.setString(1, "new");
            ps.setString(2, "Active");
            System.out.println("HEllo");
            rs = ps.executeQuery();
            
            int i =1;
            while (rs.next() && i<= 4) {
                Book showBook = new Book();
                System.out.println(rs.getString("book_name"));
                showBook.setBookName(rs.getString("book_name"));
                showBook.setAuthor(rs.getString("author"));
                showBook.setPrice(rs.getDouble("price"));
                showBook.setBookCategory(rs.getString("book_category"));
                showBook.setStatus(rs.getString("status"));
                showBook.setImg(rs.getString("photo"));
                showBook.setEmail(rs.getString("email"));
                showBook.setId(rs.getInt("id"));
                newbooks.add(showBook);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newbooks;
    }

}
