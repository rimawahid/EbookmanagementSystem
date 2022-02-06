package com.ebook.model;

import com.ebook.dao.UserDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Login {

    String email, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginAction() {
       
       boolean result = new UserDAO().login(email, password);
       
       
        if (result) {
            System.out.println("success");
            return "home";
            
        }else if(email.equals("admin@gmail.com") && password.equals("1234")){
            System.out.println("failed");
            return "admin/home";
        }else {
            System.out.println("failed");
            return "index";
        }
        //return "index";
    }
}