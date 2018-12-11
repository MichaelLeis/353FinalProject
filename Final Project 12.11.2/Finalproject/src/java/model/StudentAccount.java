/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author meleis
 */
@Named(value = "studentAccount")
@Dependent
public class StudentAccount {

    private String first;
    private String last;
    private String email;
    private int ID;
    private String password;
    private String userN;
    private int icon;
    private String fullName;
    
    public StudentAccount() {
    }

    public StudentAccount(String first, String last, String email, int ID, String password, String userN, int icon) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.ID = ID;
        this.password = password;
        this.userN = userN;
        this.icon = icon;
    }

    public String getFullName() {
        fullName = first + " " + last;
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserN() {
        return userN;
    }

    public void setUserN(String userN) {
        this.userN = userN;
    } 
}
