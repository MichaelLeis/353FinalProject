package controller;

import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import model.StudentAccount;
import model.StudentBean;
import model.UniversityAccount;
import model.UniversityBean;

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
    private UniversityAccount univLogin;
    private StudentAccount studentLogin;
    private UniversityBean univHP;
    private StudentBean studentHP;
    private boolean logIn;
    private static int ID = 1;

    public int getID() {
        return ID;
    }

    public void changeID(int number) {
        ID = number;
    }

    public UniversityBean getUnivHP() {
        if (univHP.getMajors() == null) {
            ProfileDAO profileDAO = new ProfileDAOImpl();
            univHP = profileDAO.findHP(ID);
        }
        return univHP;
    }

    public void setUnivHP(UniversityBean univHP) {
        this.univHP = univHP;
    }

    public StudentBean getStudentHP() {
        return studentHP;
    }

    public void setStudentHP(StudentBean studentHP) {
        this.studentHP = studentHP;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public boolean isLogIn() {
        return logIn;
    }

    public void setLogIn(boolean logIn) {
        this.logIn = logIn;
    }

    public LoginController() {
        univLogin = new UniversityAccount();
        studentLogin = new StudentAccount();
        univHP = new UniversityBean();
        studentHP = new StudentBean();
    }

    public UniversityAccount getUnivLogin() {
        if (univLogin.getUniversityN() == null) {
            ProfileDAO profileDAO = new ProfileDAOImpl();
            univLogin = profileDAO.findAccount(ID);
        }
        return univLogin;
    }

    public void setUnivLogin(UniversityAccount univLogin) {
        this.univLogin = univLogin;
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

    public String checkPassword() {
        ProfileDAO profileDAO = new ProfileDAOImpl();
        String checkPass = profileDAO.findPassword(username);

//        PassHash hash = new PassHash();
//        hash.hashPass(password);
//        String hashedPass = hash.hashPass(password);
        if (checkPass.equals(password)) {
            logIn = true;
            univHP = profileDAO.findHP(ID);
            univLogin = profileDAO.findAccount(ID);
            return "universityHP.xhtml";
        } else {
            return "LoginBad.xhtml";
        }
    }

    public String isAuthenticated(ComponentSystemEvent event) {
        String navi = null;

        if (logIn == false) {

            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("access-denied?faces-redirect=true");
        }
        return navi;

    }

    public String updateStudentAcc() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.

        int rowCount = aProfileDAO.updateStudentAcc(studentLogin); // Doing anything with the object after this?
        if (rowCount == 1) {
            return "studentAccountPage.xhtml";
        } else {
            return "updateHPError.xhtml";
        }
    }

    public String updateUniversityAcc() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.

        int rowCount = aProfileDAO.updateUnivAcc(univLogin); // Doing anything with the object after this?
        if (rowCount == 1) {
            return "universityAccountPage.xhtml";
        } else {
            return "updateHPError.xhtml";
        }
    }

    public String updateStudentHP() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.

        int rowCount = aProfileDAO.updateStudentHP(studentHP); // Doing anything with the object after this?
        if (rowCount == 1) {
            return "studentHP.xhtml";
        } else {
            return "updateHPError.xhtml";
        }
    }

    public String updateUnivHP() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.

        int rowCount = aProfileDAO.updateUnivHP(univHP); // Doing anything with the object after this?
        if (rowCount == 1) {
            return "universityHP.xhtml";
        } else {
            return "updateHPError.xhtml";
        }
    }

//    public String updateProfile() {
//        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.
//
//        if (checkPasswords() == false) {
//            return "error2.1.xhtml";
//        }
//
//        int rowCount = aProfileDAO.updateProfile(univLogin); // Doing anything with the object after this?
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
        if (univLogin.getPassword().equals(pass2)) {
            return true;
        }
        return false;
    }

    public StudentAccount getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(StudentAccount studentLogin) {
        this.studentLogin = studentLogin;
    }
}
