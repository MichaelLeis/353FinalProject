/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import model.PassHash;
import model.SignUpBean;

/**
 *
 * @author meleis
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private String username;
    private String password;
    private String pass2;
    private String email;
    private String jobTitle;
    private int attempts = 0;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    private SignUpBean login;
    private boolean logIn;

    public boolean isLogIn() {
        return logIn;
    }

    public void setLogIn(boolean logIn) {
        this.logIn = logIn;
    }

    public LoginController() {
        login = new SignUpBean();
        username = "";
        password = "";
    }

    public SignUpBean getLogin() {
        return login;
    }

    public void setLogin(SignUpBean login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

//    public String checkPassword() {
//        ProfileDAO profileDAO = new ProfileDAOImpl();
//        login = profileDAO.findByName(username);
//        attempts++;
//
//        if (login == null) {
//            if (attempts > 2) {
//                return "error3.xhtml";
//            }
//            return "error4.xhtml";
//        }
//
//        PassHash hash = new PassHash();
//        hash.hashPass(password);
//        String hashedPass = hash.hashPass(password);
//
//        if (login.getPassword().equals(hashedPass)) {
//            attempts = 0;
//            logIn = true;
//            return "LoginGood.xhtml";
//        } else {
//            if (attempts > 2) {
//                return "error3.xhtml";
//            }
//            return "LoginBad.xhtml";
//        }
//    }

    public String isAuthenticated(ComponentSystemEvent event) {
        String navi = null;

        if (logIn == false) {

            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("access-denied?faces-redirect=true");
        }
        return navi;

    }

//    public String updateProfile() {
//        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.
//
//        if (checkPasswords() == false) {
//            return "error2.1.xhtml";
//        }
//
//        int rowCount = aProfileDAO.updateProfile(login); // Doing anything with the object after this?
//        if (rowCount == 1) {
//            return "update.xhtml";
//        } else {
//            return "error.xtml";
//        }
//    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    private boolean checkPasswords() {
        if (login.getPassword().equals(pass2)) {
            return true;
        }
        return false;
    }
}
