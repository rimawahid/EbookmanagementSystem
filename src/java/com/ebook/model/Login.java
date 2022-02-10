package com.ebook.model;

import com.ebook.dao.UserDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
//@RequestScoped
@SessionScoped
public class Login {

    String name, email, password, msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
            

    public String loginAction() {

        boolean result = new UserDAO().login(email, password);

        if (result) {
            
            // System.out.println("success");
            return "home";

        } else if (email.equals("admin@gmail.com") && password.equals("1234")) {
            //System.out.println("failed");
            
            setName("Admin");
            clear();
            return "admin/home";
        } else {
            setMsg("Email & Password Invalid");
            
            //System.out.println("failed");
            return "login";
        }
        
        //return "index";
    }
    
    public void clear(){
        setEmail("");
        setPassword("");
    }
}
