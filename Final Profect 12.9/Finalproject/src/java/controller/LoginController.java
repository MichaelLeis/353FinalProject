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
    private boolean signUp;

    public int getID() {
        return ID;
    }

    public boolean isSignUp() {
        return signUp;
    }

    public void setSignUp(boolean signUp) {
        this.signUp = signUp;
    }
    
    public void changeID(int number) {
        ID = number;
    }
    
    public void changeSignUp(boolean c) {
        signUp = c;
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
        if (studentHP.getMajors() == null) {
            ProfileDAO profileDAO = new ProfileDAOImpl();
            studentHP = profileDAO.findStudentHP(ID);
        }
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
        String[] login = profileDAO.login(username);

//        PassHash hash = new PassHash();
//        hash.hashPass(password);
//        String hashedPass = hash.hashPass(password);
        int thisID = Integer.parseInt(login[1]);
        if (login[0].equals(password)) {
            if (login[2].equals("univ")) {
                univHP = profileDAO.findHP(thisID);
                univLogin = profileDAO.findAccount(thisID);
                logIn = true;
                return "universityHP.xhtml";
            }
            if (login[2].equals("stu ")) {
                studentHP = profileDAO.findStudentHP(thisID);
                studentLogin = profileDAO.findStudentAccount(thisID);
                logIn = true;
                return "studentHP.xhtml";
            }
        }
        return "LoginBad.xhtml";
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
        if (studentLogin.getPassword() == null) {
            ProfileDAO profileDAO = new ProfileDAOImpl();
            studentLogin = profileDAO.findStudentAccount(ID);
        }
        return studentLogin;
    }

    public void setStudentLogin(StudentAccount studentLogin) {
        this.studentLogin = studentLogin;
    }
}
