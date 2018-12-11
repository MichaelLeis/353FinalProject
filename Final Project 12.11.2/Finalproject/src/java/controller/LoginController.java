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
    private static UniversityAccount univLogin;
    private static StudentAccount studentLogin;
    private static UniversityBean univHP;
    private static StudentBean studentHP;
    private static int loginID;
    private static int ID;

    public static int getLoginID() {
        return loginID;
    }

    public static void setLoginID(int loginID) {
        LoginController.loginID = loginID;
    }

    public int getID() {
        return ID;
    }

    public void changeID(int number) {
        ID = number;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UniversityAccount getUnivLogin() {
        return univLogin;
    }

    public void setUnivLogin(UniversityAccount univLogin) {
        this.univLogin = univLogin;
    }

    public StudentAccount getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(StudentAccount studentLogin) {
        this.studentLogin = studentLogin;
    }

    public UniversityBean getUnivHP() {
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


    public void clearPage() {
        univLogin = null;
        studentLogin = null;
        univHP = null;
        studentHP = null;
    }

    public LoginController() {
    }

    public String showUnivHP() {
        ProfileDAO profileDAO = new ProfileDAOImpl();
        univHP = profileDAO.findHP(ID);
        univLogin = profileDAO.findAccount(ID);
        return "universityHP.xhtml";
    }
    
    public String showStudentHP() {
        ProfileDAO profileDAO = new ProfileDAOImpl();
        studentHP = profileDAO.findStudentHP(ID);
        studentLogin = profileDAO.findStudentAccount(ID);
        return "studentHP.xhtml";
    }
    
    public String showUnivSearchHP() {
        ProfileDAO profileDAO = new ProfileDAOImpl();
        univHP = profileDAO.findHP(ID);
        univLogin = profileDAO.findAccount(ID);
        return "universityHP_1.xhtml";
    }
    public String showStudentSearchHP() {
        ProfileDAO profileDAO = new ProfileDAOImpl();
        studentHP = profileDAO.findStudentHP(ID);
        studentLogin = profileDAO.findStudentAccount(ID);
        return "studentHP_1.xhtml";
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

//    public int getAttempts() {
//        return attempts;
//    }
//
//    public void setAttempts(int attempts) {
//        this.attempts = attempts;
//    }

    public String checkPassword() {
        ProfileDAO profileDAO = new ProfileDAOImpl();
        String[] login = profileDAO.login(username);

//        PassHash hash = new PassHash();
//        hash.hashPass(password);
//        String hashedPass = hash.hashPass(password);
        int thisID = Integer.parseInt(login[1]);
        if (login[0].equals(password)) {
            if (login[2].equals("univ")) {
                ID = thisID;
                loginID = thisID;
                return showUnivHP();
            }
            if (login[2].equals("stu ")) {
                ID = thisID;
                loginID = thisID;
                return showStudentHP();
            }
        }
        return "LoginBad.xhtml";
    }

    public String isSAuthenticated(ComponentSystemEvent event) {
        String navi = null;

        if (loginID != studentHP.getID()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("access-denied?faces-redirect=true");
        }
        return navi;
    }

    public String isUAuthenticated(ComponentSystemEvent event) {
        String navi = null;

        if (loginID != univHP.getID()) {
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

//    private boolean checkPasswords() {
//        if (univLogin.getPassword().equals(pass2)) {
//            return true;
//        }
//        return false;
//    }
}
