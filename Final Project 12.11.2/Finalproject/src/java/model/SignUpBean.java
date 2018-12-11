/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author meleis
 */
@Named(value = "signUpBean")
@Dependent
public class SignUpBean {


private String firstName;
private String lastName;
private String userID = "";
private String password;
private String password2;
private String email;
private String secQuestion;
private String secAnswer;
    
    
    public SignUpBean() {
    }

    public SignUpBean(String firstName, String lastName, String userID, String password, String password2, String email, String secQuestion, String secAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.password = password;
        this.password2 = password2;
        this.email = email;
        this.secQuestion = secQuestion;
        this.secAnswer = secAnswer;
    }
    
    public SignUpBean(String firstName, String lastName, String userID, String password, String email, String secQuestion, String secAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.secQuestion = secQuestion;
        this.secAnswer = secAnswer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecQuestion() {
        return secQuestion;
    }

    public void setSecQuestion(String secQuestion) {
        this.secQuestion = secQuestion;
    }

    public String getSecAnswer() {
        return secAnswer;
    }

    public void setSecAnswer(String secAnswer) {
        this.secAnswer = secAnswer;
    }
    
    
}
