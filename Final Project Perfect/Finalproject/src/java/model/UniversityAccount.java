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
@Named(value = "universityAccount")
@Dependent
public class UniversityAccount {

    private String first;
    private String last;
    private String email;
    private int ID;
    private String password;
    private String userN;
    private String jobTitle;
    private String universityN;
    
    public UniversityAccount() {
    }

    public UniversityAccount(String first, String last, String email, int ID, String password, String userN, String jobTitle, String universityN) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.ID = ID;
        this.password = password;
        this.userN = userN;
        this.jobTitle = jobTitle;
        this.universityN = universityN;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUniversityN() {
        return universityN;
    }

    public void setUniversityN(String universityN) {
        this.universityN = universityN;
    }    
}
