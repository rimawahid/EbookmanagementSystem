package com.ebook.model;

import com.ebook.dao.BookDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

@ManagedBean
@RequestScoped
public class Book {

    private int id;
    private String bookName, author, bookCategory, status, email, img;
    private double price;
    private Part file;
    ArrayList books;

    public ArrayList getBooks() {
        return books;
    }

    public void setBooks(ArrayList books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    

    public void addBook() throws IOException {
        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
        File savedFile = new File("D:\\IDB\\Project\\EbookManagementSystem\\web\\book\\",fileName);
        InputStream input = file.getInputStream();
        Files.copy(input, savedFile.toPath());
        //img = "img/" + fileName;
        img = "/book/" + fileName; 
        new BookDAO().save(this);
    }
}
