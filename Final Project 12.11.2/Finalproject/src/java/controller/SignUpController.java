/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAOImpl;
import dao.ProfileDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.StudentAccount;
import model.UniversityAccount;

/**
 *
 * @author meleis
 */
@SessionScoped
@ManagedBean
public class SignUpController {

    private String response;
    private UniversityAccount univModel;
    private StudentAccount studentModel;
    private String validId = "";
    private String[] images = {"ninja.jfif", "robot.jfif", "dog.jfif", "panda.jfif", "alien.png"};
    private int icon;

    public SignUpController() {
        univModel = new UniversityAccount();
        studentModel = new StudentAccount();
    }

    public StudentAccount getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentAccount studentModel) {
        this.studentModel = studentModel;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UniversityAccount getUnivModel() {
        return univModel;
    }

    public void setUnivModel(UniversityAccount univModel) {
        this.univModel = univModel;
    }

    public String getValidId() {
        return validId;
    }

    public void setValidId(String validId) {
        this.validId = validId;
    }

    public String createUnivProfile() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.

        if (checkUniversityUName() == false) {
            return "error.xhtml";
        }

//        if(checkPasswords() == false){
//            return "error2.xhtml";
//        }
        int newID = aProfileDAO.getNewID();
        univModel.setID(newID);

        int rowCount = aProfileDAO.createUnivAccount(univModel); // Doing anything with the object after this?
        if (rowCount == 1) {
            //theModel2.sendEmail(theModel);
            LoginController newLogin = new LoginController();
            newLogin.changeID(newID);
            newLogin.clearPage();
            return newLogin.showUnivHP();
        } else {
            return "searchResponse.xhtml";
        }
    }

    public String createStudentProfile() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.

        if (checkStudentUName() == false) {
            return "error.xhtml";
        }

//        if(checkPasswords() == false){
//            return "error2.xhtml";
//        }
        int newID = aProfileDAO.getNewID();
        studentModel.setID(newID);

        int rowCount = aProfileDAO.createStudentAccount(studentModel); // Doing anything with the object after this?
        if (rowCount == 1) {
            //theModel2.sendEmail(theModel);
            LoginController newLogin = new LoginController();
            newLogin.clearPage();
            newLogin.changeID(newID);
            return newLogin.showStudentHP();
        } else {
            return "searchResponse.xhtml";
        }
    }
//    
//    private boolean checkPasswords () {
//        if (theModel.getPassword().equals(theModel.getPassword2())){
//            return true;
//        }
//        return false;
//    }
//    

    public boolean checkUniversityUName() {
        String[] checkID = null;
        ProfileDAO profileDAO = new ProfileDAOImpl();
        checkID = profileDAO.findUserIDs();

        for (int i = 0; i < checkID.length; i++) {
            if (univModel.getUserN().equals(checkID[i])) {
                validId = "Invalid User ID";
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkStudentUName() {
        String[] checkID = null;
        ProfileDAO profileDAO = new ProfileDAOImpl();
        checkID = profileDAO.findUserIDs();

        for (int i = 0; i < checkID.length; i++) {
            if (studentModel.getUserN().equals(checkID[i])) {
                validId = "Invalid User ID";
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
