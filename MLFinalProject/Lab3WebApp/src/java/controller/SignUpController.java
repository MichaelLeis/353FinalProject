/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAOImpl;
import dao.ProfileDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.SignUpBean;
import model.MailBean;

/**
 *
 * @author meleis
 */
@ManagedBean
@SessionScoped
public class SignUpController {

    private String response;
    private SignUpBean theModel;
    private MailBean theModel2;
    private String validId = "";
    
    public SignUpController() {
        theModel = new SignUpBean();
        theModel2 = new MailBean();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public SignUpBean getTheModel() {
        return theModel;
    }

    public void setTheModel(SignUpBean theModel) {
        this.theModel = theModel;
    }

    public String getValidId() {
        return validId;
    }

    public void setValidId(String validId) {
        this.validId = validId;
    }

    public String createProfile() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.
        
        if(checkUserID() == false){
            return "error.xhtml";
        }
        
        if(checkPasswords() == false){
            return "error2.xhtml";
        }
        
        int rowCount = aProfileDAO.createProfile(theModel); // Doing anything with the object after this?
        if (rowCount == 1) {
            theModel2.sendEmail(theModel);
            return "echo.xhtml";
        }
        else {
            return "error.xtml"; 
        }
    }
    
    private boolean checkPasswords () {
        if (theModel.getPassword().equals(theModel.getPassword2())){
            return true;
        }
        return false;
    }
    
    public boolean checkUserID (){ 
        String[] checkID = null;
        ProfileDAO profileDAO = new ProfileDAOImpl();
        checkID = profileDAO.findUserIDs();
        
        for (int i = 0; i < checkID.length; i++){
            if (theModel.getUserID().equals(checkID[i])){
                validId = "Invalid User ID";
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }
    
    public void checkID(){
        String[] checkID = null;
        ProfileDAO profileDAO = new ProfileDAOImpl();
        checkID = profileDAO.findUserIDs();
        
        for (int i = 0; i < checkID.length; i++){
            if (theModel.getUserID().equals(checkID[i])){
                validId = "Invalid User ID";
                return;
            } else{
                validId = "Valid ID";
            }  
        }
    }
}
